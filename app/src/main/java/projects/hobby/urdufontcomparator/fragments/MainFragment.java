package projects.hobby.urdufontcomparator.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.managers.CustomFontManager;
import projects.hobby.urdufontcomparator.models.UrduFonts;

public class MainFragment extends Fragment {

    private static final String FONTS = "fonts/";

    private static final int FONT_SIZE = 16;

    private static final String LINE_SPACINGS = "\n\n";

    private Unbinder unBinder;

    @BindView(R.id.spinner_font_names)
    Spinner spinnerFontNames;

    @BindView(R.id.text_body)
    TextView textBody;

    ArrayAdapter<String> fontArrayAdapter;

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
    }

    private void setupUI() {
        spinnerFontNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                final String selectedFontName = adapter.getItemAtPosition(position).toString();
                final UrduFonts font = UrduFonts.from(selectedFontName);
                String fontAsset = FONTS.concat(getString(font.fontFileName));
                Typeface tf = CustomFontManager.getInstance().getFont(fontAsset);
                if(tf != null) {
                    textBody.setTypeface(tf);
                } else {
                    textBody.setTypeface(Typeface.DEFAULT);
                }

                textBody.setTextSize(FONT_SIZE);
                textBody.setText(formattedText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
}
