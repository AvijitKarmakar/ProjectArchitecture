package apps.com.rxapiintegration.feature.shared.presenter;

/**
 * Created by USER on 01-05-2017.
 */

public interface Presenter {
    void onCreate();

    void onPostCreate();

    void onResume();

    void onPostResume();

    void onPause();

    void onDestroy();
}
