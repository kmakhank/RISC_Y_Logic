package use_case.signup;

import entity.User;

public interface SignupDataAccessInterface {

    boolean existByName(String username);

    void save(User user);
}
