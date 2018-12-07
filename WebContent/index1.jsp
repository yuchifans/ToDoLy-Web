<%@ page import="com.siqi.taskadmin.bean.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.siqi.taskadmin.util.DataUtil"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to Todoly</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="Todoly">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%
	List<Task> tasks = (List<Task>) request.getSession().getAttribute("tasks");
%>
<script type="text/javascript">
	$(function() {

		$("#datepicker").datepicker();
<%if (tasks != null && tasks.size() > 0) {
				for (Task task : tasks) {
					out.println("$(" + "\"" + "#datepicker" + task.getId() + "\"" + ").datepicker();");

				}
			}%>
	});
</script>
<script type="text/javascript">
	function save_newtask() {
		if (validate_addform()) {
			$.ajax({
				url : "AddNewTaskServlet",
				type : "POST",
				data : {
					newTitle : document.getElementById('newTitle').value,
					newDuedate : document.getElementById('datepicker').value,
					newProject : document.getElementById('newProject').value,
					newStatus : document.getElementById('newStatus').value,
				},
				success : function(result) {
					alert("success!");
					window.location.href="index1.jsp";
					
				}
			}).fail(function(data) {
				alert("failure!");
			});
		}
	}

	function validate_required(field, alerttxt) {
		with (field) {
			if (value == null || value == "") {
				alert(alerttxt);
				return false
			} else {
				return true
			}
		}
	}

	function validate_length(field, maxlength, alerttxt) {
		with (field) {
			if (value.length > maxlength) {
				alert(alerttxt);
				return false
			} else {
				return true
			}
		}
	}

	function validate_addform() {

		if (validate_required(newTitle, "Title must be filled out!") == false) {
			newTitle.focus();
			return false
		}

		if (validate_length(newTitle, 16,
				"Title can not be longer than 16 characters!") == false) {
			newTitle.focus();
			return false
		}

		if (validate_required(datepicker, "Duedate must be filled out!") == false) {
			datepicker.focus();
			return false
		}
		if (validate_required(newProject, "Project must be filled out!") == false) {
			newProject.focus();
			return false
		}

		if (validate_length(newProject, 30,
				"Project can not be longer than 30 characters!") == false) {
			newProject.focus();
			return false
		}
		return true
	}
</script>
</head>
<body>


	<table class="responstable">

		<tr>
			<th width="10"></th>
			<th width="60">Task Id</th>
			<th width="220">Title</th>
			<th width="100">Duedate</th>
			<th width="350">Project</th>
			<th width="90">Status</th>
			<th>Operation</th>
		</tr>
		<%
			if (tasks != null && tasks.size() > 0) {
				for (Task task : tasks) {
					String status = task.getStatus() == false ? "In progress" : "Completed";
		%>
		<tr id="task<%=task.getId()%>">
			<td><input type="checkbox" name="<%=task.getId()%>"
				value="<%=task.getId()%>" /></td>
			<td><%=task.getId()%></td>
			<td><%=task.getTitle()%></td>
			<td><%=DataUtil.dateToString(task.getDuedate())%></td>
			<td><%=task.getProject()%></td>
			<td><%=status%></td>
			<td><input type="button" value="Edit"
				onClick="document.getElementById('task<%=task.getId()%>hidden').style.display = '',
						document.getElementById('task<%=task.getId()%>').style.display = 'none'">
				<input type="button" value="Delete"
				onClick="if(confirm('Confirm to delete this task？'))location.href='DeleteTaskServlet?taskid=<%=task.getId()%>'"></td>
		</tr>
		<form action="EditTaskServlet" method="post">
			<tr id="task<%=task.getId()%>hidden" style="display: none">
				<td><input type="checkbox" name="<%=task.getId()%>"
					value="<%=task.getId()%>" /></td>
				<td><%=task.getId()%><input type="hidden" name="taskid"
					value="<%=task.getId()%>"></td>
				<td><%=task.getTitle()%><input type="hidden" name="title"
					maxlength="16" value="<%=task.getTitle()%>"></td>
				<td><input type="text" name="dueDate"
					id="datepicker<%=task.getId()%>" maxlength="10"
					style="width: 90px; height: 20px;"
					value="<%=DataUtil.dateToString(task.getDuedate())%>"></td>
				<td><input type="text" name="project" maxlength="30"
					value="<%=task.getProject()%>" style="width: 340px; height: 20px;"></td>
				<td><select name="status" style="width: 75px; height: 20px;">
						<%
							if (status.equals("In progress")) {
						%>
						<option value="0" selected="selected">In progress</option>
						<option value="1">Completed</option>
						<%
							} else if (status.equals("Completed")) {
						%>
						<option value="0">In progress</option>
						<option value="1" selected="selected">Completed</option>
						<%
							}
						%>
				</select></td>
				<td><input type="Submit" name="submit"><input
					type="button" value="cancel"
					onClick="{ document.getElementById('task<%=task.getId()%>hidden').style.display = 'none',
						document.getElementById('task<%=task.getId()%>').style.display = ''}"></td>
			</tr>
		</form>

		<%
			}
			}
		%>


		<tr id="newtask" style="display: none">
			<td></td>
			<td></td>
			<td><input type="text" name="newTitle" id="newTitle"
				maxlength="16" style="width: 210px; height: 20px;"></td>
			<td><input type="text" name="newDuedate" id="datepicker"
				maxlength="10" style="width: 90px; height: 20px;"></td>
			<td><input type="text" name="newProject" id="newProject"
				maxlength="30" style="width: 340px; height: 20px;"></td>
			<td><select name="newStatus" id="newStatus"
				style="width: 75px; height: 20px;">
					<option value="0">In progress</option>
					<option value="1">Completed</option>
			</select></td>
			<td><input type="submit" name="submit"
				onClick=" save_newtask() "><input type="button"
				value="Cancel"
				onClick="{ document.getElementById('newtask').style.display = 'none'}"></td>
		</tr>

	</table>





	<table>
		<tr>

			<td>
				<div class="type-1">
					<div>
						<b class="btn btn-2"
							onclick="{ document.getElementById('newtask').style.display = '' }">
							<span class="txt">Add a new task</span>
						</b>
					</div>
				</div>
			</td>
			<td>
				<div class="type-1">
					<div>
						<b class="btn btn-2"
							onclick="{ if(confirm('Confirm to leave this page？'))location.href='filterTask.jsp'}">
							<span class="txt">filter tasks by project</span>
						</b>
					</div>
				</div>
			</td>

		</tr>
	</table>

</body>
</html>
