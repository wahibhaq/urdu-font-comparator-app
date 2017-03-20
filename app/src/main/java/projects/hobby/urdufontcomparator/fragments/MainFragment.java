package projects.hobby.urdufontcomparator.fragments;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.OnClick;

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

import static projects.hobby.urdufontcomparator.utils.UiUtils.getLineSpacings;
import static projects.hobby.urdufontcomparator.utils.UiUtils.getLineSpacingsWithDash;

public class MainFragment extends BaseFragment implements MainMvp.View , UniversalPickerDialog.OnPickListener{

    private static final int MIN_SEEKBAR_LEVEL = 16; //min font size allowed

    @BindView(R.id.text_body)
    protected TextView textBody;

    @BindView(R.id.seekbar)
    protected SeekBar seekBar;

    @BindView(R.id.tv_selected_font)
    protected TextView selectedFont;

    @Inject
    protected MainMvp.Presenter presenter;

    private UrduFonts currentSelectedFont;

    private Dialog progressDialog;
    private List<String> allFont;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.get(getActivity()).getComponent()
                .mvpComponent(new MvpModule(this))
                .inject(this);
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
    }

    @OnClick(R.id.button_font_details)
    void setInfoButtonContent() {
        presenter.handleFontInfoAction(currentSelectedFont.serializedName);
    }

    @OnClick(R.id.btn_choose_font)
    void setSelectFontButton(){
        presenter.loadFontsAvailable();
    }

    private String formattedDialogContent(final UrduFonts font) {
        return getLineSpacings()
                .concat(getString(R.string.provider, font.provider))
                .concat(getLineSpacings())
                .concat(getString(R.string.home_website, font.website))
                .concat(getLineSpacings())
                .concat(getString(R.string.download_url, font.downloadLink));
    }

    private SpannableString formattedUrduText() {
        String poetry = getString(R.string.urdu_sample_text_poetry);
        String textToBold = getString(R.string.urdu_sample_text_bold);
        String alphabets = getString(R.string.urdu_sample_text_alphabets);
        String finalText = poetry.concat(getLineSpacingsWithDash())
                .concat(textToBold)
                .concat(getLineSpacingsWithDash())
                .concat(alphabets);

        final SpannableString spannableString = new SpannableString(finalText);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, poetry.length(),
                finalText.length() - alphabets.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public void showFontSelector(final List<String> fontNames) {

        //I know this is bad way
        this.allFont = fontNames;

        new UniversalPickerDialog.Builder(getActivity())
                .setTitle(R.string.select_font)
                .setListener(this)
                .setInputs(
                        new UniversalPickerDialog.Input(0, (AbstractList<?>) fontNames)
                )
                .show();
    }

    @Override
    public void setConvertedText(Typeface tf) {
        if(tf != null) {
            textBody.setTypeface(tf);
        } else {
            textBody.setTypeface(Typeface.DEFAULT);
        }
        textBody.setText(formattedUrduText());
    }

    @Override
    public void showFontInfoDialog(UrduFonts font) {
        UiUtils.showDialogWithUrlsWithTitle(getActivity(), font.fontLabel,
                formattedDialogContent(font));
    }

    @Override
    public void showProgress(boolean show) {
        if(show) {
            if(progressDialog == null) {
                progressDialog = UiUtils.showProgressUpdateDialog(getActivity(),
                        getString(R.string.loading_message));
            }
        } else if (progressDialog!= null) {
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
        }else{
            seekBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setFontSize(int size) {
        textBody.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    @Override
    public void onPick(int[] selectedValues, int key) {
        if (allFont != null) {
            String selectedFontName = allFont.get(selectedValues[0]);
            currentSelectedFont = UrduFonts.from(selectedFontName);
            presenter.handleFontSelection(getString(currentSelectedFont.fontFileName));
            selectedFont.setText(selectedFontName);
        }
    }
}
