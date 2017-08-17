package apps.com.rxapiintegration.restservice;

/**
 * Created by USER on 30-04-2017.
 */

class CompletionEvent implements Event {

    private int requestCode;

    CompletionEvent(int requestCode) {
        this.requestCode = requestCode;
    }

    CompletionEvent() {

    }

    @Override
    public int getType() {
        return TYPE_COMPLETION;
    }

    @Override
    public <T> T getResult() {
        return null;
    }

    @Override
    public int getRequestCode() {
        return requestCode;
    }

}
