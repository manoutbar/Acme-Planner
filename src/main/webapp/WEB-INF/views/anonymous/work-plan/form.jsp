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
	<acme:form-checkbox code="anonymous.work-plan.form.label.public" path="isPublic"/>
	<acme:form-moment code="anonymous.work-plan.form.label.execution-start" path="executionStart"/>
	<acme:form-moment code="anonymous.work-plan.form.label.execution-end" path="executionEnd"/>
	<acme:form-double code="anonymous.work-plan.form.label.workload" path="workload"/>
	
	<jstl:if test="${not empty workPlanTask}">
		<h3><acme:message code="anonymous.work-plan.form.label.tasks.title"/></h3>
		<table class="table">
			<thead>
			<tr>
				<th><acme:message code="anonymous.work-plan.form.label.tasks.task.title" /></th>
				<th><acme:message code="anonymous.work-plan.form.label.tasks.task.executionStart" /></th>
				<th><acme:message code="anonymous.work-plan.form.label.tasks.task.executionEnd" /></th>
				<th><acme:message code="anonymous.work-plan.form.label.tasks.task.workload" /></th>
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
	</jstl:if>
	
	
	<acme:form-return code="anonymous.work-plan.form.button.return"/>	
</acme:form>
