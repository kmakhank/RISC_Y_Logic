package use_case.signup;

import data_access.InMemoryUserRepository;
import entity.User;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SignupInteractorTest {

    @Test
    public void testSuccessfulSignup() {
        SignupInputData signupInputData = new SignupInputData("stephencurry", "301123");
        InMemoryUserRepository userRepository = new InMemoryUserRepository();

        userRepository.save(new User(signupInputData.getUsername(), signupInputData.getPassword()));

        SignupOutputData signupOutputData = new SignupOutputData("stephencurry", false);

        SignupViewModel signupViewModel = new SignupViewModel();

        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(signupViewModel);

        SignupInteractor signupInteractor = new SignupInteractor(userRepository, signupOutputBoundary);

        signupInteractor.execute(signupInputData);

        assertEquals("stephencurry", signupOutputData.getUsername());
        assertEquals(false, signupOutputData.isUseCaseFailed());
    }

    @Test
    public void testFailedSignup() {
        SignupInputData signupInputData = new SignupInputData("stephenli", "301123");
        InMemoryUserRepository userRepository = new InMemoryUserRepository();

        userRepository.save(new User(signupInputData.getUsername(), signupInputData.getPassword()));

        SignupOutputData signupOutputData = new SignupOutputData("stephenli", true);

        SignupViewModel signupViewModel = new SignupViewModel();

        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(signupViewModel);

        SignupInteractor signupInteractor = new SignupInteractor(userRepository, signupOutputBoundary);

        signupInteractor.execute(signupInputData);

        assertEquals("stephenli", signupOutputData.getUsername());
        assertEquals(true, signupOutputData.isUseCaseFailed());
    }
}
