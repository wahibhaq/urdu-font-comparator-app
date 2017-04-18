package projects.hobby.urdufontcomparator.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder unBinder;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unBinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        unBinder.unbind();
        super.onDestroyView();
    }

    protected void sharedPrefApply(@StringRes int key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(key), value);
        editor.apply();
    }

    protected void removeSharedPref(@StringRes int key) {
        sharedPreferences.edit().remove(getString(key)).apply();
    }


}
