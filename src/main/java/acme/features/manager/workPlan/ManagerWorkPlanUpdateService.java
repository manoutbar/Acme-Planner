package acme.features.manager.workPlan;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.WorkPlan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.util.Utils;

@Service
public class ManagerWorkPlanUpdateService implements AbstractUpdateService<Manager, WorkPlan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkPlanRepository repository;
	
	@Autowired
	protected Utils utils;

	// AbstractListService<Employer, Job> -------------------------------------


	@Override
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		boolean result;
		int jobId;
		WorkPlan workPlan;
		final Manager owner;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		workPlan = this.repository.findOneWorkPlanById(jobId);
		owner = workPlan.getOwner();
		principal = request.getPrincipal();
		result = owner.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;


		
		if (!errors.hasErrors("title")) {
			final String spam = this.utils.spamControl(entity.getTitle());
			errors.state(request, spam.isEmpty(), "title", "master.form.error.marked-as-spam");
		}
		
		if (!errors.hasErrors("description")) {
			final String spam = this.utils.spamControl(entity.getDescription());
			errors.state(request, spam.isEmpty(), "description", "master.form.error.marked-as-spam");
		}
		
		if (!errors.hasErrors("executionStart") || !errors.hasErrors("executionEnd")) {
			final WorkPlan workPlan = this.repository.findOneWorkPlanById(request.getModel().getInteger("id"));
			final boolean startDateChanged = !workPlan.getExecutionStart().equals(new Timestamp(entity.getExecutionStart().getTime()));
			final boolean endDateChanged = !workPlan.getExecutionEnd().equals(new Timestamp(entity.getExecutionEnd().getTime())); 
			// we will only check dates if fields has been updated
			if (startDateChanged && !errors.hasErrors("executionStart")) {
				final Date minimumExecutionStart = new Date();
				errors.state(request, entity.getExecutionStart().after(minimumExecutionStart), "executionStart", "manager.task.form.error.execution-start-past");
			}
			
			if ((startDateChanged  || endDateChanged ) && !errors.hasErrors("executionEnd")) {
				errors.state(request, entity.getExecutionEnd().after(entity.getExecutionStart()), "executionEnd", "manager.task.form.error.execution-end-before-start");
			}
		}
	}

	@Override
	public void bind(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "isPublic", "executionStart", "executionEnd");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;

		WorkPlan result;
		int id;

		id = request.getModel().getInteger("id");
		final boolean suggestExecutionPeriod = request.getModel().getBoolean("suggestExecutionPeriod");
		result = this.repository.findOneWorkPlanById(id);
		if(suggestExecutionPeriod) {
			Date suggestExecutionStart = result.getWorkPlanTask().stream()
				.map(wpt -> wpt.getTask().getExecutionStart())
				.sorted()
				.findFirst()
				.orElse(null);
			Date suggestExecutionEnd = result.getWorkPlanTask().stream()
				.map(wpt -> wpt.getTask().getExecutionEnd())
				.sorted()
				.skip(result.getWorkPlanTask().size()-1)
				.findFirst()
				.orElse(null);
			
			suggestExecutionStart = Date.from(suggestExecutionStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().minusDays(1).withHour(8).atZone(ZoneId.systemDefault()).toInstant());
			suggestExecutionEnd = Date.from(suggestExecutionStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(1).withHour(17).atZone(ZoneId.systemDefault()).toInstant());
			result.setExecutionStart(suggestExecutionStart);
			result.setExecutionEnd(suggestExecutionEnd);
		}

		return result;
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;
		final boolean suggestExecutionPeriod = request.getModel().getBoolean("suggestExecutionPeriod");
		if (!suggestExecutionPeriod) {
			this.repository.save(entity);			
		}
	}
}
