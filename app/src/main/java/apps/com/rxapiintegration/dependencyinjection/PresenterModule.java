package apps.com.rxapiintegration.dependencyinjection;

import android.app.Activity;

import javax.inject.Singleton;

import apps.com.rxapiintegration.restservice.EventBus;
import apps.com.rxapiintegration.restservice.RestService;
import apps.com.rxapiintegration.restservice.RxBus;
import dagger.Module;
import dagger.Provides;

/**
 * Created by USER on 01-05-2017.
 */

@Module
public class PresenterModule {

    private Activity activity;

    public PresenterModule(Activity activity) {
        this.activity = activity;
    }

    @Singleton
    @Provides
    EventBus getEventBus() {
        return new RxBus();
    }

    @Provides
    RestService getRestService(EventBus eventBus) {
        return new RestService(activity, eventBus);
    }

}
