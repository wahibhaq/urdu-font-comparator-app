package projects.hobby.urdufontcomparator;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }
}
