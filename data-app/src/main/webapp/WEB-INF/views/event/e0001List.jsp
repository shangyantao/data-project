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
	<h4>PO列表</h4>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<form:form modelAttribute="e0001" action="${ctx}/event/e0001/findPOByCode" method="post" class="form-horizontal">
		<fieldset>
			
			<div class="control-group">
				<label for="relGroup" class="control-label">relGroup:</label>
				<div class="controls">
					<input type="text" id="relGroup" name="relGroup" size="50"  class="required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="relCode" class="control-label">relCode:</label>
				<div class="controls">
					<input type="text" id="relCode" name="relCode" size="50"  class="required" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="itemsForRelease" class="control-label">itemsForRelease:</label>
				<div class="controls">
					<input type="text" id="itemsForRelease" name="itemsForRelease" size="50" class="required"/>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit" class="btn btn-primary" type="submit" value="Send"/>&nbsp;	
			</div>
		</fieldset>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>relGroup</th><th>relCode</th><th>itemsForRelease</th><th>poNumber</th><th>vender</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${e0001s}" var="e0001">
			<tr>
				<td>${e0001.relGroup}</td>
				<td>${e0001.relCode}</td>
				<td>${e0001.itemsForRelease}</td>
				<td>${e0001.poNumber}</td>
				<td>${e0001.vendor}</td>
				<td>
	    				<a href="update/${e0001.id}" id="editLink-${user.id}">修改</a> <a href="delete/${e0001.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
