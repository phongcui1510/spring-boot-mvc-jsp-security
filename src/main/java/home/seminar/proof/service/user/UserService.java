package home.seminar.proof.service.user;

import java.util.Collection;
import java.util.Optional;

import home.seminar.proof.domain.User;
import home.seminar.proof.domain.UserCreateForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByUsername(String username);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
    
    void deleteUser(Long id);

}
