package data_access;

import entity.User;
import use_case.login.LoginDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements LoginDataAccessInterface,
                                       SignupDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();

    public InMemoryUserRepository() {
        save(new User("stephenli", "957772"));
        save(new User("justintrudeau", "7853"));
    }

    @Override
    public User findByName(String username) {
        return users.get(username);
    }

    @Override
    public boolean existByName(String username) {
        return users.containsKey(username);
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }
}
