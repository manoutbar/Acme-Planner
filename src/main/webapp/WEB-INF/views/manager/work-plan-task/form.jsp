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

<jstl:set var="readonly" value="${command == 'show'}" />

<acme:form readonly="${readonly}">
	<acme:form-select code="manager.work-plan-task.form.label.task" path="task.id">
		<jstl:forEach var="task" items="${tasks}">
			<acme:form-option code="${task.title}" value="${task.id}"/>
		</jstl:forEach>
	</acme:form-select>
	
	<acme:form-submit test="${command == 'create'}" code="manager.work-plan-task.form.button.create" action="/manager/work-plan/${workPlan.id}/tasks/create"/>
	<acme:form-submit test="${command == 'show'}" code="manager.work-plan-task.form.button.delete" action="/manager/work-plan/${workPlan.id}/tasks/delete"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.work-plan-task.form.button.delete" action="/manager/work-plan/${workPlan.id}/tasks/delete"/>
	<acme:form-return code="manager.work-plan-task.form.button.return"/>	
</acme:form>

