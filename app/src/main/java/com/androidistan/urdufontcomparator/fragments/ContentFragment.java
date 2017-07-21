package com.androidistan.urdufontcomparator.fragments;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import javax.inject.Inject;
import com.androidistan.urdufontcomparator.MainApplication;
import com.androidistan.urdufontcomparator.R;
import com.androidistan.urdufontcomparator.dagger.ContentMvpModule;
import com.androidistan.urdufontcomparator.models.UrduFont;
import com.androidistan.urdufontcomparator.mvp.ContentMvp;


public class ContentFragment extends BaseFragment implements ContentMvp.View,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private final static int DEFAULT_FONT_SIZE = 20; //min font size

    @BindView(R.id.text_body)
    protected TextView contentBody;

    private static final String ARG_FONT_NAME = "font_name";

    private static final String ARG_FONT_FILE = "font_file";


    @Inject
    protected ContentMvp.Presenter presenter;

    @Inject
    protected SharedPreferences sharedPreferences;

    public ContentFragment() {

    }

    public static ContentFragment newInstance(UrduFont font) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FONT_NAME, font.getName());
        args.putString(ARG_FONT_FILE, font.getFilename());
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
        String fontName = getArguments().getString(ARG_FONT_NAME);
        String fontFileName = getArguments().getString(ARG_FONT_FILE);
        presenter.handleFontSelection(fontName, fontFileName);
        setTextFontSize(sharedPreferences.getInt(getString(R.string.font_size), DEFAULT_FONT_SIZE));
    }

    public void setTextFontSize(int size) {
        contentBody.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
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
        if (key.equals(getString(R.string.font_size)) && contentBody != null) {
            setTextFontSize(sharedPreferences.getInt(key, DEFAULT_FONT_SIZE));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
