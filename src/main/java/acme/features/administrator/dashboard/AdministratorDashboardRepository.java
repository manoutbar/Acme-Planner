/*
 * AdministratorDashboardRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double averageNumberOfJobsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double averageNumberOfApplicationsPerWorker();

	@Query("select avg(select count(a) from Application a where exists(select j from Job j where j.employer.id = e.id and a.job.id = j.id)) from Employer e")
	Double averageNumberOfApplicationsPerEmployer();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.PENDING")
	Double ratioOfPendingApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.ACCEPTED")
	Double ratioOfAcceptedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.REJECTED")
	Double ratioOfRejectedApplications();
	
	@Query("select 1.0 * count(t) from Task t")
	Double countTasks();

	@Query("select 1.0 * count(t) from Task t where t.isPublic = false")
	Double totalOfPrivateTasks();

	@Query("select 1.0 * count(t) from Task t where t.executionEnd < CURRENT_TIMESTAMP")
	Double totalOfFinishedTasks();

	@Query("select 1.0 * avg(datediff(t.executionEnd, t.executionStart)) from Task t")
	Double averageNumberOfTasksExecutionPeriod();

	@Query("select 1.0 * std(datediff(t.executionEnd, t.executionStart)) from Task t")
	Double deviationSumOfTasksExecutionPeriod();

	@Query("select 1.0 * min(datediff(t.executionEnd, t.executionStart)) from Task t")
	Double minimumTaskExecutionPeriod();
	
	@Query("select 1.0 * max(datediff(t.executionEnd, t.executionStart)) from Task t")
	Double maximumTaskExecutionPeriod();

	@Query("select 1.0 * avg(t.workload) from Task t")
	Double averageNumberOfTasksWorkloads();

	@Query("select 1.0 * std(t.workload) from Task t")
	Double deviationNumberOfTasksWorkloads();

	@Query("select 1.0 * min(t.workload) from Task t")
	Double minimumTaskWorkloads();

	@Query("select 1.0 * max(t.workload) from Task t")
	Double maximumTaskWorkloads();
		
	@Query("select 1.0 * count(wp) from WorkPlan wp")
	Double countWorkPlans();
		
	@Query("select 1.0 * count(wp) from WorkPlan wp where wp.finalMode = true")
	Double countPublishedWorkPlans();
	
	@Query("select 1.0 * count(wp) from WorkPlan wp where wp.isPublic = false")
	Double totalOfPrivateWorkPlans();

	@Query("select 1.0 * count(wp) from WorkPlan wp where wp.executionEnd < CURRENT_TIMESTAMP")
	Double totalOfFinishedWorkPlans();

	@Query("select 1.0 * avg(datediff(wp.executionEnd, wp.executionStart)) from WorkPlan wp")
	Double averageNumberOfWorkPlansExecutionPeriod();

	@Query("select 1.0 * std(datediff(wp.executionEnd, wp.executionStart)) from WorkPlan wp")
	Double deviationSumOfWorkPlansExecutionPeriod();

	@Query("select 1.0 * min(datediff(wp.executionEnd, wp.executionStart)) from WorkPlan wp")
	Double minimumWorkPlansExecutionPeriod();
	
	@Query("select 1.0 * max(datediff(wp.executionEnd, wp.executionStart)) from WorkPlan wp")
	Double maximumWorkPlansExecutionPeriod();
	
	@Query("select 1.0 * avg(select sum(t.workload) from WorkPlan wp2 left join wp2.workPlanTask wpt left join wpt.task t where wp2.id = wp.id) from WorkPlan wp")
	Double averageNumberOfWorkPlansWorkloads();
	
	@Query("select 1.0 * std((select sum(t.workload) from WorkPlan wp2 left join wp2.workPlanTask wpt left join wpt.task t where wp2.id = wp.id)) from WorkPlan wp")
	Double deviationNumberOfWorkPlansWorkloads();

	@Query("select 1.0 * min(select sum(t.workload) from WorkPlan wp2 left join wp2.workPlanTask wpt left join wpt.task t where wp2.id = wp.id) from WorkPlan wp")
	Double minimumWorkPlanWorkload();

	@Query("select 1.0 * max(select sum(t.workload) from WorkPlan wp2 left join wp2.workPlanTask wpt left join wpt.task t where wp2.id = wp.id) from WorkPlan wp")
	Double maximumWorkPlanWorkload();
	
}
