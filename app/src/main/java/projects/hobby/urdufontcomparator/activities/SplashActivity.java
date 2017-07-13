package projects.hobby.urdufontcomparator.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import projects.hobby.urdufontcomparator.R;

public class SplashActivity extends AppCompatActivity {

    private static final long SCREEN_DELAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, SCREEN_DELAY_TIME);
    }
}
