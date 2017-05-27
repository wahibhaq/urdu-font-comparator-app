package projects.hobby.urdufontcomparator.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Patterns;
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
import com.hsalf.smilerating.SmileRating;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import me.relex.circleindicator.CircleIndicator;
import projects.hobby.urdufontcomparator.MainApplication;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.adapter.ContentAdapter;
import projects.hobby.urdufontcomparator.dagger.MainMvpModule;
import projects.hobby.urdufontcomparator.models.UrduFont;
import projects.hobby.urdufontcomparator.mvp.MainMvp;
import projects.hobby.urdufontcomparator.utils.Utils;
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

    @BindView(R.id.indicator)
    protected CircleIndicator circleIndicator;

    @Inject
    protected MainMvp.Presenter presenter;

    @Inject
    protected SharedPreferences sharedPreferences;

    private UrduFont currentSelectedFont;

    private Dialog progressDialog;

    private List<UrduFont> fonts;

    private List<String> fontNames;

    private UniversalPickerDialog.Builder builderPickerDialog;

    private static int fontRatingValue = 0;

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
                .setInputs(new UniversalPickerDialog.Input(0, (AbstractList<String>) fontNames));
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
        if (Utils.isOnline(getActivity())) {
            presenter.loadFontsAvailable();
        } else {
            Utils.showConnectionErrorDialog(getActivity());
        }
        setActionbarTitle();
        setDefaultFontSize();
    }

    private void setDefaultFontSize() {
        seekBar.setProgress(0);
        saveUpdatedFontSize(MIN_SEEKBAR_LEVEL);
    }

    private void setActionbarTitle() {
        final android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity())
                .getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name_expanded);
        }
    }

    @OnClick(R.id.button_font_details)
    void setInfoButtonContent() {
        presenter.handleFontInfoAction(currentSelectedFont);
    }

    @OnClick(R.id.button_rate_this_font)
    void showFontRatingDialog() {
        presenter.handleFontRateAction(currentSelectedFont);
    }

    @Override
    public void setFontSelectorContent(final List<UrduFont> fonts) {
        //Gets called after successful fetching from backend
        this.fonts = fonts;

        fontNames = new ArrayList<>();
        for (UrduFont font : fonts) {
            fontNames.add(font.getFontName());
        }

        ContentAdapter contentAdapter = new ContentAdapter(getChildFragmentManager(), fonts);
        viewPager.setAdapter(contentAdapter);

        //Initialize and set Adapter
        ArrayAdapter<String> fontArrayAdapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, fontNames);
        fontArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFontNames.setAdapter(fontArrayAdapter);
        spinnerFontNames.setTextDirection(View.TEXT_DIRECTION_RTL);
        setPickerDialog();
        currentSelectedFont = fonts.get(0); //setting default value
        presenter.handleFontSelection(currentSelectedFont.getFontName());
        viewPager.setVisibility(View.VISIBLE);
        circleIndicator.setViewPager(viewPager);
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
            if (currentSelectedFont != null && builderPickerDialog != null) {
                builderPickerDialog.show();
            } else {
                presenter.loadFontsAvailable();
            }
        }
        return true;
    }

    @Override
    public void showFontDetailsDialog(UrduFont font, String content) {
        showFontDetailsDialog(getActivity(),font.getFontName(), content);
    }

    public static void showFontDetailsDialog(Context context, String title, String message) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewFontDetails = inflater.inflate(R.layout.dialog_font_details, null);

        //To make sure Font FileName doesn't end up as a clickable url because it is automatically
        //turned into a url
        final SpannableString content = new SpannableString(message);
        Linkify.addLinks(content, Patterns.WEB_URL, null, new Linkify.MatchFilter() {
            @Override
            public boolean acceptMatch(CharSequence seq, int start, int end) {
                String url = seq.subSequence(start, end).toString();
                //Apply the default matcher too. This will remove filenames that matched.
                return !url.contains(".ttf") && Linkify.sUrlMatchFilter
                        .acceptMatch(seq, start, end);
            }
        }, null);

        TextView tvMessage = (TextView) viewFontDetails
                .findViewById(R.id.text_font_details_message);
        if(tvMessage != null) {
            tvMessage.setText(content);
            tvMessage.setMovementMethod(LinkMovementMethod.getInstance()); //Making link clickable
            tvMessage.setLinkTextColor(ContextCompat.getColor(context, R.color.blue));
        }

        final Dialog dialog = new LovelyCustomDialog(context)
                .setView(viewFontDetails)
                .setTopColorRes(R.color.colorPrimaryLight)
                .setTitle(title)
                .setIcon(R.drawable.ic_info_outline)
                .show();

        TextView btnGotIt = (TextView) viewFontDetails.findViewById(R.id.button_font_details_got_it);
        btnGotIt.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if(dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (progressDialog == null) {
                progressDialog = Utils.showProgressUpdateDialog(getActivity(),
                        getString(R.string.loading_message));
            }
        } else if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showError(int errorMessageIf) {
        Utils.showSimpleDialogWithoutTitle(getActivity(), getString(errorMessageIf));
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

    @Override
    public void showFontRatingDialog(UrduFont font) {
        showRatingDialog(getActivity(), font.getFontName());
    }

    public static void showRatingDialog(Context context , String fontName){
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewRatingBar = inflater.inflate(R.layout.dialog_rating_bar, null);
        final Dialog dialog = new LovelyCustomDialog(context)
                .setView(viewRatingBar)
                .setTopColorRes(R.color.colorPrimary)
                .setTitle(context.getString(R.string.rate_font_dialog_title, fontName))
                .setIcon(R.drawable.ic_star_border)
                .show();

        SmileRating smileRating = (SmileRating) viewRatingBar.findViewById(R.id.rating_bar);
        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                //Log.d("TAG", level + " clicked");
                fontRatingValue = level;
            }
        });

        TextView btnSubmit = (TextView) viewRatingBar.findViewById(R.id.button_rating_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call submit function
                //Send fontRatingValue to endpoint and show Toast with message "Thank You!"
            }
        });

        TextView btnCancel = (TextView) viewRatingBar.findViewById(R.id.button_rating_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    private void saveUpdatedFontSize(int updatedFontSize) {
        applySharedPref(R.string.font_size, updatedFontSize);
    }

    @Override
    public void onPick(int[] selectedValues, int key) {
        int position = selectedValues[0];
        setCurrentSelectedFont(position);
        presenter.handleFontSelection(currentSelectedFont.getFontName());
        viewPager.setCurrentItem(position, false); // updating viewpager item
    }


    private void setCurrentSelectedFont(int position) {
        spinnerFontNames.setSelection(position);
        currentSelectedFont = fonts.get(position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeSharedPref(R.string.font_size);
    }
}