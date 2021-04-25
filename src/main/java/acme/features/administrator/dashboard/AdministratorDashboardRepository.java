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

	@Query("select SQRT(SUM("
				+ "(1.0 * datediff(t.executionEnd, t.executionStart) - :avg)"
				+ " * "
				+ "(1.0 * datediff(t.executionEnd, t.executionStart) - :avg)"
			+ "))"
		+ "/ SQRT(COUNT(t)) from Task t where t.executionEnd is not null and t.executionStart is not null")
	Double deviationSumOfTasksExecutionPeriod(double avg);

	@Query("select 1.0 * min(datediff(t.executionEnd, t.executionStart)) from Task t")
	Double minimumTaskExecutionPeriod();
	
	@Query("select 1.0 * max(datediff(t.executionEnd, t.executionStart)) from Task t")
	Double maximumTaskExecutionPeriod();

	@Query("select 1.0 * avg(t.workload) from Task t")
	Double averageNumberOfTasksWorkloads();
	
	@Query("select SQRT(SUM("
			+ "(1.0 * t.workload - :avg)"
			+ " * "
			+ "(1.0 * t.workload - :avg)"
		+ "))"
	+ "/ SQRT(COUNT(t)) from Task t")
	Double deviationNumberOfTasksWorkloads(double avg);

	@Query("select 1.0 * min(t.workload) from Task t")
	Double minimumTaskWorkloads();

	@Query("select 1.0 * max(t.workload) from Task t")
	Double maximumTaskWorkloads();

}
