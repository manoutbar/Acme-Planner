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
	<acme:form-moment code="manager.work-plan.form.label.executionStart" path="executionStart"/>
	<acme:form-moment code="manager.work-plan.form.label.executionEnd" path="executionEnd"/>
	<acme:form-checkbox code="manager.work-plan.form.label.isPublic" path="isPublic"/>	
			
	<acme:form-submit test="${command == 'show' && finalMode == 'false'}" code="manager.work-plan.form.button.update" action="/manager/work-plan/update"/>
	<acme:form-submit test="${command == 'show' && finalMode == 'false'}" code="manager.work-plan.form.button.delete" action="/manager/work-plan/delete"/>
	<acme:form-submit test="${command == 'show' && finalMode == 'false'}" code="manager.work-plan.form.button.publish" action="/manager/work-plan/publish"/>
	<acme:form-submit test="${command == 'create'}" code="manager.work-plan.form.button.create" action="/manager/work-plan/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.work-plan.form.button.update" action="/manager/work-plan/update"/>
	<acme:form-submit test="${command == 'publish'}" code="manager.work-plan.form.button.publish" action="/manager/work-plan/publish"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.work-plan.form.button.delete" action="/manager/work-plan/delete"/>		
	<acme:form-return code="manager.work-plan.form.button.return"/>	
</acme:form>
