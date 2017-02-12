package projects.hobby.urdufontcomparator.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
                String fontAsset = "fonts/".concat(getString(font.fontFileName));
                Typeface tf = CustomFontManager.getInstance().getFont(fontAsset);
                if(tf != null) {
                    textBody.setTypeface(tf);
                } else {
                    textBody.setTypeface(Typeface.DEFAULT);
                }
                textBody.setText(getString(R.string.urdu_sample_text_poetry));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override public void onDestroyView() {
        unBinder.unbind();
        super.onDestroyView();
    }
}
