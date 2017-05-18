package apps.com.rxapiintegration.feature.shared.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import apps.com.rxapiintegration.dependencyinjection.DaggerPresenterComponent;
import apps.com.rxapiintegration.dependencyinjection.PresenterComponent;
import apps.com.rxapiintegration.dependencyinjection.PresenterModule;
import apps.com.rxapiintegration.feature.shared.presenter.Presenter;

/**
 * Created by USER on 01-05-2017.
 */

public abstract class PresentedActivity<T extends Presenter> extends AppCompatActivity {

    private T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        presenter.onCreate();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.onPostCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        presenter.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void inject() {
        presenter = onCreatePresenter();

        PresenterComponent presenterComponent = DaggerPresenterComponent.builder()
                .presenterModule(new PresenterModule(this))
                .build();

        injectPresenter(presenterComponent, presenter);
    }

    protected abstract T onCreatePresenter();

    protected abstract void injectPresenter(PresenterComponent presenterComponent, T presenter);

}
