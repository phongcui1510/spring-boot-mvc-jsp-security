package home.seminar.proof.domain.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import home.seminar.proof.domain.form.UserForm;
import home.seminar.proof.service.user.UserService;

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserForm form = (UserForm) target;
        validatePasswords(errors, form);
        validateUsername(errors, form);
    }

    private void validatePasswords(Errors errors, UserForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateUsername(Errors errors, UserForm form) {
        if (userService.getUserByUsername(form.getUsername()).isPresent()) {
            errors.reject("email.exists", "User with this email already exists");
        }
    }
}
