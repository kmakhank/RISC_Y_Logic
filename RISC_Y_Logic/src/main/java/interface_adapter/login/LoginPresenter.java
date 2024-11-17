package interface_adapter.login;

public class LoginPresenter {
    public String prepareSuccessMessage() {
        return "Login successful!";
    }

    public String prepareFailureMessage() {
        return "Invalid username or password.";
    }
}
