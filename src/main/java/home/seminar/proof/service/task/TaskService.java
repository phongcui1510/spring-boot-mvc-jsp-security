package home.seminar.proof.service.task;

import java.util.List;

import home.seminar.proof.domain.entity.Task;
import home.seminar.proof.domain.form.TaskForm;

public interface TaskService {

	public void save(TaskForm task);
	public void edit(TaskForm task);
	public List<Task> findByAssignee(Integer assigneeid);
	public List<Task> findAll();
	public TaskForm findById(Integer id);
}
