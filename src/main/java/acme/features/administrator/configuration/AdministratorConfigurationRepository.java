package acme.features.administrator.configuration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorConfigurationRepository extends AbstractRepository {

	/*@Query("select s from Shout s")
	Collection<Shout> findMany();*/
	
	@Query("select c from Configuration c where c.id = ?1")
	Configuration findOneConfigurationById(int id);

}
