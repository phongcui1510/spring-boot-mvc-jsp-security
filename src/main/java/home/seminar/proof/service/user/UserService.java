package home.seminar.proof.service.user;

import java.util.Collection;
import java.util.Optional;

import home.seminar.proof.domain.entity.User;
import home.seminar.proof.domain.form.UserForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByUsername(String username);

    Collection<User> getAllUsers();

    User create(UserForm form);
    
    void deleteUser(Long id);
    
    User update(UserForm form);

}
