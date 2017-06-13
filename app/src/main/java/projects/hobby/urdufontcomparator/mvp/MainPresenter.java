package projects.hobby.urdufontcomparator.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.models.UrduFont;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.tracking.TrackingManager;
import projects.hobby.urdufontcomparator.utils.Utils;
import timber.log.Timber;

public class MainPresenter implements MainMvp.Presenter {

    private final MainMvp.View view;

    private final UrduTextSource urduTextSource;

    private final DatabaseReference databaseReference;

    private final TrackingManager tracker;

    private List<UrduFont> fontsFromFirebase;

    private ValueEventListener valueEventListener;

    public MainPresenter(MainMvp.View view,
                         UrduTextSource urduTextSource,
                         DatabaseReference databaseReference,
                         TrackingManager trackingManager) {
        this.view = view;
        this.urduTextSource = urduTextSource;
        this.databaseReference = databaseReference;
        this.tracker = trackingManager;
        fontsFromFirebase = new ArrayList<>();
    }

    @Override
    public void dispose() {
        databaseReference.removeEventListener(valueEventListener);
    }

    @Override
    public void loadFontsAvailable() {
        view.showProgress();
        addDatabaseFetchEventListener();
    }

    private void addDatabaseFetchEventListener() {
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fontsFromFirebase.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    try {
                        UrduFont font = child.getValue(UrduFont.class);
                        fontsFromFirebase.add(font);
                    } catch (Exception ex) {
                        view.hideProgress();
                        handleError(R.string.error_unable_to_fetch_fonts, ex.getMessage());
                    }
                }

                view.hideProgress();
                view.setFontSelectorContent(fontsFromFirebase);
                dispose();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                view.hideProgress();
                handleError(R.string.error_unable_to_fetch_fonts, error.getMessage());
            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }

    @Override
    public void handleFontInfoAction(UrduFont font) {
        if (font == null) {
            handleError(R.string.error_message_unknown_font);
        } else {
            float rating = font.getRatingSum() / font.getRatingCount();
            String ratingStr = rating + " (" + font.getRatingCount() + ")";
            tracker.openFontDetails(font.getName());
            view.showFontDetailsDialog(font, urduTextSource.prepareFontInfoDialogText(font),ratingStr);
        }
    }

    @Override
    public void handleFontRatingShowAction(UrduFont font) {
        if (font == null) {
            handleError(R.string.error_message_unknown_font);
        } else {
            tracker.openFontRating(font.getName());
            view.showFontRatingDialog(font);
        }
    }

    @Override
    public void handleRatingUpdateAction(int fontIndex, UrduFont font) {
        tracker.submitFontRating(font.getName(), font.getRatingValue());
        databaseReference.child(String.valueOf(fontIndex))
                .setValue(font)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            view.showToast(R.string.thank_you);
                        } else {
                            handleError(R.string.error_unable_to_update_rating);
                        }
                    }
                });
    }

    private void handleError(@StringRes int errorToDisplay, String errorMessageToLog) {
        if (Utils.isNullOrEmpty(errorMessageToLog)) {
            handleError(R.string.error_message_generic);
        } else {
            Timber.e(getClass().getSimpleName(), errorMessageToLog);
            handleError(errorToDisplay);
        }
    }

    private void handleError(@StringRes int errorMessage) {
        tracker.errorShown();
        view.showError(errorMessage);
    }

}
