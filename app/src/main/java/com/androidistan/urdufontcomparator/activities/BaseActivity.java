package com.androidistan.urdufontcomparator.activities;

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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.androidistan.urdufontcomparator.MainApplication;
import com.androidistan.urdufontcomparator.R;
import com.androidistan.urdufontcomparator.fragments.LicenseFragment;
import com.androidistan.urdufontcomparator.models.UrduTextSource;
import com.androidistan.urdufontcomparator.tracking.TrackingManager;
import com.androidistan.urdufontcomparator.utils.Utils;

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

            case R.id.action_about_us:
                tracker.openAboutUs();
                Utils.showDialogWithUrlsWithTitle(this, R.string.menu_about_us, prepareAboutUsDialogText());
                return true;

            case R.id.action_credits:
                tracker.openCredits();
                Utils.showDialogWithUrlsWithTitle(this, R.string.menu_credits, prepareCreditsDialogText());
                return true;
            case R.id.action_email:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto", getString(R.string.brand_email), null));
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
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        String.format(getString(R.string.share_app_url_message),
                        getString(R.string.app_name_expanded), getString(R.string.app_url)));
                if (Utils.isIntentSafe(this, shareIntent)) {
                    tracker.shareAppWithFriend();
                    startActivity(Intent.createChooser(shareIntent,
                            getString(R.string.menu_share_with_a_friend)));
                } else {
                    tracker.errorShown();
                    Toast.makeText(this, R.string.sharing_client_not_found,
                            Toast.LENGTH_SHORT).show();
                }

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private String prepareAboutUsDialogText() {
        return  Utils.getLineSpacings(this)
                .concat(getString(R.string.project_description))
                .concat(Utils.getLineSpacingsWithDash(this))
                .concat(Utils.getLineSpacings(this))
                .concat(getString(R.string.project_blog_page_mention,
                        getString(R.string.app_blog_page)))
                .concat(Utils.getLineSpacings(this))
                .concat(Utils.getLineSpacings(this))
                .concat(getString(R.string.project_github_comment,
                        getString(R.string.app_github_repo_url)));
    }

    private String prepareCreditsDialogText() {
        return Utils.getLineSpacings(this)
                .concat(getString(R.string.credits_text,
                        getString(R.string.app_github_credits_url)));
    }

}
