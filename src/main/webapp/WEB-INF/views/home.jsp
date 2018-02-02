<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="_includes/header.jsp"%>

<h1><spring:message code="index.header" /></h1>
<a href='<spring:url value="/logout" />'><input type="button" value="<spring:message code='button.logout' />" /></a>
<a href='<spring:url value="/pruebas" />'><input type="button" value="<spring:message code='button.pruebas' />" /></a>

<%@ include file="_includes/footer.jsp"%>