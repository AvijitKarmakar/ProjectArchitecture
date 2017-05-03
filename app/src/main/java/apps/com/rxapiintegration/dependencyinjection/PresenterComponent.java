package apps.com.rxapiintegration.dependencyinjection;

import javax.inject.Singleton;

import apps.com.rxapiintegration.feature.profile.presenter.UsernamePresenter;
import dagger.Component;

/**
 * Created by USER on 01-05-2017.
 */

@Singleton
@Component(modules = {PresenterModule.class})
public interface PresenterComponent {
    void inject(UsernamePresenter usernamePresenter);
}
