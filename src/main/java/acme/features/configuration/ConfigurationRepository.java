package acme.features.configuration;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ConfigurationRepository extends AbstractRepository{

	@Query("select s from Configuration s")
	Collection<Configuration> findMany();

}
