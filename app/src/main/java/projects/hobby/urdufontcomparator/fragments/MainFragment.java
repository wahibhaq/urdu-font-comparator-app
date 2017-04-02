package projects.hobby.urdufontcomparator.fragments;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import java.util.AbstractList;
import java.util.List;
import javax.inject.Inject;
import projects.hobby.urdufontcomparator.MainApplication;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.dagger.MvpModule;
import projects.hobby.urdufontcomparator.models.UrduFonts;
import projects.hobby.urdufontcomparator.mvp.MainMvp;
import projects.hobby.urdufontcomparator.utils.UiUtils;
import stfalcon.universalpickerdialog.UniversalPickerDialog;

public class MainFragment extends BaseFragment implements MainMvp.View,
        UniversalPickerDialog.OnPickListener {

    private static final int MIN_SEEKBAR_LEVEL = 16; //min font size allowed

    @BindView(R.id.spinner_font_names)
    protected Spinner spinnerFontNames;

    @BindView(R.id.text_body)
    protected TextView textBody;

    @BindView(R.id.seekbar)
    protected SeekBar seekBar;

    @Inject
    protected MainMvp.Presenter presenter;

    private UrduFonts currentSelectedFont;

    private Dialog progressDialog;

    private List<String> fonts;

    private UniversalPickerDialog.Builder builderPickerDialog;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.get(getActivity()).getComponent()
                .mvpComponent(new MvpModule(this))
                .inject(this);
    }

    private void setPickerDialog() {
        builderPickerDialog = new UniversalPickerDialog.Builder(getActivity())
                .setTitle(R.string.select_font)
                .setTitleColorRes(R.color.blue)
                .setListener(this)
                .setInputs(new UniversalPickerDialog.Input(0, (AbstractList<String>) fonts));
    }

    private void setDefaultContent() {
        spinnerFontNames.setSelection(UrduFonts.getDefaultFont().ordinal());
        presenter.handleFontSelection(getString(UrduFonts.getDefaultFont().fontFileName));
        currentSelectedFont = UrduFonts.getDefaultFont();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadFontsAvailable();
        setDefaultContent();
    }

    @OnClick(R.id.button_font_details)
    void setInfoButtonContent() {
        presenter.handleFontInfoAction(currentSelectedFont.serializedName);
    }

    @Override
    public void setFontSelectorContent(final List<String> fontNames) {
        this.fonts = fontNames;

        //Initialize and set Adapter
        ArrayAdapter<String> fontArrayAdapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, fontNames);
        fontArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFontNames.setAdapter(fontArrayAdapter);
        spinnerFontNames.setTextDirection(View.TEXT_DIRECTION_RTL);
        setPickerDialog();
    }

    @OnTouch(R.id.spinner_font_names)
    protected boolean onSpinnerTouchListener(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            builderPickerDialog.show();
        }
        return true;
    }

    @Override
    public void setConvertedText(Typeface tf) {
        if (tf != null) {
            textBody.setTypeface(tf);
        } else {
            textBody.setTypeface(Typeface.DEFAULT);
        }
        presenter.handleSampleTextShowing();
    }

    @Override
    public void showFontInfoDialog(UrduFonts font, String content) {
        UiUtils.showDialogWithUrlsWithTitle(getActivity(), font.fontLabel, content);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (progressDialog == null) {
                progressDialog = UiUtils.showProgressUpdateDialog(getActivity(),
                        getString(R.string.loading_message));
            }
        } else if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showError(int errorMessageIf) {
        UiUtils.showSimpleDialogWithoutTitle(getActivity(), getString(errorMessageIf));
    }

    @Override
    public void showAndSetSeekbar(boolean show) {
        if (show) {
            seekBar.setVisibility(View.VISIBLE);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                //setting default value so seekbar and textBody font size
                //doesn't conflict with each other.
                int progressChanged = MIN_SEEKBAR_LEVEL;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progressChanged = MIN_SEEKBAR_LEVEL + progress;
                    presenter.handleFontSize(progressChanged);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        } else {
            seekBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setFontSize(int size) {
        textBody.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    @Override
    public void setSampleText(String sampleText) {
        textBody.setText(sampleText);
    }

    @Override
    public void onPick(int[] selectedValues, int key) {
        int position = selectedValues[0];
        spinnerFontNames.setSelection(position); // setting selected font to spinner
        final String selectedFontName = fonts.get(position);
        currentSelectedFont = UrduFonts.from(selectedFontName);
        presenter.handleFontSelection(getString(currentSelectedFont.fontFileName));
    }
}
