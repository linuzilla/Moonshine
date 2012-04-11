<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<spring:url value="/user/modify" var="modify_url" />
<spring:url value="/user/delete" var="delete_url" />
<spring:url value="/resources/images/delete.png" var="delete_image" />

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
		<td>
			<spring:url value="/user/delete/${user.userId}" var="delete_form_url" />
			<spring:url value="/resources/images/delete.png" var="delete_image_url" />
			<form:form action="${fn:escapeXml(delete_form_url)}" method="DELETE">
                <spring:message text="Delete" code="entity_delete" var="delete_label" />
                <c:set var="delete_confirm_msg">
                  <spring:escapeBody javaScriptEscape="true">
                    <spring:message code="entity_delete_confirm" text="Confirm" />
                  </spring:escapeBody>
                </c:set>
                <input alt="${fn:escapeXml(delete_label)}" class="image" src="${fn:escapeXml(delete_image_url)}" title="${fn:escapeXml(delete_label)}" type="image" value="${fn:escapeXml(delete_label)}" onclick="return confirm('${delete_confirm_msg}');" />
              </form:form>
		</td>
		<td><a href="${delete_url}?userId=${user.userId}">delete</a></td>
	</tr>
</c:forEach>
</table>

<br /><br /><hr /><br /><br />

<spring:url value="/user/json" var="json_url" />
<script type="text/javascript">

function callGetAll() {
	$.ajax({
		url: '${json_url}',
		dataType: 'json',
		type: 'Get',
		timeout: 5000,
		success: function(data) {
			var r = new Array(), j = -1;
			r[++j] = '<tr><th>UserId</th><th>Name</th><th>Email</th></tr>';
			for (var key=0, size=data.length; key < size; key++) {
				r[++j] ='<tr><td>';
				r[++j] = data[key].userId;
				r[++j] = '</td><td class="whatever1">';
				r[++j] = data[key].name;
				r[++j] = '</td><td class="whatever2">';
				r[++j] = data[key].email;
				r[++j] = '</td></tr>';
			}
			$('#dataTable').html(r.join('')); 
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("oops! " + textStatus);
		}
	});
}

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

$(document).ready(function() { callGetAll(); });
</script>
<div>
<form id="getUserForm" >
<span>Name: </span> 
<input type="text" name="username" />
<input type="button" value="GetUser" onClick="callGetUser()"/></form>
</div>
<table id="table" style="display:none;"><tr><td>
UserId: <span id="userid"></span><br />
Name: <span id="uname"></span><br />
Email: <span id="email"></span><br />
Created By: <span id="createdBy"></span><br />
</td></tr></table>
<br /><br /><hr /><br /><br />

<table id="dataTable"></table>

<br /><br /><hr /><br /><br />

<a href="<spring:url value="/user/add" />">Add user</a><br />
<a href="<spring:url value="/role" />">Role Management</a>
