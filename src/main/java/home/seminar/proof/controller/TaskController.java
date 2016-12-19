package home.seminar.proof.controller;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import home.seminar.proof.domain.CurrentUser;
import home.seminar.proof.domain.entity.Task;
import home.seminar.proof.domain.entity.User;
import home.seminar.proof.domain.enumeration.Role;
import home.seminar.proof.domain.form.TaskForm;
import home.seminar.proof.domain.form.UserForm;
import home.seminar.proof.service.task.TaskService;
import home.seminar.proof.service.user.UserService;

@Controller
public class TaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProofController.class);
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	
	@InitBinder("form")
    public void initBinder(WebDataBinder binder) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@RequestMapping(value = "/task/create", method = RequestMethod.GET)
	public ModelAndView createTask(HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.info("Redirect to task creation form");
    	TaskForm form = new TaskForm();
    	form.setHeader("TẠO NHIỆM VỤ");
    	form.setAction(request.getContextPath()+"/task/create");
    	List<User> users = (List<User>) userService.findByRole(Role.USER3);
    	form.setAssigneeLst(users);
        return new ModelAndView("task_create", "task", form);
	}
	
	@RequestMapping(value = "/task/edit", method = RequestMethod.GET)
	public ModelAndView redirectEditTaskPage(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) {
    	LOGGER.info("Redirect to task edition form");
    	TaskForm form = taskService.findById(id);
    	form.setHeader("CHỈNH SỬA NHIỆM VỤ");
    	form.setAction(request.getContextPath()+"/task/edit");
        return new ModelAndView("task_create", "task", form);
	}
	
	@RequestMapping(value = "/task/create", method = RequestMethod.POST)
	public String submitCreateTask(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("form") TaskForm form) {
		CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserForm assigner = new UserForm();
		assigner.setId(currentUser.getId());
		form.setAssigner(assigner);
		String decodeDescription = decodeUTF8(form.getDescription().getBytes());
		form.setDescription(decodeDescription);
		taskService.save(form);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/task/edit", method = RequestMethod.POST)
	public String submitEditTask(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("form") TaskForm form) {
		String decode = decodeUTF8(form.getDescription().getBytes());
		form.setDescription(decode);
		taskService.edit(form);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/task/mytask", method = RequestMethod.GET)
	public ModelAndView findMyTask(HttpServletRequest request, HttpServletResponse response) {
		CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Task> tasks = taskService.findByAssignee(currentUser.getId().intValue());
		return new ModelAndView("task_list", "tasks", tasks);
	}
	
	@RequestMapping(value = "/task/list", method = RequestMethod.GET)
	public ModelAndView listTask(HttpServletRequest request, HttpServletResponse response) {
		List<Task> tasks = taskService.findAll();
		return new ModelAndView("task_list", "tasks", tasks);
	}
	
	String decodeUTF8(byte[] bytes) {
	    return new String(bytes, UTF8_CHARSET);
	}

	byte[] encodeUTF8(String string) {
	    return string.getBytes(UTF8_CHARSET);
	}
}
