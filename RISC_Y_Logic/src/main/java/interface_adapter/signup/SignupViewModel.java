package interface_adapter.signup;

import interface_adapter.ViewModel;

public class SignupViewModel extends ViewModel<SignupState> {
    public SignupViewModel() {
        super("signup");
        setState(new SignupState());
    }

    public void onSignupSuccess(String username) {
        SignupState state = getState();
        state.setUsername(username);
        state.setMessage("Signup successful! Welcome, " + username);
        setState(state);
        this.firePropertyChanged();
    }

    public void onSignupFailure(String errorMessage) {
        SignupState state = getState();
        state.setMessage(errorMessage);
        setState(state);
        this.firePropertyChanged();
    }
}
