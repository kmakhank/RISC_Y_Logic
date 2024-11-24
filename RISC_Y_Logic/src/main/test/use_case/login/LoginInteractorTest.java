package use_case.login;

import data_access.InMemoryUserRepository;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginInteractorTest {

    @Test
    public void successLoginTest() {
        LoginInputData loginInputData = new LoginInputData("stephenli", "957772");
        InMemoryUserRepository userRepository = new InMemoryUserRepository();

        LoginOutputData loginOutputData = new LoginOutputData("stephenli", false);

        LoginViewModel loginViewModel = new LoginViewModel();

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginViewModel);

        LoginInteractor loginInteractor = new LoginInteractor(userRepository, loginOutputBoundary);

        loginInteractor.execute(loginInputData);

        assertEquals("stephenli", loginOutputData.getUsername());
        assertEquals(false, loginOutputData.getUseCaseFailed());

    }

    @Test
    public void failedLoginTest() {
        LoginInputData loginInputData = new LoginInputData("stephenli", "957773");
        InMemoryUserRepository userRepository = new InMemoryUserRepository();

        LoginOutputData loginOutputData = new LoginOutputData("stephenli", true);

        LoginViewModel loginViewModel = new LoginViewModel();

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginViewModel);

        LoginInteractor loginInteractor = new LoginInteractor(userRepository, loginOutputBoundary);

        loginInteractor.execute(loginInputData);

        assertEquals("stephenli", loginOutputData.getUsername());
        assertEquals(true, loginOutputData.getUseCaseFailed());
    }
}
