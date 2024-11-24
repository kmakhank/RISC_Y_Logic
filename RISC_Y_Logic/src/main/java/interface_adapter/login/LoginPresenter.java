package interface_adapter.login;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel viewModel;

    public LoginPresenter(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareMessage(LoginOutputData loginOutputData) {
        if (loginOutputData.getUseCaseFailed()) {
            viewModel.onLoginFailure("Invalid username or password.");
        } else {
            viewModel.onLoginSuccess(loginOutputData.getUsername());
        }
    }
}
