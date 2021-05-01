package acme.features.anonymous.configuration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.features.configuration.ConfigurationRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousConfigurationListService  implements AbstractListService<Anonymous, Configuration> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ConfigurationRepository repository;

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
		}

		@Override
		public Collection<Configuration> findMany(final Request<Configuration> request) {
			assert request != null;

			Collection<Configuration> result;

			result = this.repository.findMany();

			return result;
		}
}
