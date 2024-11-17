package interface_adapter.login;

import use_case.LoginInteractor;

public class LoginController {
    private final LoginInteractor loginInteractor;

    public LoginController(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    public boolean login(String username, String password) {
        return loginInteractor.login(username, password);
    }
}
