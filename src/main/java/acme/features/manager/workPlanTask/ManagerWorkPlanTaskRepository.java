package acme.features.manager.workPlanTask;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.entities.tasks.WorkPlan;
import acme.entities.tasks.WorkPlanTask;
import acme.framework.entities.Manager;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanTaskRepository extends AbstractRepository {

	@Query("select wpt from WorkPlanTask wpt join fetch wpt.task t where wpt.workPlan.id = ?1")
	Collection<WorkPlanTask> findManyByWorkPlanId(int workPlanId);

	@Query("select w from WorkPlan w where w.id = ?1 and w.owner.id = ?2")
	WorkPlan findOneWorkPlanByIdAndOwnerId(int workPlanId, int ownerId);

	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);
	
	@Query("select t from Task t where t.executionStart > CURRENT_TIMESTAMP and  t.id not in (select wpt.task.id from WorkPlanTask wpt where wpt.workPlan.id = ?1)")
	Collection<Task> findManyTasks(int id);

	@Query("select t from Task t where t.isPublic = true and t.executionStart > CURRENT_TIMESTAMP and t.id not in (select wpt.task.id from WorkPlanTask wpt where wpt.workPlan.id = ?1)")
	Collection<Task> findManyPublicTasks(int id);
	
	@Query("select t from Task t where t.id = ?1")
	Task findTaskById(int taskId);

	@Query("select wpt from WorkPlanTask wpt left join wpt.task t left join wpt.workPlan w left join w.owner where w.id = ?1 and wpt.id = ?2")
	WorkPlanTask findOneWorkPlanTaskByWorkPlanAndId(int workPlanId, int id);

}
