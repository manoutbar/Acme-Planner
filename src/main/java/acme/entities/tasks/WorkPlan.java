package acme.entities.tasks;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.Manager;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity {

	// Serialisation identifier
	protected static final long serialVersionUID = 1L;
	
	
	// Attributes ---------------------------------
	
	@Length(max=80)
	@NotBlank
	protected String 			title;
	
	@Length(max=500)
	@Column(length=500) // field greater than 255 chars
	@NotBlank
	protected String			description;
	
	@NotNull
	protected Boolean			isPublic;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				executionStart;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				executionEnd;
	
	protected boolean			finalMode;
	
	
	// Derived attributes -----------------------------------------------------


	public Double getWorkload() {
		if (this.workPlanTask == null || this.workPlanTask.isEmpty()) {
			return 0.0;
		}
		return this.workPlanTask.stream()
			.map(WorkPlanTask::getTask)
			.mapToDouble(Task::getWorkload)
			.sum();
	}
	
	
	// Relationships ----------------------------------------------------------
	
	@Valid
	@ManyToOne(optional = false)
	protected Manager owner;
	
	@Valid
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "workPlan")
	protected Collection<WorkPlanTask> workPlanTask;
}
