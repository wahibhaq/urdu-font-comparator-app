package projects.hobby.urdufontcomparator.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.utils.UiUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        if (savedInstanceState == null) {
            showDefaultFragment();
        }
    }

    private void setupToolbar() {
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    //defined by the child activities
    protected abstract void showDefaultFragment();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.actionbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
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
                UiUtils.showSimpleDialogWithTitle(this, R.string.licenses,
                        "License info will be shown here");
                return true;

            case R.id.action_about_dev:
                // User chose the "About Dev" item, show about me info
                UrduTextSource urduTextSource = new UrduTextSource(this);
                UiUtils.showDialogWithUrlsWithTitle(this, R.string.about_me,
                        urduTextSource.prepareDevsInfoDialogText());
                return true;

            case R.id.action_email:
                Intent email = new Intent(Intent.ACTION_SEND, Uri.parse(getString(R.string.mail_to)));
                email.setType(getString(R.string.intent_type));
                email.putExtra(Intent.EXTRA_EMAIL, getReceptionName(R.string.dev_email));
                try {
                    startActivity(Intent.createChooser(email, getString(R.string.send_email)));
                } catch (Exception ex) {
                    Toast.makeText(this, R.string.email_app_isnt_installed_message, Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.action_tweet:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.dev_twitter_handle));
                intent.setType(getString(R.string.intent_type));
                if (isTwitterInstalled(intent)) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.twitter_app_isnt_installed_message, Toast.LENGTH_SHORT).show();
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private boolean isTwitterInstalled(Intent intent) {
        PackageManager packManager = getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean resolved = false;
        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith(getString(R.string.twitter_identifier))) {
                intent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name);
                resolved = true;
                break;
            }
        }
        return resolved;
    }

    private String[] getReceptionName(@StringRes int res) {
        return new String[]{getString(res)};
    }

}
