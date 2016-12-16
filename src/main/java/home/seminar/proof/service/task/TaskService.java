package home.seminar.proof.service.task;

import java.util.List;

import home.seminar.proof.domain.entity.Task;
import home.seminar.proof.domain.form.TaskForm;

public interface TaskService {

	public void save(TaskForm task);
	public List<Task> findByAssignee(Integer assigneeid);
}
