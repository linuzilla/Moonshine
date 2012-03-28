<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/dwr" var="dwr_url" />
<script type='text/javascript' src='${dwr_url}/engine.js'></script>
<script type='text/javascript' src='${dwr_url}/interface/UserService.js'></script>
<script type='text/javascript' src='${dwr_url}/util.js'></script>
<script type="text/javascript">
	function init() {
		dwr.util.useLoadingMessage();
		fillTable();
	}

	var peopleCache = { };
	var viewed = -1;

	function fillTable() {
		UserService.findAll(function(people) {
			dwr.util.removeAllRows("peoplebody", {
				filter:function(tr) { return (tr.id != "pattern");}
			});
			var person, id;
			for (var i = 0; i < people.length; i++) {
				person = people[i];
				id = person.userId;
				dwr.util.cloneNode("pattern", { idSuffix:id });
				dwr.util.setValue("tableName" + id, person.name);
				dwr.util.setValue("tableId" + id, person.userId);
				dwr.util.setValue("tableEmail" + id, person.email);
				dwr.util.setValue("tableCreatedBy" + id, person.createdBy);
				roles = "";
				if (person.roles.length > 0) {
					for (var j = 0; j < person.roles.length; j++) {
						if (j > 0) roles += ", ";
						roles += person.roles[j].roleName;
					}
				}
				dwr.util.setValue("tableRoles" + id, roles);
				dwr.util.byId("pattern" + id).style.display = ""; // officially we should use table-row, but IE prefers "" for some reason
				peopleCache[id] = person;
			}
		});
	}

	function editClicked(eleid) {
		// we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
		var person = peopleCache[eleid.substring(4)];
		dwr.util.setValues(person);
	}

	function deleteClicked(eleid) {
		// we were an id of the form "delete{id}", eg "delete42". We lookup the "42"
		var person = peopleCache[eleid.substring(6)];
		if (confirm("Are you sure you want to delete " + person.name + "?")) {
			dwr.engine.beginBatch();
			UserService.deleteUserById(person.userId);
			fillTable();
			dwr.engine.endBatch();
		}
	}

	function writePerson() {
		var person = { id:viewed, name:null, email:null, createdBy:null };
		dwr.util.getValues(person);

		dwr.engine.beginBatch();
		UserService.modifyUser(person);
		fillTable();
		dwr.engine.endBatch();
	}

	function clearPerson() {
		viewed = -1;
		dwr.util.setValues({ id:-1, name:null, email:null, createdBy:null });
	}

	$(document).ready(function() { init(); });
</script>
<h3>All User</h3>
<table border="1" class="rowed grey">
  <thead>
    <tr>
      <th>User</th>
      <th>User ID</th>
      <th>Email</th>
      <th>Created by</th>
      <th>Roles</th>
      <th>Actions</th>
    </tr>
  </thead>
  <tbody id="peoplebody">
    <tr id="pattern" style="display:none;">
      <td><span id="tableName">Name</span></td>
      <td align="center"><span id="tableId">Id</span></td>
      <td><span id="tableEmail">Email</span></td>
      <td><span id="tableCreatedBy">Created By</span></td>
      <td><span id="tableRoles">Roles</span></td>
      <td>
        <input id="edit" type="button" value="Edit" onclick="editClicked(this.id)"/>
        <input id="delete" type="button" value="Delete" onclick="deleteClicked(this.id)"/>
      </td>
    </tr>
  </tbody>
</table>
<h3>Edit User</h3>
<table class="plain">
  <tr>
    <td>Name:</td>
    <td><input id="name" type="text" size="30"/></td>
  </tr>
  <tr>
    <td>Email:</td>
    <td><input id="email" type="text" size="40"/></td>
  </tr>
  <tr>
    <td colspan="2" align="right">
      <small>(ID=<span id="userId">-1</span>)</small>
      <input type="button" value="Save" onclick="writePerson()"/>
      <input type="button" value="Clear" onclick="clearPerson()"/>
   </td>
  </tr>
</table>