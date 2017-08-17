package apps.com.rxapiintegration.feature.profile;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by USER on 19-05-2017.
 */

interface ProfileRestInterface {

    @GET("{username}")
    Observable<UserDetails> getUserDetails(@Path("username") String username);

}
