package acme.features.administrator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorConfigurationCreateService implements AbstractCreateService<Administrator, Configuration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigurationRepository repository;

	// AbstractCreateService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spam", "threshold");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
	}

	@Override
	public Configuration instantiate(final Request<Configuration> request) {
		assert request != null;

		Configuration result;
		

		result = new Configuration();
		

		return result;
	}

	@Override
	public void validate(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;
		
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void create(final Request<Configuration> request, final Configuration entity) {
		assert request != null;
		assert entity != null;

		
		this.repository.save(entity);
	}
}
