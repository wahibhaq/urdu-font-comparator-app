package projects.hobby.urdufontcomparator.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.models.UrduFontsSource;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.mvp.ContentMvp;
import projects.hobby.urdufontcomparator.mvp.ContentPresenter;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;

/**
 * Created by Zeeshan on 4/12/17.
 */

public class ContentFragment extends BaseFragment implements ContentMvp.View, MainFragment.OnFontSizeChangeListener {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.text_body)
    protected TextView textBody;

    private static final String ARG_PARAM = "param1";

    private ContentPresenter presenter;

    public ContentFragment() {

    }

    public static ContentFragment newInstance(String font) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, font);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ContentPresenter(this, new CustomFontManager(getActivity().getAssets()),
                new UrduTextSource(getActivity()));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String font = getArguments().getString(ARG_PARAM);
        if (font != null) {
            presenter.handleFontSelection(getActivity().getString(UrduFontsSource.from(font).fontFileName));
        }
    }

    @Override
    public void setConvertedText(Typeface typeface) {
        if (typeface != null) {
            textBody.setTypeface(typeface);
        } else {
            textBody.setTypeface(Typeface.DEFAULT);
        }
        presenter.handleSampleTextShowing();
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
    public void size(int fontSize) {
        presenter.handleFontSize(fontSize);
    }
}
