package projects.hobby.urdufontcomparator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import projects.hobby.urdufontcomparator.BuildConfig;
import projects.hobby.urdufontcomparator.R;

public class SplashActivity extends AppCompatActivity {

    private static final long SCREEN_DELAY_TIME = 3000; //3 seconds

    @BindView(R.id.text_versionname)
    TextView versionName;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mUnbinder = ButterKnife.bind(this);

        if(savedInstanceState == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }, SCREEN_DELAY_TIME);
        }

        versionName.setText(getString(R.string.app_version_name, BuildConfig.VERSION_NAME));
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
