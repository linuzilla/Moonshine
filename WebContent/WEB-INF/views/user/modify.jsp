<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="POST" commandName="userBean">
Name:<form:input path="name" /><br />
Email:<form:input path="email" /><br />
<input type="submit" name="submit" value="Modify user" tabindex="2" />
</form:form>