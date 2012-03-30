<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/dwr" var="dwr_url" />
<script type='text/javascript' src='${dwr_url}/engine.js'></script>
<script type='text/javascript' src='${dwr_url}/interface/RoleService.js'></script>
<script type='text/javascript' src='${dwr_url}/util.js'></script>
<script type="text/javascript">
	function update() {
		var name = dwr.util.getValue("findRoleByName");
		RoleService.getRoleByName(name, function(data) {
			if (data != null) {
				dwr.util.setValue("roleId", data.roleId);
				dwr.util.setValue("roleName", data.roleName);
				// dwr.util.setValue(data);
				dwr.util.setValue("message", "found");
			} else {
				dwr.util.setValue("roleId", "");
				dwr.util.setValue("roleName", "");
				dwr.util.setValue("message", "not found");
			}
		});
	}
</script>
<p>
  Name:
  <input type="text" id="findRoleByName"/>
  <input value="Find" type="button" onclick="update()"/>
  <br/>
  RoleId: <span id="roleId"></span><br/>
  RoleName: <span id="roleName"></span><br/>
  Message: <span id="message"></span><br/>
</p>