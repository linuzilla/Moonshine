<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<spring:url value="/user/modify" var="modify_url" />
<spring:url value="/user/delete" var="delete_url" />

<h1>Hi, <security:authentication property="principal.username" /></h1>

<table>
<c:forEach var="user" items="${userList}">
	<tr>
		<td>${user.userId}</td>
		<td>${user.name}</td>
		<td>${user.email}</td>
		<td>[ 
			<c:forEach var="item" items="${user.roles}">
				${item.roleName}
			</c:forEach>
			]</td>
		<td>${user.createdBy}</td>
		<td><a href="${modify_url}/${user.userId}">modify</a></td>
		<td><a href="${delete_url}?userId=${user.userId}">delete</a></td>
	</tr>
</c:forEach>
</table>

<spring:url value="/user/json" var="json_url" />
<script type="text/javascript">
    function callGetUser(){
    	var name=$('#getUserForm input[name=username]').val();
       
    	if (name != "") {
	    	$.ajax({
	    		url: '${json_url}/' + name,
	    		dataType: 'json', 
	    		type: 'GET', 
	    		timeout: 5000, 
	    	
	    		success: function(data) {
	    			if (data != null) {
	            		$('#userid').html(data.userId);
	            		$('#uname').html(data.name);
	            		$('#email').html(data.email);
	            		$('#createdBy').html(data.createdBy);
	            		$('#table').show();
	            	} else {
	            		$('#table').hide();
	            		alert(name + " not found");
	            	}
	    		},
	    		
	    		error: function(XMLHttpRequest, textStatus, errorThrown){
	    			alert(textStatus);
	    		}
	    	});
    	}
    }
</script>
<div>
<form id="getUserForm" >
<span>Name: </span> 
<input type="text" name="username" />
<input type="button" value="GetUser" onClick="callGetUser()"/></form>
</div>
<hr />
<table id="table" style="display:none;"><tr><td>
UserId: <span id="userid"></span><br />
Name: <span id="uname"></span><br />
Email: <span id="email"></span><br />
Created By: <span id="createdBy"></span><br />
</td></tr></table>

<a href="<spring:url value="/user/add" />">Add user</a><br />
<a href="<spring:url value="/role" />">Role Management</a>
