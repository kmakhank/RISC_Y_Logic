package interface_adapter.signup;

import use_case.SignupInteractor;

public class SignupController {
    private final SignupInteractor sighupInteractor;

    public SignupController(SignupInteractor sighupInteractor) {
        this.sighupInteractor = sighupInteractor;
    }

    public boolean signup(String username, String password) {
        return sighupInteractor.signup(username, password);
    }
}
