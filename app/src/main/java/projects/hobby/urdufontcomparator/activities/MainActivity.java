package projects.hobby.urdufontcomparator.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.fragments.ContentFragment;
import projects.hobby.urdufontcomparator.fragments.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void showDefaultFragment() {
        Fragment fragment = MainFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
}
