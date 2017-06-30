package projects.hobby.urdufontcomparator.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import javax.inject.Inject;

import projects.hobby.urdufontcomparator.MainApplication;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.fragments.LicenseFragment;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.tracking.TrackingManager;
import projects.hobby.urdufontcomparator.utils.Utils;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Inject
    UrduTextSource urduTextSource;

    @Inject
    TrackingManager tracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainApplication.get(this).getComponent().inject(this);

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
                tracker.openLicenses();
                Fragment fragment = LicenseFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, fragment)
                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.action_about_dev:
                tracker.openAboutDevs();
                Utils.showDialogWithUrlsWithTitle(this, R.string.menu_about_devs,
                        urduTextSource.prepareDevsInfoDialogText());
                return true;

            case R.id.action_credits:
                tracker.openCredits();
                Utils.showDialogWithUrlsWithTitle(this, R.string.menu_credits,
                        urduTextSource.prepareCreditsDialogText());
                return true;
            case R.id.action_email:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto", getString(R.string.dev_wahib_email), null));
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        getString(R.string.menu_contact_email_subject));

                if (Utils.isIntentSafe(this, emailIntent)) {
                    tracker.sendEmail();
                    startActivity(emailIntent);
                } else {
                    tracker.errorShown();
                    Toast.makeText(this, R.string.email_client_app_not_found,
                            Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.action_tweet:
                Intent twitterIntent = new Intent(Intent.ACTION_SEND);
                twitterIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.dev_wahib_twitter));
                twitterIntent.setType(getString(R.string.intent_type));
                if (Utils.isTwitterInstalled(this, twitterIntent)) {
                    tracker.sendTweet();
                    startActivity(twitterIntent);
                } else {
                    tracker.errorShown();
                    Toast.makeText(this, R.string.twitter_client_app_not_found,
                            Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.action_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType(getString(R.string.intent_type));
                shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_url));
                if (Utils.isIntentSafe(this, shareIntent)) {
                    // TODO: 6/30/2017 add sharing tracking event
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_with_a_friend)));
                } else {
                    // TODO: 6/30/2017 add share error tracking event
                    Toast.makeText(this, R.string.sharing_client_not_found, Toast.LENGTH_SHORT).show();
                }

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
