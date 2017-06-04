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
import projects.hobby.urdufontcomparator.utils.Utils;
import rx.Observable;
import rx.functions.Action1;
import timber.log.Timber;

public class MainPresenter implements MainMvp.Presenter {

    private static final String FONTS = "fonts/";

    private final MainMvp.View view;

    private final UrduTextSource urduTextSource;

    private final DatabaseReference databaseReference;

    private List<UrduFont> fontsFromFirebase;

    private ValueEventListener valueEventListener;

    public MainPresenter(MainMvp.View view,
            UrduTextSource urduTextSource,
            DatabaseReference databaseReference) {
        this.view = view;
        this.urduTextSource = urduTextSource;
        this.databaseReference = databaseReference;
        init();
    }

    private void init() {
        fontsFromFirebase = new ArrayList<>();
        valueEventListener = new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {
                fontsFromFirebase.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    try {
                        UrduFont font = child.getValue(UrduFont.class);
                        fontsFromFirebase.add(font);
                    } catch (Exception ex) {
                        view.showProgress();
                        handleError(R.string.error_unable_to_fetch_fonts, ex.getMessage());
                    }
                }

                view.showProgress();
                view.setFontSelectorContent(fontsFromFirebase);
                dispose();
            }

            @Override public void onCancelled(DatabaseError error) {
                view.hideProgress();
                handleError(R.string.error_unable_to_fetch_fonts, error.getMessage());
            }
        };
    }

    @Override
    public void dispose() {
        databaseReference.removeEventListener(valueEventListener);
    }

    @Override
    public void loadFontsAvailable() {
        view.showProgress();
        databaseReference.addValueEventListener(valueEventListener);
    }

    @Override
    public void handleFontSelection(String fontFileName) {
        if(fontFileName.isEmpty()) {
            handleError(R.string.error_message_unknown_font);
        } else {
            view.showProgress();
            Observable.just(getFontAsset(fontFileName))
                    .subscribe(new Action1<String>() {
                        @Override public void call(String fontAsset) {
                            view.hideProgress();
                            view.showAndSetSeekbar(true);
                        }
                    }, new Action1<Throwable>() {
                        @Override public void call(Throwable throwable) {
                            view.hideProgress();
                            view.showAndSetSeekbar(false);
                            handleError(R.string.error_message_unknown_font, throwable.getMessage());
                        }
                    });
        }
    }

    @Override
    public void handleFontInfoAction(UrduFont font) {
        if(font == null) {
            view.showError(R.string.error_message_unknown_font);
        } else {
            view.showFontDetailsDialog(font, urduTextSource.prepareFontInfoDialogText(font));
        }
    }

    @Override
    public void handleFontRatingShowAction(UrduFont font) {
        if(font == null) {
            view.showError(R.string.error_message_unknown_font);
        } else {
            view.showFontRatingDialog(font);
        }
    }

    @Override
    public void handleRatingUpdateAction(int fontIndex, UrduFont font) {
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

    private String getFontAsset(String fileName) {
        return FONTS.concat(fileName);
    }

    private void handleError(@StringRes int errorToDisplay, String errorMessageToLog) {
        if(Utils.isNullOrEmpty(errorMessageToLog)) {
            handleError(R.string.error_message_generic);
        } else {
            Timber.e(getClass().getSimpleName(), errorMessageToLog);
            handleError(errorToDisplay);
        }
    }

    private void handleError(@StringRes int errorMessage) {
        view.showError(errorMessage);
    }
}
