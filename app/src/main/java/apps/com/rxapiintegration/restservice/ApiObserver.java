package apps.com.rxapiintegration.restservice;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by USER on 31-03-2017.
 */

public class ApiObserver<T> extends DisposableObserver<T> {

    private EventBus eventBus;
    private int requestCode;

    public ApiObserver(EventBus eventBus, int requestCode) {
        this.eventBus = eventBus;
        this.requestCode = requestCode;
    }

    @Override
    public void onNext(T t) {
        eventBus.onNext(new SuccessEvent<T>(t, requestCode));
    }

    @Override
    public void onError(Throwable t) {
        eventBus.onError(new ErrorEvent(t, requestCode));
    }

    @Override
    public void onComplete() {
        eventBus.onCompletion(new CompletionEvent(requestCode));
    }

}
