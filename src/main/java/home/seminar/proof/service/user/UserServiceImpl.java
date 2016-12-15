package home.seminar.proof.service.user;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import home.seminar.proof.domain.User;
import home.seminar.proof.domain.UserCreateForm;
import home.seminar.proof.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        LOGGER.debug("Getting user by username={}", username);
        return userRepository.findOneByUsername(username);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll(new Sort("id"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setDob(form.getDob());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        user.setFullName(form.getFullName());
        user.setPhone(form.getPhone());
        return userRepository.save(user);
    }

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	@Override
	public User update(UserCreateForm form) {
		User user = userRepository.findOne(form.getId());
		if (!StringUtils.isEmpty(form.getFullName())) {
			user.setFullName(form.getFullName());
		}
		if (!StringUtils.isEmpty(form.getUsername())) {
			user.setUsername(form.getUsername());
		}
		if (!StringUtils.isEmpty(form.getEmail())) {
			user.setEmail(form.getEmail());
		}
		if (!StringUtils.isEmpty(form.getPhone())) {
			user.setPhone(form.getPhone());
		}
		if (!StringUtils.isEmpty(form.getPassword()) && !user.getPassword().equals(form.getPassword())) {
			user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
		}
		if (!StringUtils.isEmpty(form.getRole())) {
			user.setRole(form.getRole());
		}
		if (!StringUtils.isEmpty(form.getDob())) {
			user.setDob(form.getDob());
		}
		return userRepository.save(user);
	}

}
