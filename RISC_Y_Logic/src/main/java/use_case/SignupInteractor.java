package use_case;

import data_access.UserRepository;
import entity.User;

public class SignupInteractor {
    public final UserRepository userRepository;

    public SignupInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signup(String username, String password) {
        User newUser = new User(username, password);
        return userRepository.save(newUser);
    }
}
