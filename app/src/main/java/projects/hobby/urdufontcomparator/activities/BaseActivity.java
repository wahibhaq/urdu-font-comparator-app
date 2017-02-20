package projects.hobby.urdufontcomparator.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.utils.UiUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(savedInstanceState == null) {
            showDefaultFragment();
        }
    }

    //defined by the child activities
    protected abstract void showDefaultFragment();

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
                UiUtils.showSimpleDialog(this, R.string.licenses,
                        UiUtils.getLineSpacings().concat("License info will be shown here"));
                return true;

            case R.id.action_about_dev:
                // User chose the "About Dev" item, show about me info
                UiUtils.showDialogWithUrlsInContent(this, R.string.about_me,
                        UiUtils.getLineSpacings().concat("Wahib-Ul-Haq \n\n http://wahibhaq.com"));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
