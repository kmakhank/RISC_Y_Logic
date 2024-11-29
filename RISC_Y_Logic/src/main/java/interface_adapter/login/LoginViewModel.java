package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {

    public LoginViewModel() {
        super("log in");
        setState(new LoginState());
    }

    public void onLoginSuccess(String username) {
        LoginState state = getState();
        state.setUsername(username);
        state.setMessage("Welcome " + username);
        state.setLoginError(null);
        setState(state);
        this.firePropertyChanged();
    }

    public void onLoginFailure(String error) {
        LoginState state = getState();
        state.setLoginError(error);
        setState(state);
        this.firePropertyChanged();
    }
}
