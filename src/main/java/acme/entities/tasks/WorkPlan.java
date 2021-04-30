package acme.entities.tasks;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity {

	// Serialisation identifier
	protected static final long serialVersionUID = 1L;
	
	
	// Attributes ---------------------------------
	
	
	@NotNull
	protected Boolean			isPublic;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				executionStart;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				executionEnd;
	
	
	// Derived attributes -----------------------------------------------------


	public Double getWorkload() {
		if (this.tasks == null) {
			return 0.0;
		}
		return this.tasks.stream()
			.mapToDouble(Task::getWorkload)
			.sum();
	}
	
	
	// Relationships ----------------------------------------------------------

	@NotNull
	@ManyToOne(optional = false)
	protected UserAccount		owner;
	
	@NotNull
	@Valid
	@ManyToMany
	protected Collection<Task>	tasks;
}