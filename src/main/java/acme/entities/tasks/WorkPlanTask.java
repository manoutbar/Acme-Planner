package acme.entities.tasks;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlanTask extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne(optional = false)
	protected WorkPlan workPlan;
	
	@ManyToOne(optional = false)
	protected Task task;

}
