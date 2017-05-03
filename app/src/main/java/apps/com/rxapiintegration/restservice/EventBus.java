package apps.com.rxapiintegration.restservice;

/**
 * Created by USER on 30-04-2017.
 */

public interface EventBus {
    void subscribe(EventSubscriber subscriber);

    void unsubscribe();

    void onNext(Event event);

    void onError(Event event);

    void onCompletion(Event event);

}
