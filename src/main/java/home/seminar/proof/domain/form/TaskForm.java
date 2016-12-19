package home.seminar.proof.domain.form;

import java.util.Date;
import java.util.List;

import home.seminar.proof.domain.entity.User;

public class TaskForm {

	private Integer id;
	
	private UserForm assigner;
	private Long assigneeid;
	private Long assignerid;
	private UserForm assignee;
	
	private String description;
	private String status;
	private String header;
	private String action;
	
	private Date deadline;

	private List<User> assigneeLst;
	
	public List<User> getAssigneeLst() {
		return assigneeLst;
	}


	public void setAssigneeLst(List<User> assigneeLst) {
		this.assigneeLst = assigneeLst;
	}


	public Long getAssigneeid() {
		return assigneeid;
	}

	
	public Long getAssignerid() {
		return assignerid;
	}



	public void setAssignerid(Long assignerid) {
		this.assignerid = assignerid;
	}



	public void setAssigneeid(Long assigneeid) {
		this.assigneeid = assigneeid;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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
		if (this.assigner == null) {
			this.assigner = new UserForm();
		}
		return assigner;
	}

	public void setAssigner(UserForm assigner) {
		this.assigner = assigner;
	}

	public UserForm getAssignee() {
		if (this.assignee == null) {
			this.assignee = new UserForm();
		}
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
