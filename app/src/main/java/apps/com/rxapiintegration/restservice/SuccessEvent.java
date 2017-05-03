package apps.com.rxapiintegration.restservice;

/**
 * Created by USER on 28-04-2017.
 */

public class SuccessEvent<T> implements Event {

    private T result;
    private int requestCode;

    public SuccessEvent(T result, int requestCode) {
        this.result = result;
        this.requestCode = requestCode;
    }

    @Override
    public int getType() {
        return TYPE_SUCCESS;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getResult() {
        return (T) result;
    }

    @Override
    public int getRequestCode() {
        return requestCode;
    }

}
