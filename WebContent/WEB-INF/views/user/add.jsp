<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="POST" commandName="userBean">
<form:input path="name" />
<input type="submit" name="submit" value="Add user" tabindex="2" />
</form:form>