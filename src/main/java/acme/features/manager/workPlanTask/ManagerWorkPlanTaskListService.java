package acme.features.manager.workPlanTask;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import acme.entities.tasks.WorkPlan;
import acme.entities.tasks.WorkPlanTask;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class ManagerWorkPlanTaskListService implements AbstractListService<Manager, WorkPlanTask> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkPlanTaskRepository repository;

	// AbstractListService<Employer, Job> interface ---------------------------

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
		
		Principal principal;
		Integer workPlanId;
		WorkPlan workPlan;
		
		principal = request.getPrincipal();
		workPlanId = this.getWorkPlanId(request);
		
		workPlan = this.repository.findOneWorkPlanByIdAndOwnerId(workPlanId, principal.getActiveRoleId());
		
		return workPlan != null;
	}

	@Override
	public void unbind(final Request<WorkPlanTask> request, final WorkPlanTask entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "task.title", "task.executionStart", "task.executionEnd");
	}

	@Override
	public Collection<WorkPlanTask> findMany(final Request<WorkPlanTask> request) {
		assert request != null;

		Collection<WorkPlanTask> result;
		Integer workPlanId;

		workPlanId = this.getWorkPlanId(request);
		
		result = this.repository.findManyByWorkPlanId(workPlanId);
		
		request.getServletRequest().setAttribute("workPlanId", workPlanId);
		
		return result;
	}
	
}
