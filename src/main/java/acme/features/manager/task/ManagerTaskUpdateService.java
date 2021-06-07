package acme.features.manager.task;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.util.Utils;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;

	@Autowired
	protected Utils utils;
	
	// AbstractListService<Employer, Job> -------------------------------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		boolean result;
		int taskId;
		Task task;
		final Manager owner;
		Principal principal;

		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(taskId);
		owner = task.getOwner();
		principal = request.getPrincipal();
		result = owner.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
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
			final Task task = this.repository.findOneTaskById(request.getModel().getInteger("id"));
			
			final boolean startDateChanged = !task.getExecutionStart().equals(new Timestamp(entity.getExecutionStart().getTime()));
			final boolean endDateChanged = !task.getExecutionEnd().equals(new Timestamp(entity.getExecutionEnd().getTime()));
			
			// we will only check dates if fields has been updated
			if (startDateChanged && !errors.hasErrors("executionStart")) {
				final Date minimumExecutionStart = new Date();
				errors.state(request, entity.getExecutionStart().after(minimumExecutionStart), "executionStart", "manager.task.form.error.execution-start-past");
			}
			
			if ((startDateChanged || endDateChanged) && !errors.hasErrors("executionEnd")) {
				errors.state(request, entity.getExecutionEnd().after(entity.getExecutionStart()), "executionEnd", "manager.task.form.error.execution-end-before-start");
			}
		}
		
		if (!errors.hasErrors("workload")) {
			errors.state(request, entity.getWorkload() >= 0.0, "workload", "manager.task.form.error.workload-positive");
			
			final double decimalPart = entity.getWorkload() - entity.getWorkload().intValue();
			errors.state(request, decimalPart < 0.59, "workload", "manager.task.form.error.workload-invalid-decimal-part");

			if (!errors.hasErrors("executionStart") && !errors.hasErrors("executionEnd")) {
				final Long diffInMillis = Math.abs(entity.getExecutionEnd().getTime() - entity.getExecutionStart().getTime());
				final Long executionPeriodInMinutes = TimeUnit.MINUTES.convert(diffInMillis, TimeUnit.MILLISECONDS);
				final double workloadDecimals = (double) Math.round((entity.getWorkload() - entity.getWorkload().intValue()) * 100) / 100;
				final Long workloadInMinutes = (long) (entity.getWorkload().intValue() * 60 + workloadDecimals * 100);
				
				errors.state(request, executionPeriodInMinutes >= workloadInMinutes, "workload", "manager.task.form.error.workload-greater-than-execution-period");
			}
		}
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "isPublic", "executionStart", "executionEnd", "workload", "description", "link");
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);

		return result;
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
