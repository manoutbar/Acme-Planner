package acme.features.administrator.configuration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.features.configuration.ConfigurationRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorConfigurationListService implements AbstractListService<Administrator, Configuration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ConfigurationRepository repository;


	// AbstractListService<Administrator, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spam", "threshold");
	}

	@Override
	public Collection<Configuration> findMany(final Request<Configuration> request) {
		assert request != null;

		Collection<Configuration> result;

		result = this.repository.findMany();

		return result;
	}

}
