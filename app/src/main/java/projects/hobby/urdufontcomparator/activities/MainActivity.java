package projects.hobby.urdufontcomparator.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

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

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.actionbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_licenses:
                // User chose the "Licenses" item, show the font licenses info
                return true;

            case R.id.action_about_dev:
                // User chose the "About Dev" item, show about me info
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
