package use_case.login;

import data_access.UserRepository;
import entity.User;

public class LoginInteractor {
    private final UserRepository userRepository;

    public LoginInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
