package projects.hobby.urdufontcomparator.fragments;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.AbstractList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import projects.hobby.urdufontcomparator.MainApplication;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.adapter.ContentAdapter;
import projects.hobby.urdufontcomparator.dagger.MainMvpModule;
import projects.hobby.urdufontcomparator.models.UrduFontsSource;
import projects.hobby.urdufontcomparator.mvp.MainMvp;
import projects.hobby.urdufontcomparator.utils.UiUtils;
import stfalcon.universalpickerdialog.UniversalPickerDialog;


public class MainFragment extends BaseFragment implements MainMvp.View,
        UniversalPickerDialog.OnPickListener {

    private static final int MIN_SEEKBAR_LEVEL = 16; //min font size allowed

    @BindView(R.id.spinner_font_names)
    protected Spinner spinnerFontNames;

    @BindView(R.id.seekbar)
    protected SeekBar seekBar;

    @BindView(R.id.content_viewpager)
    protected ViewPager viewPager;

    @Inject
    protected MainMvp.Presenter presenter;

    @Inject
    protected SharedPreferences sharedPreferences;

    private UrduFontsSource currentSelectedFont;

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
                .mvpComponent(new MainMvpModule(this))
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
        spinnerFontNames.setSelection(UrduFontsSource.getDefaultFont().ordinal());
        presenter.handleFontSelection(getString(UrduFontsSource.getDefaultFont().fontFileName));
        currentSelectedFont = UrduFontsSource.getDefaultFont();
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
        ContentAdapter contentAdapter = new ContentAdapter(getChildFragmentManager(), fonts);
        viewPager.setAdapter(contentAdapter);
        //Initialize and set Adapter
        ArrayAdapter<String> fontArrayAdapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, fontNames);
        fontArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFontNames.setAdapter(fontArrayAdapter);
        spinnerFontNames.setTextDirection(View.TEXT_DIRECTION_RTL);
        setPickerDialog();
        setViewPagerPageChangeListener();
    }

    private void setViewPagerPageChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentSelectedFont(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnTouch(R.id.spinner_font_names)
    protected boolean onSpinnerTouchListener(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            builderPickerDialog.show();
        }
        return true;
    }

    @Override
    public void showFontInfoDialog(UrduFontsSource font, String content) {
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
                //setting default value so seekbar and contentBody font size
                //doesn't conflict with each other.
                int updatedFontSize = MIN_SEEKBAR_LEVEL;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updatedFontSize = MIN_SEEKBAR_LEVEL + progress;
                    saveUpdatedFontSize(updatedFontSize);
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

    private void saveUpdatedFontSize(int updatedFontSize) {
        applySharedPref(R.string.font_size, updatedFontSize);
    }

    @Override
    public void onPick(int[] selectedValues, int key) {
        int position = selectedValues[0];
        setCurrentSelectedFont(position);
        presenter.handleFontSelection(getString(currentSelectedFont.fontFileName));
        viewPager.setCurrentItem(position, false); // updating viewpager item
    }


    private void setCurrentSelectedFont(int position) {
        spinnerFontNames.setSelection(position);
        String selectedFontName = fonts.get(position);
        currentSelectedFont = UrduFontsSource.from(selectedFontName);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeSharedPref(R.string.font_size);
    }
}