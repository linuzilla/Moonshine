<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/role/modify" var="modify_url" />
<spring:url value="/role/delete" var="delete_url" />
<table>
<c:forEach var="role" items="${roles}">
	<tr>
		<td>${role.roleId}</td>
		<td>${role.roleName}</td>
		<td><a href="${modify_url}/${role.roleId}">modify</a></td>
		<td><a href="${delete_url}?roleId=${role.roleId}">delete</a></td>
	</tr>
</c:forEach>
</table>
<a href="<spring:url value="/role/add" />">Add role</a>
