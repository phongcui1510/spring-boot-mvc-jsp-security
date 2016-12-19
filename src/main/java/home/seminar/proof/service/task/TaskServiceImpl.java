package home.seminar.proof.service.task;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import home.seminar.proof.domain.entity.Task;
import home.seminar.proof.domain.entity.User;
import home.seminar.proof.domain.form.TaskForm;
import home.seminar.proof.domain.form.UserForm;
import home.seminar.proof.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository repository;
	
	@Override
	public void save(TaskForm form) {
		Task task = new Task();
		User assigner = new User();
		assigner.setId(form.getAssigner().getId());
		task.setAssigner(assigner);
		User assignee = new User();
		assignee.setId((long)form.getAssigneeid());
		task.setAssignee(assignee);
		task.setDescription(form.getDescription());
		task.setDeadline(form.getDeadline());
		task.setStatus(form.getStatus());
		repository.save(task);
	}

	@Override
	public List<Task> findByAssignee(Integer assigneeid) {
		User user = new User();
		user.setId(assigneeid.longValue());
		return repository.findByAssignee(user);
	}

	@Override
	public List<Task> findAll() {
		return repository.findAll();
	}

	@Override
	public TaskForm findById(Integer id) {
		TaskForm form = new TaskForm();
		Task task = repository.findOne(id);
		form.setId(task.getId());
		form.setDeadline(task.getDeadline());
		form.setDescription(task.getDescription());
		form.setStatus(task.getStatus());
		User assigner = task.getAssigner();
		User assignee = task.getAssignee();
		UserForm assignerForm = form.getAssigner();
		assignerForm.setId(assigner.getId());
		assignerForm.setUsername(assigner.getUsername());
		UserForm assigneeForm = form.getAssignee();
		assigneeForm.setId(assignee.getId());
		assigneeForm.setUsername(assignee.getUsername());
		return form;
	}

	@Override
	public void edit(TaskForm form) {
		Task task = repository.findOne(form.getId());
		if (!StringUtils.isEmpty(form.getStatus())) {
			task.setStatus(form.getStatus());
		}
		if (!StringUtils.isEmpty(form.getStatus())) {
			task.setStatus(form.getStatus());
		}
		if (!StringUtils.isEmpty(form.getDescription())) {
			task.setDescription(form.getDescription());
		}
		repository.save(task);
	}

}
