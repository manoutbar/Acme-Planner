package acme.features.manager.workPlan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class ManagerWorkPlanListMineService implements AbstractListService<Manager, WorkPlan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkPlanRepository repository;

	// AbstractListService<Employer, Job> interface ---------------------------


	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "finalMode", "title", "isPublic", "executionStart", "executionEnd");
	}

	@Override
	public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
		assert request != null;

		Collection<WorkPlan> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findManyByManagerId(principal.getActiveRoleId());

		return result;
	}
}
