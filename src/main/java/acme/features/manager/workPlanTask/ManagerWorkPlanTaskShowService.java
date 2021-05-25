package acme.features.manager.workPlanTask;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import acme.entities.tasks.WorkPlanTask;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkPlanTaskShowService implements AbstractShowService<Manager, WorkPlanTask> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkPlanTaskRepository repository;

	
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

		boolean result;
		Integer workPlanId;
		int workPlanTaskId;
		WorkPlanTask workPlanTask;
		Manager owner;
		Principal principal;


		workPlanId = this.getWorkPlanId(request);
		
		if (workPlanId == null || !request.getModel().hasAttribute("id")) {
			return false;
		}
		
		try {
			workPlanTaskId = request.getModel().getInteger("id");
		} catch (final ConversionFailedException e) {
			return false;
		}
		workPlanTask = this.repository.findOneWorkPlanTaskByWorkPlanAndId(workPlanId, workPlanTaskId);
		
		owner = workPlanTask.getWorkPlan().getOwner();
		principal = request.getPrincipal();
		result = owner.getUserAccount().getId() == principal.getAccountId();
		
		result = result && !workPlanTask.getWorkPlan().isFinalMode();

		return result;
	}

	@Override
	public void unbind(final Request<WorkPlanTask> request, final WorkPlanTask entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "task", "workPlan");
	}

	@Override
	public WorkPlanTask findOne(final Request<WorkPlanTask> request) {
		assert request != null;

		WorkPlanTask result;
		int workPlanId;
		int workPlanTaskId;

		workPlanId = this.getWorkPlanId(request);
		workPlanTaskId = request.getModel().getInteger("id");
		
		result = this.repository.findOneWorkPlanTaskByWorkPlanAndId(workPlanId, workPlanTaskId);
		
		request.getServletRequest().setAttribute("tasks", Arrays.asList(result.getTask()));

		return result;
	}

}
