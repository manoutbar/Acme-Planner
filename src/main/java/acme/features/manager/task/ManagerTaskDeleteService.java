package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerTaskDeleteService implements AbstractDeleteService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		boolean result;
		int jobId;
		final Task workplan;
		Manager owner;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		workplan = this.repository.findOneTaskById(jobId);
		owner = workplan.getOwner();
		principal = request.getPrincipal();
		result = owner.getUserAccount().getId() == principal.getAccountId();

		return result;
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
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}
}
