<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="pageName" required="false" type="java.lang.String" %>


<div class="container mt-2 p-2">
	 <h3 class="text-primary"><c:out value="${pageName }"></c:out></h3>
	<jsp:doBody></jsp:doBody>
</div>
