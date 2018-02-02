<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="_includes/header.jsp"%>
<h1>Error</h1>
<h3><c:out value="${e.messageForUser}" /></h3> 
<h3><c:out value="${e.error}" /></h3>
<h3><c:out value="${e.nameClass}" /></h3>
<h3><c:out value="${e.stackTrace}" /></h3>
<h3><c:out value="${e.uri}" /></h3>

<%@ include file="_includes/footer.jsp"%>