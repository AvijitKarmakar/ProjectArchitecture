package apps.com.rxapiintegration.feature.profile;

import android.app.Activity;

import apps.com.rxapiintegration.restservice.EventBus;
import dagger.Module;
import dagger.Provides;

/**
 * Created by USER on 16-05-2017.
 */

@Module
class ProfileModule {

    private Activity activity;

    ProfileModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    ProfileRestService getProfileRestService(EventBus eventBus) {
        return new ProfileRestService(activity, eventBus);
    }

}
