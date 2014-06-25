<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="header" class="span12">
	<div id="title">
	    	<h1>SAP-DM ShowCase</h1>
	    	<shiro:user>
			<span class="pull-right">Hello, <shiro:principal property="name"/>!!</span>
		</shiro:user>
	</div>
		
	<div id="menu">
		<ul class="nav nav-tabs">
			<shiro:user>
			<shiro:hasPermission name="user:view">
				<li id="user-tab"><a href="${ctx}/account/user/" >帐号列表</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="group:view">
				<li id="group-tab"><a href="${ctx}/account/group/">权限组列表</a></li>
			</shiro:hasPermission>
			    <li id="company-tab"><a href="${ctx}/system/company/">公司列表</a></li>
			    <li id="event-tab"><a href="${ctx}/system/event/">事件列表</a></li>
				<li id="task-tab"><a href="${ctx}/task/list/">任务列表</a></li>
			<li><a href="${ctx}/logout">退出登录</a></li>
			</shiro:user>
			<shiro:guest>
				<li class="active"><a href="${ctx}/login">登录</a></li>
			</shiro:guest>
		</ul>
		
	</div>
</div>