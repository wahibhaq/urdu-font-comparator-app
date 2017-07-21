package com.androidistan.urdufontcomparator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import butterknife.BindView;
import com.androidistan.urdufontcomparator.R;

public class LicenseFragment extends BaseFragment {

    @BindView(R.id.web_view)
    protected WebView webView;

    public static LicenseFragment newInstance() {
        return new LicenseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_licenses, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        setActionbarTitle();
        webView.loadUrl(getString(R.string.asset_file_path_license));
    }


    private void setActionbarTitle() {
        final android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity())
                .getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.license_screen_title);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        //hiding overflow menu
        menu.setGroupVisible(R.id.menu_overflow, false);
        super.onPrepareOptionsMenu(menu);
    }
}
