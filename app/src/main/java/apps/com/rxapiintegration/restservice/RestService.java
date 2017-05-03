package apps.com.rxapiintegration.restservice;

import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import apps.com.rxapiintegration.feature.profile.model.UserDetails;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by USER on 31-03-2017.
 */

public class RestService {

    private RestInterface restInterface;
    private Context context;
    private EventBus eventBus;

    public RestService(Context context, EventBus eventBus) {
        this.context = context;
        this.eventBus = eventBus;

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(httpLoggingInterceptor);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        restInterface = new Retrofit.Builder()
                .baseUrl(RestInterface.BASE_URL)
                .client(clientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build()
                .create(RestInterface.class);
    }

    public void getUserDetails(String username, int requestCode) {
        restInterface.getUserDetails(username)
                .compose(CustomObservableTransformer.<UserDetails>transformObservable())
                .subscribeWith(new ApiObserver<UserDetails>(eventBus, requestCode));
    }

}
