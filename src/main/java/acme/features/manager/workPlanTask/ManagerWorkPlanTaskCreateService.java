package acme.features.manager.workPlanTask;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.entities.tasks.WorkPlan;
import acme.entities.tasks.WorkPlanTask;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.services.AbstractCreateService;
import acme.util.Utils;

@Service
public class ManagerWorkPlanTaskCreateService implements AbstractCreateService<Manager, WorkPlanTask> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkPlanTaskRepository repository;
	

	@Autowired
	protected Utils utils;

	@Override
	public boolean authorise(final Request<WorkPlanTask> request) {
		assert request != null;

		final WorkPlan workPlan;
		
		workPlan = this.repository.findOneWorkPlanByIdAndOwnerId(
			request.getModel().getInteger("workPlanId"),
			request.getPrincipal().getActiveRoleId()
		);
		
		return workPlan != null && !workPlan.isFinalMode();
	}

	@Override
	public void validate(final Request<WorkPlanTask> request, final WorkPlanTask entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void bind(final Request<WorkPlanTask> request, final WorkPlanTask entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<WorkPlanTask> request, final WorkPlanTask entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "task");
	}

	@Override
	public WorkPlanTask instantiate(final Request<WorkPlanTask> request) {
		assert request != null;

		final WorkPlanTask result;
		final WorkPlan workPlan;
		final Integer workPlanId = request.getModel().getInteger("workPlanId");
		
		Collection<Task> tasks;
		
		workPlan = this.repository.findOneWorkPlanByIdAndOwnerId(
			workPlanId,
			request.getPrincipal().getActiveRoleId()
		);
		
		result = new WorkPlanTask();
		result.setWorkPlan(workPlan);
		
		if(request.getModel().hasAttribute("task")) {
			result.setTask(this.repository.findTaskById(request.getModel().getInteger("task")));		
		}
		if (workPlan.getIsPublic().booleanValue()) {
			tasks = this.repository.findManyPublicTasks(); 
		} else {
			tasks = this.repository.findManyTasks();
		}
		 
		request.getServletRequest().setAttribute("tasks", tasks);
		request.getServletRequest().setAttribute("workPlanId", workPlanId);
		
		return result;
	}

	@Override
	public void create(final Request<WorkPlanTask> request, final WorkPlanTask entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
