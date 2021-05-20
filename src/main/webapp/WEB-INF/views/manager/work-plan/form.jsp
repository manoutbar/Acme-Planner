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

<acme:form>
	<acme:form-textbox code="anonymous.work-plan.form.label.title" path="title"/>
	<acme:form-textbox code="anonymous.work-plan.form.label.description" path="description"/>
	<acme:form-moment code="manager.work-plan.form.label.executionStart" path="executionStart"/>
	<acme:form-moment code="manager.work-plan.form.label.executionEnd" path="executionEnd"/>
	<acme:form-checkbox code="manager.work-plan.form.label.isPublic" path="isPublic"/>
	
	<!--<jstl:if test="${not empty workPlanTask && command == 'show'}">
		<h3><acme:message code="anonymous.work-plan.form.label.tasks.title"/></h3>
		<table class="table">
			<thead>
			<tr>
				<th><acme:message code="manager.work-plan.form.label.tasks.task.title" /></th>
				<th><acme:message code="manager.work-plan.form.label.tasks.task.executionStart" /></th>
				<th><acme:message code="manager.work-plan.form.label.tasks.task.executionEnd" /></th>
				<th><acme:message code="manager.work-plan.form.label.tasks.task.workload" /></th>
			</tr>
			</thead>
			
			<tbody>
				<jstl:forEach var="workPlanTask" items="${workPlanTask}">
				<tr>
					<td><acme:print value="${workPlanTask.task.title}" /></td>
					<td><acme:print value="${workPlanTask.task.executionStart}" /></td>
					<td><acme:print value="${workPlanTask.task.executionEnd}" /></td>
					<td><acme:print value="${workPlanTask.task.workload}" /></td>
				</tr>
				</jstl:forEach>
			</tbody>
		</table>
	</jstl:if> -->
			
	<acme:form-submit test="${command == 'show' && finalMode == 'false'}" code="manager.work-plan.form.button.update" action="/manager/work-plan/update"/>
	<acme:form-submit test="${command == 'show' && finalMode == 'false'}" code="manager.work-plan.form.button.suggest" action="/manager/work-plan/update?suggestExecutionPeriod=true"/>
	<acme:form-submit test="${command == 'show' && finalMode == 'false'}" code="manager.work-plan.form.button.delete" action="/manager/work-plan/delete"/>
	<acme:form-submit test="${command == 'show' && finalMode == 'false'}" code="manager.work-plan.form.button.publish" action="/manager/work-plan/publish"/>
	<acme:form-submit test="${command == 'show' && finalMode == 'false'}" method="get" code="manager.work-plan.form.button.manageTasks" action="/manager/work-plan/${id}/tasks/list"/>
	<acme:form-submit test="${command == 'create'}" code="manager.work-plan.form.button.create" action="/manager/work-plan/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.work-plan.form.button.update" action="/manager/work-plan/update"/>
	<acme:form-submit test="${command == 'publish'}" code="manager.work-plan.form.button.publish" action="/manager/work-plan/publish"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.work-plan.form.button.delete" action="/manager/work-plan/delete"/>
	<acme:form-return code="manager.work-plan.form.button.return"/>	
</acme:form>

