package apps.com.rxapiintegration.feature.profile.presenter;

import android.util.Log;

import javax.inject.Inject;

import apps.com.rxapiintegration.constants.Constants;
import apps.com.rxapiintegration.feature.shared.presenter.PresenterStub;
import apps.com.rxapiintegration.feature.profile.model.UserDetails;
import apps.com.rxapiintegration.restservice.Event;
import apps.com.rxapiintegration.restservice.EventBus;
import apps.com.rxapiintegration.restservice.EventSubscriber;
import apps.com.rxapiintegration.restservice.RestService;

/**
 * Created by USER on 01-05-2017.
 */

public class UsernamePresenter extends PresenterStub implements EventSubscriber {

    @Inject
    RestService restService;

    @Inject
    EventBus eventBus;

    private IUsernameView iUsernameView;

    public UsernamePresenter(IUsernameView iUsernameView) {
        this.iUsernameView = iUsernameView;
    }

    @Override
    public void onPostCreate() {
        eventBus.subscribe(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unsubscribe();
    }

    public void onSearchBtnClick(String username) {
        iUsernameView.showProgressDialog();
        restService.getUserDetails(username, Constants.USER_DETAILS_REQ_CODE);
    }

    @Override
    public void onEvent(Event event) {
        switch (event.getType()) {
            case Event.TYPE_SUCCESS:
                Log.e("onEvent: ", "success");
                if (event.getRequestCode() == Constants.USER_DETAILS_REQ_CODE) {
                    UserDetails userDetails = event.getResult();
                    iUsernameView.setName(userDetails.getName());
                    iUsernameView.setLocation(userDetails.getLocation());
                }
                break;

            case Event.TYPE_ERROR:
                Log.e("onEvent: ", "error");
                if (event.getRequestCode() == Constants.USER_DETAILS_REQ_CODE) {
                    iUsernameView.dismissProgressDialog();
                }
                break;

            case Event.TYPE_COMPLETION:
                Log.e("onEvent: ", "completion");
                if (event.getRequestCode() == Constants.USER_DETAILS_REQ_CODE) {
                    iUsernameView.dismissProgressDialog();
                }
                break;
        }
    }

    public interface IUsernameView {
        void setName(String name);

        void setLocation(String location);

        void showProgressDialog();

        void dismissProgressDialog();
    }

}
