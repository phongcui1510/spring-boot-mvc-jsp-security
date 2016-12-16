package home.seminar.proof.domain.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import home.seminar.proof.domain.entity.User;

public class TaskForm {

	private Integer id;
	
	private UserForm assigner;
	
	private UserForm assignee;
	
	private String description;
	private String header;
	private String action;
	
	private Date deadline;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserForm getAssigner() {
		return assigner;
	}

	public void setAssigner(UserForm assigner) {
		this.assigner = assigner;
	}

	public UserForm getAssignee() {
		return assignee;
	}

	public void setAssignee(UserForm assignee) {
		this.assignee = assignee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
}
