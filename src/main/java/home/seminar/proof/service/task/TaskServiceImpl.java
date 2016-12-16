package home.seminar.proof.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.seminar.proof.domain.entity.Task;
import home.seminar.proof.domain.entity.User;
import home.seminar.proof.domain.form.TaskForm;
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
		assignee.setId(form.getAssignee().getId());
		task.setAssignee(assignee);
		task.setDescription(form.getDescription());
		task.setDeadline(form.getDeadline());
		repository.save(task);
	}

	@Override
	public List<Task> findByAssignee(Integer assigneeid) {
		return repository.findByAssignee(assigneeid);
	}

}
