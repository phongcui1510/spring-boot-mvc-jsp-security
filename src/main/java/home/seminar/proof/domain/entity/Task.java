package home.seminar.proof.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, updatable = false)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="assigner")
	private User assigner;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="assignee")
	private User assignee;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "deadline")
	private Date deadline;
	
	public String getStatus() {
		return status;
	}
	public String getStatusDisplay() {
		if (this.status != null && this.status.equalsIgnoreCase("NEW")) {
			return "TẠO MỚI";
		} else if (this.status != null && this.status.equalsIgnoreCase("INPROGRESS")) {
			return "ĐANG LÀM";
		} else if (this.status != null && this.status.equalsIgnoreCase("DONE")) {
			return "HOÀN TẤT";
		}
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getAssigner() {
		return assigner;
	}
	public void setAssigner(User assigner) {
		this.assigner = assigner;
	}
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
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
