<%--
- list.tag
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@tag language="java" 
	import="java.util.Collection,java.util.ArrayList,java.util.Map,acme.framework.helpers.JspHelper"
%>


<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%@attribute name="action" required="true" type="java.lang.String"%>
<%@attribute name="code" required="true" type="java.lang.String"%>

<a class="btn btn-primary mt-3" href="javascript: redirect('${action}')" role="button" aria-disabled="true">
	<spring:message code="${code}" />
</a>
<!--  <button type="button" formmethod="get" onclick="javascript: redirect('${action}')" class="btn btn-primary">
				<acme:message code="${code}"/>
			</button>	 -->