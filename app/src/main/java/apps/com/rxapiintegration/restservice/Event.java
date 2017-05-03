package apps.com.rxapiintegration.restservice;

/**
 * Created by USER on 28-04-2017.
 */

public interface Event {
    int TYPE_SUCCESS = 1;
    int TYPE_ERROR = 2;
    int TYPE_COMPLETION = 3;

    int getType();

    <T> T getResult();

    int getRequestCode();
}
