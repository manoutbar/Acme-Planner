package acme.features.manager.workPlan;

import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Service;

import acme.entities.tasks.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkPlanShowService implements AbstractShowService<Manager, WorkPlan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkPlanRepository repository;


	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		boolean result;
		int workPlanId;
		WorkPlan workPlan;
		Manager owner;
		Principal principal;

		if (!request.getModel().hasAttribute("id")) {
			return false;
		}

		try {
			workPlanId = request.getModel().getInteger("id");
		} catch (final ConversionFailedException e) {
			return false;
		}
		workPlan = this.repository.findOneWorkPlanById(workPlanId);
		owner = workPlan.getOwner();
		principal = request.getPrincipal();
		result = owner.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		boolean suggestExecutionPeriod = false;
		if (request.getModel().hasAttribute("suggestExecutionPeriod")) {
			suggestExecutionPeriod = request.getModel().getBoolean("suggestExecutionPeriod");
		}
		if (suggestExecutionPeriod) {
			Date suggestExecutionStart = entity.getWorkPlanTask().stream()
				.map(wpt -> wpt.getTask().getExecutionStart())
				.sorted()
				.findFirst()
				.orElse(null);
			Date suggestExecutionEnd = entity.getWorkPlanTask().stream()
				.map(wpt -> wpt.getTask().getExecutionEnd())
				.sorted(Comparator.reverseOrder())
				.findFirst()
				.orElse(null);
			
			if (suggestExecutionStart != null && suggestExecutionEnd != null) {
				suggestExecutionStart = Date.from(suggestExecutionStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().minusDays(1).withHour(8).atZone(ZoneId.systemDefault()).toInstant());
				suggestExecutionEnd = Date.from(suggestExecutionEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(1).withHour(17).atZone(ZoneId.systemDefault()).toInstant());
				entity.setExecutionStart(suggestExecutionStart);
				entity.setExecutionEnd(suggestExecutionEnd);
			}
		}

		request.unbind(entity, model, "isPublic", "executionStart", "executionEnd", "finalMode", "workload", "workPlanTask", "title", "description");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;

		WorkPlan result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkPlanById(id);

		return result;
	}

}
