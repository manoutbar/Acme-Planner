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
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-public-work-plans"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPublicWorkPlans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-private-work-plans"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrivateWorkPlans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-finished-work-plans"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfFinishedWorkPlans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-non-finished-work-plans"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfNonFinishedWorkPlans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-work-plans-execution-period"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfWorkPlansExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-work-plans-execution-period"/>
		</th>
		<td>
			<acme:print value="${deviationSumOfWorkPlansExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-work-plans-execution-period"/>
		</th>
		<td>
			<acme:print value="${minimumWorkPlansExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-work-plans-execution-period"/>
		</th>
		<td>
			<acme:print value="${maximumWorkPlansExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-work-plans-workload"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfWorkPlansWorkloads}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-work-plans-workload"/>
		</th>
		<td>
			<acme:print value="${deviationNumberOfWorkPlansWorkloads}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-work-plans-workload"/>
		</th>
		<td>
			<acme:print value="${minimumWorkPlanWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-work-plans-workload"/>
		</th>
		<td>
			<acme:print value="${maximumWorkPlanWorkload}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.workplan-statuses"/>
</h2>

<div>
	<canvas id="workplans-canvas"></canvas>
</div>
<p>
	TOTAL: <acme:message code="administrator.dashboard.form.label.chart.total" />	
</p><p>
	PUBLISHED: <acme:message code="administrator.dashboard.form.label.chart.published" />	
</p><p>
	NON_PUBLISHED: <acme:message code="administrator.dashboard.form.label.chart.non-published" />	
</p>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			workPlans: {
				labels : [
					"TOTAL", "PUBLISHED", "NOT_PUBLISHED"
				],
				datasets : [
					{
						data : [
							<jstl:out value="${totalNumberOfWorkPlans}"/>, 
							<jstl:out value="${totalNumberOfPublishedWorkPlans}"/>, 
							<jstl:out value="${totalNumberOfWorkPlans - totalNumberOfPublishedWorkPlans}"/>, 
						]
					}
				]
			}
		};
	
		
		function createChart(canvasId, data, options) {
			var canvas, context;
			
			canvas = document.getElementById(canvasId);
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
		}
		
		createChart("workplans-canvas", data.workPlans, {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : <jstl:out value="${totalNumberOfWorkPlans}"/>
						}
					}
				]
			},
			legend : {
				display : false
			}
		});
	
		
	});
</script>
