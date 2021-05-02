package acme.entities.configuration;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	protected String spam;
	
	@NotNull
	@Range(min = 0, max = 100)
	protected Double threshold;
	
	
	public List<String> getSpamList() {
		return Arrays.asList(this.spam.split(";"));
	}
	

}
