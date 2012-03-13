<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="POST" commandName="userBean">
Name:<form:input path="name" /><br />
Email:<form:input path="email" /><br />
Role:<form:checkboxes path="roleNames" items="${roles}" itemValue="roleName" itemLabel="roleName"/>
<input type="submit" name="submit" value="Add user"  />
</form:form>
