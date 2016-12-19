package home.seminar.proof.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import home.seminar.proof.domain.entity.Task;
import home.seminar.proof.domain.entity.User;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByAssignee(User id);
}
