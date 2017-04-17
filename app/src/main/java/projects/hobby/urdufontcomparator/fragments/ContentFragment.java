package projects.hobby.urdufontcomparator.fragments;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import projects.hobby.urdufontcomparator.MainApplication;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.dagger.ContentMvpModule;
import projects.hobby.urdufontcomparator.models.UrduFontsSource;
import projects.hobby.urdufontcomparator.mvp.ContentMvp;


public class ContentFragment extends BaseFragment implements ContentMvp.View,
        SharedPreferences.OnSharedPreferenceChangeListener {
    private final static int DEFAULT_FONT_SIZE = 16;
    @BindView(R.id.text_body)
    protected TextView contentBody;

    private static final String ARG_FONT = "font";

    @Inject
    protected ContentMvp.Presenter presenter;

    @Inject
    protected SharedPreferences sharedPreferences;

    public ContentFragment() {

    }

    public static ContentFragment newInstance(String font) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FONT, font);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.get(getActivity()).getComponent()
                .contentmvpComponent(new ContentMvpModule(this))
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String font = getArguments().getString(ARG_FONT);
        if (font != null) {
            presenter.handleFontSelection(getActivity().
                    getString(UrduFontsSource.from(font).fontFileName));
        }
        contentBody.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                sharedPreferences.getInt(getString(R.string.font_size), DEFAULT_FONT_SIZE));
    }

    @Override
    public void setConvertedText(Typeface typeface) {
        if (typeface != null) {
            contentBody.setTypeface(typeface);
        } else {
            contentBody.setTypeface(Typeface.DEFAULT);
        }
        presenter.handleSampleTextShowing();
    }

    @Override
    public void setSampleText(String sampleText) {
        contentBody.setText(sampleText);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.font_size))) {
            if (contentBody != null) // sometimes app crashes when changing size
                contentBody.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                        sharedPreferences.getInt(key, DEFAULT_FONT_SIZE));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
