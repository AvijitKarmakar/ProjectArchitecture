package apps.com.rxapiintegration.restservice;

import apps.com.rxapiintegration.feature.profile.model.UserDetails;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by USER on 31-03-2017.
 */

public interface RestInterface {

    String BASE_URL= "https://api.github.com/users/";

    @GET("{username}")
    Observable<UserDetails> getUserDetails(@Path("username") String username);

}
