package projects.hobby.urdufontcomparator.fragments;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.managers.CustomFontManager;
import projects.hobby.urdufontcomparator.models.UrduFonts;

public class MainFragment extends Fragment {

    private static final String FONTS = "fonts/";

    private static final String LINE_SPACINGS = "\n-------------\n";

    private Unbinder unBinder;

    @BindView(R.id.spinner_font_names)
    protected Spinner spinnerFontNames;

    @BindView(R.id.text_body)
    protected TextView textBody;

    ArrayAdapter<String> fontArrayAdapter;

    private UrduFonts currentSelectedFont;

    public static MainFragment newInstance() {
        return new MainFragment();
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
        unBinder = ButterKnife.bind(this, view);
        init();
        setupUI();
    }

    private void init() {
        //init CustomFontManager
        CustomFontManager.init(getResources().getAssets());

        //Initialize and set Adapter
        fontArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, UrduFonts.getFontNames());
        fontArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFontNames.setAdapter(fontArrayAdapter);
        spinnerFontNames.setTextDirection(View.TEXT_DIRECTION_RTL);
    }

    private void setupUI() {
        spinnerFontNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                final String selectedFontName = adapter.getItemAtPosition(position).toString();
                currentSelectedFont = UrduFonts.from(selectedFontName);
                String fontAsset = FONTS.concat(getString(currentSelectedFont.fontFileName));
                Typeface tf = CustomFontManager.getInstance().getFont(fontAsset);
                if(tf != null) {
                    textBody.setTypeface(tf);
                } else {
                    textBody.setTypeface(Typeface.DEFAULT);
                }
                textBody.setText(formattedText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @OnClick(R.id.button_font_details)
    void setInfoButtonContent() {
        if(currentSelectedFont != null) {
            showDialog(currentSelectedFont.fontLabel, getString(R.string.provider,
                    currentSelectedFont.provider));
        }
    }

    private SpannableString formattedText() {
        String poetry = getString(R.string.urdu_sample_text_poetry);
        String textToBold = getString(R.string.urdu_sample_text_bold);
        String alphabets = getString(R.string.urdu_sample_text_alphabets);
        String finalText = poetry.concat(LINE_SPACINGS).concat(textToBold).concat(LINE_SPACINGS).concat(alphabets);

        final SpannableString spannableString = new SpannableString(finalText);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, poetry.length(),
                finalText.length() - alphabets.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override public void onDestroyView() {
        unBinder.unbind();
        super.onDestroyView();
    }

    private void showDialog(@StringRes int title, String content) {
        //TODO find a better way to show custom view
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(title);
        dialog.setMessage(content);
        dialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
