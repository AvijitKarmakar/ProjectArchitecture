package apps.com.rxapiintegration.feature.profile;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import apps.com.rxapiintegration.R;
import apps.com.rxapiintegration.databinding.ActivityUsernameBinding;
import apps.com.rxapiintegration.dependencyinjection.PresenterComponent;
import apps.com.rxapiintegration.feature.shared.view.activity.PresentedActivity;

public class ProfileActivity extends PresentedActivity<ProfilePresenter>
        implements ProfilePresenter.IProfileView, View.OnClickListener {

    private ProfilePresenter presenter;
    private ActivityUsernameBinding binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_username);

        binding.searchBtn.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    @Override
    protected ProfilePresenter onCreatePresenter() {
        presenter = new ProfilePresenter(this);
        return presenter;
    }

    @Override
    protected void injectPresenter(PresenterComponent presenterComponent, ProfilePresenter presenter) {
        ProfilePresenterComponent profilePresenterComponent = DaggerProfilePresenterComponent.builder()
                .presenterComponent(presenterComponent)
                .profileModule(new ProfileModule(this))
                .build();

        profilePresenterComponent.inject(presenter);
    }

    @Override
    public void setName(final String name) {
        binding.nameTxt.setText(name);
    }

    @Override
    public void setLocation(String location) {
        binding.locationTxt.setText(location);
    }

    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Fetching profile data..");
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_btn) {
            presenter.onSearchBtnClick(binding.githubUsernameTxt.getText().toString());
        }
    }

}
