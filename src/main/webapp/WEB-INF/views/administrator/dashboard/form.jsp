<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-number-jobs-employer"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfJobsPerEmployer}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-number-applications-worker"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfApplicationsPerWorker}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-number-applications-employer"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfApplicationsPerEmployer}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-public-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPublicTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-private-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrivateTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-finished-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-unfinished-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfUnfinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-number-tasks-execution-period"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfTasksExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-number-tasks-execution-period"/>
		</th>
		<td>
			<acme:print value="${deviationNumberOfTasksExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-tasks-execution-period"/>
		</th>
		<td>
			<acme:print value="${minimumTaskExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-tasks-execution-period"/>
		</th>
		<td>
			<acme:print value="${maximumTaskExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-number-tasks-workload"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfTasksWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-number-tasks-workload"/>
		</th>
		<td>
			<acme:print value="${deviationNumberOfTasksWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-tasks-workload"/>
		</th>
		<td>
			<acme:print value="${minimumTasksWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-tasks-workload"/>
		</th>
		<td>
			<acme:print value="${maximumTasksWorkload}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.application-statuses"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"PENDING", "ACCEPTED", "REJECTED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${ratioOfPendingApplications}"/>, 
						<jstl:out value="${ratioOfAcceptedApplications}"/>, 
						<jstl:out value="${ratioOfRejectedApplications}"/>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
