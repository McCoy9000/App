<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="_includes/header.jsp"%>
<h1>Error</h1>
<h3><c:out value="${errorInfo.error}" /></h3>
<h3><c:out value="${errorInfo.messageForUser}" /></h3>
<h3><c:out value="${errorInfo.nameClass}" /></h3>
<h5><c:out value="${errorInfo.uri}" /></h5>
<h3><c:out value="${url}" /></h3> 
<h5><c:out value="${errorInfo.stackTrace}" /></h5>
<%@ include file="_includes/scripts.jsp" %>

<%@ include file="_includes/footer.jsp"%>