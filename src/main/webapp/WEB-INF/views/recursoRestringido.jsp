<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="_includes/header.jsp"%>

<h1>Recurso de acceso restringido</h1>

<form:form id="form_usuario">

<select id="action">
	<option>usuarios</option>
	<option>usuarios/filter</option>
</select>

<select id="method">
	<option>GET</option>
	<option>POST</option>
	<option>PUT</option>
	<option>DELETE</option>
</select>

<input type="submit" value="enviar" id="submit_form_usuario"/>
</form:form>

<div id="target"></div>

<%@ include file="_includes/scripts.jsp"%>
<script src="<spring:url value='/resources/js/lib/recursoRestringido.js'/>"></script>
<%@ include file="_includes/footer.jsp"%>