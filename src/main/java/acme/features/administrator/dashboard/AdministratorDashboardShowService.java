/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,
			"totalNumberOfPublicTasks", "totalNumberOfPrivateTasks",
			"totalNumberOfFinishedTasks", "totalNumberOfUnfinishedTasks",
			"averageNumberOfTasksExecutionPeriod", "deviationNumberOfTasksExecutionPeriod",
			"minimumTaskExecutionPeriod", "maximumTaskExecutionPeriod",
			"averageNumberOfTasksWorkload", "deviationNumberOfTasksWorkload",
			"minimumTasksWorkload", "maximumTasksWorkload",
			"totalNumberOfWorkPlans", "totalNumberOfPrivateWorkPlans",
			"totalNumberOfFinishedWorkPlans", "averageNumberOfWorkPlansExecutionPeriod",
			"deviationSumOfWorkPlansExecutionPeriod", "minimumWorkPlansExecutionPeriod",
			"maximumWorkPlansExecutionPeriod", "averageNumberOfWorkPlansWorkloads",
			"deviationNumberOfWorkPlansWorkloads", "minimumWorkPlanWorkload",
			"maximumWorkPlanWorkload", "totalNumberOfPublicWorkPlans",
			"totalNumberOfNonFinishedWorkPlans", "totalNumberOfPublishedWorkPlans"
			);
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		Double totalNumberOfTasks;
		Double totalNumberOfPublicTasks;
		Double totalNumberOfPrivateTasks;
		Double totalNumberOfFinishedTasks;
		Double totalNumberOfUnfinishedTasks;
		Double averageNumberOfTasksExecutionPeriod;
		Double deviationNumberOfTasksExecutionPeriod;
		Double minimumTaskExecutionPeriod;
		Double maximumTaskExecutionPeriod;
		Double averageNumberOfTasksWorkloads;
		Double deviationNumberOfTasksWorkloads;
		Double minimumTaskWorkloads;
		Double maximumTaskWorkloads;
		Double totalNumberOfWorkPlans;
		final Double totalNumberOfPublishedWorkPlans;
		Double totalNumberOfPublicWorkPlans;
		Double totalNumberOfPrivateWorkPlans;
		Double totalNumberOfFinishedWorkPlans;
		Double totalNumberOfNonFinishedWorkPlans;
		Double averageNumberOfWorkPlansExecutionPeriod;
		Double deviationSumOfWorkPlansExecutionPeriod;
		Double minimumWorkPlansExecutionPeriod;
		Double maximumWorkPlansExecutionPeriod;
		Double averageNumberOfWorkPlansWorkloads;
		Double deviationNumberOfWorkPlansWorkloads;
		Double minimumWorkPlanWorkload;
		Double maximumWorkPlanWorkload;
		
		totalNumberOfTasks = this.repository.countTasks();
		totalNumberOfPrivateTasks = this.repository.totalOfPrivateTasks();
		totalNumberOfPublicTasks = totalNumberOfTasks - totalNumberOfPrivateTasks;
		totalNumberOfFinishedTasks = this.repository.totalOfFinishedTasks();
		totalNumberOfUnfinishedTasks = totalNumberOfTasks - totalNumberOfFinishedTasks;
		averageNumberOfTasksExecutionPeriod = this.repository.averageNumberOfTasksExecutionPeriod();
		deviationNumberOfTasksExecutionPeriod = this.repository.deviationSumOfTasksExecutionPeriod();
		minimumTaskExecutionPeriod = this.repository.minimumTaskExecutionPeriod();
		maximumTaskExecutionPeriod = this.repository.maximumTaskExecutionPeriod();
		averageNumberOfTasksWorkloads = this.repository.averageNumberOfTasksWorkloads();
		deviationNumberOfTasksWorkloads = this.repository.deviationNumberOfTasksWorkloads();
		minimumTaskWorkloads = this.repository.minimumTaskWorkloads();
		maximumTaskWorkloads = this.repository.maximumTaskWorkloads();
		
		// workplans
		totalNumberOfWorkPlans = this.repository.countWorkPlans();
		totalNumberOfPublishedWorkPlans = this.repository.countPublishedWorkPlans();
		totalNumberOfPrivateWorkPlans = this.repository.totalOfPrivateWorkPlans();
		totalNumberOfPublicWorkPlans = totalNumberOfWorkPlans - totalNumberOfPrivateWorkPlans;
		totalNumberOfFinishedWorkPlans = this.repository.totalOfFinishedWorkPlans();
		totalNumberOfNonFinishedWorkPlans = totalNumberOfWorkPlans - totalNumberOfFinishedWorkPlans;
		averageNumberOfWorkPlansExecutionPeriod = this.repository.averageNumberOfWorkPlansExecutionPeriod();
		deviationSumOfWorkPlansExecutionPeriod = this.repository.deviationSumOfWorkPlansExecutionPeriod();
		minimumWorkPlansExecutionPeriod = this.repository.minimumWorkPlansExecutionPeriod();
		maximumWorkPlansExecutionPeriod = this.repository.maximumWorkPlansExecutionPeriod();
		averageNumberOfWorkPlansWorkloads = this.repository.averageNumberOfWorkPlansWorkloads();
		deviationNumberOfWorkPlansWorkloads = this.repository.deviationNumberOfWorkPlansWorkloads();
		minimumWorkPlanWorkload = this.repository.minimumWorkPlanWorkload();
		maximumWorkPlanWorkload = this.repository.maximumWorkPlanWorkload();

		result = new Dashboard();		
		result.setTotalNumberOfFinishedTasks(totalNumberOfFinishedTasks);
		result.setTotalNumberOfUnfinishedTasks(totalNumberOfUnfinishedTasks);
		result.setTotalNumberOfPrivateTasks(totalNumberOfPrivateTasks);
		result.setTotalNumberOfPublicTasks(totalNumberOfPublicTasks);
		result.setAverageNumberOfTasksExecutionPeriod(averageNumberOfTasksExecutionPeriod);
		result.setDeviationNumberOfTasksExecutionPeriod(deviationNumberOfTasksExecutionPeriod);
		result.setMinimumTaskExecutionPeriod(minimumTaskExecutionPeriod);
		result.setMaximumTaskExecutionPeriod(maximumTaskExecutionPeriod);
		result.setAverageNumberOfTasksWorkload(averageNumberOfTasksWorkloads);
		result.setDeviationNumberOfTasksWorkload(deviationNumberOfTasksWorkloads);
		result.setMinimumTasksWorkload(minimumTaskWorkloads);
		result.setMaximumTasksWorkload(maximumTaskWorkloads);
		
		result.setTotalNumberOfWorkPlans(totalNumberOfWorkPlans);
		result.setTotalNumberOfPublishedWorkPlans(totalNumberOfPublishedWorkPlans);
		result.setTotalNumberOfPublicWorkPlans(totalNumberOfPublicWorkPlans);
		result.setTotalNumberOfPrivateWorkPlans(totalNumberOfPrivateWorkPlans);
		result.setTotalNumberOfFinishedWorkPlans(totalNumberOfFinishedWorkPlans);
		result.setTotalNumberOfNonFinishedWorkPlans(totalNumberOfNonFinishedWorkPlans);
		result.setAverageNumberOfWorkPlansExecutionPeriod(averageNumberOfWorkPlansExecutionPeriod);
		result.setDeviationSumOfWorkPlansExecutionPeriod(deviationSumOfWorkPlansExecutionPeriod);
		result.setMinimumWorkPlansExecutionPeriod(minimumWorkPlansExecutionPeriod);
		result.setMaximumWorkPlansExecutionPeriod(maximumWorkPlansExecutionPeriod);
		result.setAverageNumberOfWorkPlansWorkloads(averageNumberOfWorkPlansWorkloads);
		result.setDeviationNumberOfWorkPlansWorkloads(deviationNumberOfWorkPlansWorkloads);
		result.setMinimumWorkPlanWorkload(minimumWorkPlanWorkload);
		result.setMaximumWorkPlanWorkload(maximumWorkPlanWorkload);

		return result;
	}
}
