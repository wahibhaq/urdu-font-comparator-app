package projects.hobby.urdufontcomparator.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            showDefaultFragment();
        }
    }

    private void showDefaultFragment() {
        Fragment fragment = MainFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
}
