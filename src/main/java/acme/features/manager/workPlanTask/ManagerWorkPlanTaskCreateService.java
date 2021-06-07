package acme.features.manager.workPlanTask;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

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

	private Integer getWorkPlanId(final Request<?> request) {
		@SuppressWarnings("unchecked")
		final Map<String, String> pathVariables = (Map<String, String>) request.getServletRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		final String str = pathVariables.get("workPlanId");
		if (str != null) {
			try {
				return new Integer(str);
			} catch (final Exception ex) {
				// ignored exception
			}
		}
		return null;
	}
	
	@Override
	public boolean authorise(final Request<WorkPlanTask> request) {
		assert request != null;

		final WorkPlan workPlan;
		
		workPlan = this.repository.findOneWorkPlanByIdAndOwnerId(
			this.getWorkPlanId(request),
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

		request.unbind(entity, model, "task", "workPlan");
	}

	@Override
	public WorkPlanTask instantiate(final Request<WorkPlanTask> request) {
		assert request != null;

		final WorkPlanTask result;
		final WorkPlan workPlan;
		final Integer workPlanId = this.getWorkPlanId(request);
		
		final Collection<Task> tasks;
		
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
			tasks = this.repository.findManyPublicTasks(workPlan.getId()); 
		} else {
			tasks = this.repository.findManyTasks(workPlan.getId());
		}
		
		request.getServletRequest().setAttribute("tasks", tasks);
		// request.getServletRequest().setAttribute("workPlanId", workPlanId);
		
		return result;
	}

	@Override
	public void create(final Request<WorkPlanTask> request, final WorkPlanTask entity) {
		assert request != null;
		assert entity != null;
		
		final Date executionStart = entity.getWorkPlan().getExecutionStart();
		final Date executionEnd = entity.getWorkPlan().getExecutionEnd();
		
		final Task task = this.repository.findTaskById(entity.getTask().getId());
		
		final Date taskExecutionStart = task.getExecutionStart();
		final Date taskExecutionEnd = task.getExecutionEnd();
		
		boolean updateWorkPlan = false;
		if (!executionStart.before(taskExecutionStart)) {
			updateWorkPlan = true;
			entity.getWorkPlan().setExecutionStart(taskExecutionStart);
		}
		
		if (!executionEnd.after(taskExecutionEnd)) {
			updateWorkPlan = true;
			entity.getWorkPlan().setExecutionEnd(taskExecutionEnd);
		}
		
		if (updateWorkPlan) {
			this.repository.save(entity.getWorkPlan());
		}
		
		this.repository.save(entity);
	}

}
