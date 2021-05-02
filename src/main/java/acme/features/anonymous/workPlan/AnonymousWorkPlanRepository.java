/*
 * AnonymousJobRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkPlanRepository extends AbstractRepository {

	@Query("select wp from WorkPlan wp where wp.id = ?1")
	WorkPlan findOneWorkPlanById(int id);

	@Query("select wp from WorkPlan wp where wp.executionEnd > current_timestamp() and wp.isPublic = true order by wp.executionStart, wp.executionEnd")
	Collection<WorkPlan> findManyPublicNonFinishedWorkPlans();

}
