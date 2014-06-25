<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>PO管理</title>
	<script>
		
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#relGroup").focus();
		});
	</script>
</head>

<body>
	<h4>TASK LIST</h4>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>taskId</th><th>eventType</th><th>userId</th><th>triggerTime</th><th>taskPriority</th><th>inputParas</th><th>Status</th><th>actions</th></tr></thead>
		<tbody>
		<c:forEach items="${tasks}" var="task">
			<tr>
			  <td>${task.id}</td>
				<td>${task.eventType}</td>
				<td>${task.userId}</td>
				<td>${task.triggerTime}</td>
				<td>${task.taskPriority}</td>
				<td>${task.inputParas}</td>
				<td>${task.taskStatus}</td>
				<td>
	    				<a href="../execute/${task.id}"><i class="icon-play" title="execute"></i></a><a href="../logs/${task.id}" id="editLink-${task.id}"><i class="icon-list" title="logs"></i></a><a href="../update/${task.id}" id="editLink-${task.id}"><i class="icon-edit" title="update"></i></a> <a href="../delete/${task.id}"><i class="icon-remove" title="delete"></i></a><a href="../copy/${task.id}"><i class="icon-file" title="copy"></i></a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<a class="btn" href="../create">创建任务</a>
</body>
</html>
