package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {

	// Serialisation identifier
	protected static final long serialVersionUID = 1L;
	
	// Attributes ---------------------------------
	
	@Length(max=80)
	@NotBlank
	protected String			title;
	
	@NotNull
	protected Boolean			isPublic;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				executionStart;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				executionEnd;
	
	@Digits(integer=2, fraction=2)
	@NotNull
	protected Double			workload;
	
	@Length(max=500)
	@Column(length=500) // field greater than 255 chars
	@NotBlank
	protected String			description;
	
	@URL
	protected String			link;
}
