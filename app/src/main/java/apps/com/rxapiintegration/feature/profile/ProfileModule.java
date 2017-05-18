package apps.com.rxapiintegration.feature.profile;

import android.app.Activity;

import dagger.Module;

/**
 * Created by USER on 16-05-2017.
 */

@Module
public class ProfileModule {

    private Activity activity;

    public ProfileModule(Activity activity) {
        this.activity = activity;
    }

}
