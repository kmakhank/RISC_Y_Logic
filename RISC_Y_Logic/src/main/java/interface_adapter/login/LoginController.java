package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInteractor;

public class LoginController {
    private final LoginInteractor loginInteractor;

    public LoginController(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    public void login(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);
        loginInteractor.execute(loginInputData);
    }
}
