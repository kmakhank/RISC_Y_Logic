package interface_adapter.signup;

public class SignupPresenter {
    public String prepareSuccessMessage() {
        return "Signup successful!";
    }

    public String prepareFailureMessage() {
        return "Username already exists.";
    }
}