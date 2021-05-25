<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="manager.work-plan-task.list.label.title" path="task.title" width="50%"/>
	<acme:list-column code="manager.work-plan-task.list.label.executionStart" path="task.executionStart" width="20%"/>
	<acme:list-column code="manager.work-plan-task.list.label.executionEnd" path="task.executionEnd" width="20%"/>
	<acme:list-column code="manager.work-plan-task.list.label.isPublic" path="task.isPublic" width="10%"/>
</acme:list>

<acme:form>
	<acme:form-submit code="manager.work-plan-task.list.action.add" method="get" action="/manager/work-plan/${workPlanId}/tasks/create"/>
  	<acme:form-return code="manager.work-plan-task.list.action.return"/>
</acme:form>