package interface_adapter.signup;

import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel viewModel;

    public SignupPresenter(SignupViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void prepareMessage(SignupOutputData signupOutputData) {
        if (signupOutputData.isUseCaseFailed()) {
            viewModel.onSignupFailure("User name already taken");
        }
        else {
            viewModel.onSignupSuccess(signupOutputData.getUsername());
        }
    }
}
