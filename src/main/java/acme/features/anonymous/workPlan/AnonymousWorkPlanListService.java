/*
 * AnonymousJobListService.java
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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acme.entities.tasks.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousWorkPlanListService implements AbstractListService<Anonymous, WorkPlan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousWorkPlanRepository repository;

	// AbstractListService<Anonymous, WorkPlan>  interface -------------------------

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

		request.unbind(entity, model, "title", "executionStart", "executionEnd", "workload");
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
		assert request != null;

		Collection<WorkPlan> result;

		result = this.repository.findManyPublicNonFinishedWorkPlans();

		return result;
	}

}
