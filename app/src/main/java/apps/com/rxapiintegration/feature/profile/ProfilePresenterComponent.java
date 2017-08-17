package apps.com.rxapiintegration.feature.profile;

import apps.com.rxapiintegration.dependencyinjection.PresenterComponent;
import apps.com.rxapiintegration.feature.shared.dependencyinjection.UserScope;
import dagger.Component;

/**
 * Created by USER on 16-05-2017.
 */

@UserScope
@Component(dependencies = PresenterComponent.class, modules = ProfileModule.class)
interface ProfilePresenterComponent {
    void inject(ProfilePresenter presenter);
}
