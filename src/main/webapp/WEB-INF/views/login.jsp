<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="_includes/header.jsp"%>

<c:url value="/login" var="loginUrl"/>

<p>${requestScope.SPRING_SECURITY_LAST_EXCEPTION.getMessage}</p>

<form action="${loginUrl}" method="post">
	
	<c:if test="${param.error != null}">
		<p>
			Invalid username and password.
		</p>
	</c:if>
	<c:if test="${param.logout != null}">
		<p>
			You have been logged out.
		</p>
	</c:if>
	<c:if test="${accessDenied == true}">
		<p>Acceso denegado.</p>
		<p>Identifíquese con unas credenciales válidas
		   antes de intentar acceder a este recurso.
		</p>

	</c:if>
	<p>
		<label for="username">Username</label>
		<input type="text" id="username" name="username"/>
	</p>
	<p>
		<label for="password">Password</label>
		<input type="password" id="password" name="password"/>
	</p>
	<input type="hidden"
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
	<button type="submit" class="btn">LOG IN</button>
</form>

<%@ include file="_includes/scripts.jsp" %>

<%@ include file="_includes/footer.jsp"%>