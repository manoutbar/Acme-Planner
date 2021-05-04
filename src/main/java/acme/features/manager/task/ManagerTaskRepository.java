package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.entities.tasks.WorkPlanTask;
import acme.framework.entities.Manager;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {

	@Query("select t from Task t where t.owner.id = ?1")
	Collection<Task> findManyByManagerId(int ownerId);

	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);
	
	@Query("select wpt from WorkPlanTask wpt where wpt.task.id = ?1")
	Collection<WorkPlanTask> findWorkPlanTaskByTaskId(int id);

}
