package apps.com.rxapiintegration.restservice;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by USER on 30-04-2017.
 */

public class RxBus implements EventBus {

    private final Subject<Event> subject = PublishSubject.create();
    private final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void subscribe(final EventSubscriber subscriber) {
        subject.subscribe(new Observer<Event>() {

            @Override
            public void onSubscribe(Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(Event event) {
                try {
                    subscriber.onEvent(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                subscriber.onEvent(new ErrorEvent(e));
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                subscriber.onEvent(new CompletionEvent());
            }
        });
    }

    @Override
    public void unsubscribe() {
        disposable.dispose();
    }

    @Override
    public void onNext(Event event) {
        try {
            subject.onNext(event);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void onError(Event event) {
        subject.onNext(event);
    }

    @Override
    public void onCompletion(Event event) {
        subject.onNext(event);
    }

}
