<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/user/modify" var="modify_url" />
<spring:url value="/user/delete" var="delete_url" />
<table>
<c:forEach var="user" items="${userList}">
	<tr>
		<td>${user.userId}</td>
		<td>${user.name}</td>
		<td>${user.email}</td>
		<td><a href="${modify_url}/${user.userId}">modify</a></td>
		<td><a href="${delete_url}?userId=${user.userId}">delete</a></td>
	</tr>
</c:forEach>
</table>
<a href="<spring:url value="/user/add" />">Add user</a>
