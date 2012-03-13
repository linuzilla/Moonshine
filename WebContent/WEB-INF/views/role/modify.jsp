<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="POST" commandName="roleBean">
Name:<form:input path="roleName" /><br />
<input type="submit" name="submit" value="Modify role" />
</form:form>