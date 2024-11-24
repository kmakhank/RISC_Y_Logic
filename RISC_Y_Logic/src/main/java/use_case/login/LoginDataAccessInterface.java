package use_case.login;

import entity.User;

public interface LoginDataAccessInterface {
    User findByName(String username);

    void save(User user);
}
