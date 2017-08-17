package apps.com.rxapiintegration.feature.profile;

import android.content.Context;

import apps.com.rxapiintegration.restservice.ApiObserver;
import apps.com.rxapiintegration.restservice.CustomObservableTransformer;
import apps.com.rxapiintegration.restservice.EventBus;
import apps.com.rxapiintegration.restservice.RestService;

/**
 * Created by USER on 18-08-2017.
 */

class ProfileRestService extends RestService {

    private ProfileRestInterface restInterface;

    ProfileRestService(Context context, EventBus eventBus) {
        super(context, eventBus);
        restInterface = retrofit.create(ProfileRestInterface.class);
    }

    void getUserDetails(String username, int requestCode) {
        restInterface.getUserDetails(username)
                .compose(CustomObservableTransformer.<UserDetails>transformObservable())
                .subscribeWith(new ApiObserver<UserDetails>(eventBus, requestCode));
    }

}
