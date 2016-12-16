package home.seminar.proof.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import home.seminar.proof.domain.CurrentUser;
import home.seminar.proof.domain.form.TaskForm;
import home.seminar.proof.domain.form.UserForm;
import home.seminar.proof.service.task.TaskService;

@Controller
public class TaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProofController.class);
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value = "/task/create", method = RequestMethod.GET)
	public ModelAndView createTask(HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.info("Redirect to task creation form");
    	TaskForm form = new TaskForm();
    	form.setHeader("Task Creation");
    	form.setAction(request.getContextPath()+"/task/create");
        return new ModelAndView("task_create", "task", form);
	}
	
	@RequestMapping(value = "/task/create", method = RequestMethod.POST)
	public String submitCreateTask(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("form") TaskForm form) {
		CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserForm assigner = new UserForm();
		assigner.setId(currentUser.getId());
		form.setAssigner(assigner);
		taskService.save(form);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/task/search", method = RequestMethod.GET)
	public String findMyTask(HttpServletRequest request, HttpServletResponse response) {
		CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		taskService.findByAssignee(currentUser.getId().intValue());
		return "redirect:/home";
	}
	 
}
