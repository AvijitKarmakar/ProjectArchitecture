package apps.com.rxapiintegration.feature.profile;

import android.util.Log;

import javax.inject.Inject;

import apps.com.rxapiintegration.constants.Constants;
import apps.com.rxapiintegration.feature.shared.presenter.PresenterStub;
import apps.com.rxapiintegration.restservice.Event;
import apps.com.rxapiintegration.restservice.EventBus;
import apps.com.rxapiintegration.restservice.EventSubscriber;

/**
 * Created by USER on 01-05-2017.
 */

class ProfilePresenter extends PresenterStub implements EventSubscriber {

    @Inject
    ProfileRestService restService;

    @Inject
    EventBus eventBus;

    private IProfileView iProfileView;

    ProfilePresenter(IProfileView iProfileView) {
        this.iProfileView = iProfileView;
    }

    @Override
    public void onPostCreate() {
        eventBus.subscribe(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unsubscribe();
    }

    void onSearchBtnClick(String username) {
        iProfileView.showProgressDialog();
        restService.getUserDetails(username, Constants.USER_DETAILS_REQ_CODE);
    }

    @Override
    public void onEvent(Event event) {
        switch (event.getType()) {
            case Event.TYPE_SUCCESS:
                Log.e("onEvent: ", "success");
                if (event.getRequestCode() == Constants.USER_DETAILS_REQ_CODE) {
                    UserDetails userDetails = event.getResult();
                    iProfileView.setName(userDetails.getName());
                    iProfileView.setLocation(userDetails.getLocation());
                }
                break;

            case Event.TYPE_ERROR:
                Log.e("onEvent: ", "error");
                if (event.getRequestCode() == Constants.USER_DETAILS_REQ_CODE) {
                    iProfileView.dismissProgressDialog();
                }
                break;

            case Event.TYPE_COMPLETION:
                Log.e("onEvent: ", "completion");
                if (event.getRequestCode() == Constants.USER_DETAILS_REQ_CODE) {
                    iProfileView.dismissProgressDialog();
                }
                break;
        }
    }

    interface IProfileView {
        void setName(String name);

        void setLocation(String location);

        void showProgressDialog();

        void dismissProgressDialog();
    }

}
