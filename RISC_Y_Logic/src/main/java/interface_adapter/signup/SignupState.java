package interface_adapter.signup;

public class SignupState {
    private String username = "";
    private String password = "";
    private String usernameError;
    private String message;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsernameError() { return usernameError; }

    public String getMessage() { return message; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
