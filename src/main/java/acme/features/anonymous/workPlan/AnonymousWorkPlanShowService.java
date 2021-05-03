/*
 * AnonymousJobShowService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.workPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acme.entities.tasks.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousWorkPlanShowService implements AbstractShowService<Anonymous, WorkPlan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousWorkPlanRepository repository;

	@Override
	@Transactional(readOnly = true)
	public boolean authorise(final Request<WorkPlan> request) {
		assert request != null;

		boolean result;
		int id;
		WorkPlan workPlan;

		id = request.getModel().getInteger("id");
		workPlan = this.repository.findOneWorkPlanById(id);
		
		result = workPlan != null && workPlan.getIsPublic();

		return result;
	}

	// AbstractShowService<Anonymous, Task> interface --------------------------

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "isPublic", "executionStart", "executionEnd", "title", "description");
		request.unbind(entity, model, "workload", "workPlanTask");
	}

	@Override
	@Transactional(readOnly = true)
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;

		WorkPlan result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkPlanById(id);

		return result;
	}

}
