package home.seminar.proof.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import home.seminar.proof.domain.User;
import home.seminar.proof.domain.UserCreateForm;
import home.seminar.proof.domain.validator.UserCreateFormValidator;
import home.seminar.proof.service.user.UserService;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        LOGGER.debug("Getting user page for user={}", id);
        return new ModelAndView("user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Getting user create form");
        UserCreateForm form = new UserCreateForm();
        form.setAction(request.getContextPath()+"/user/create");
        form.setHeader("THÊM TÀI KHOẢN");
        return new ModelAndView("user_create", "user", form);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    public ModelAndView getUserEditPage(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Long id) {
    	Optional<User> user = userService.getUserById(id);
    	UserCreateForm form = new UserCreateForm();
    	form.setHeader("CHỈNH SỬA TÀI KHOẢN");
    	if (user.isPresent()) {
    		BeanUtils.copyProperties(user.get(), form);
    		form.setAction(request.getContextPath()+"/user/edit");
    	}
        return new ModelAndView("user_create", "user", form);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public String editUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("form") UserCreateForm form) {
    	try {
    		userService.update(form);
    	} catch (Exception e) {
    		LOGGER.error("Error when updating user", e);
    	}
        return "redirect:/home";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("id") Long id) {
    	userService.deleteUser(id);
    	List<User> users = (List<User>) userService.getAllUsers();
        return new ModelAndView("user_list", "users", users);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public ModelAndView listUser(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Getting all users");
        List<User> users = (List<User>) userService.getAllUsers();
        return new ModelAndView("user_list", "users", users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.info("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("username.exists", "Username already exists");
            return "user_create";
        }
        // ok, redirect
        return "redirect:/home";
    }

}
