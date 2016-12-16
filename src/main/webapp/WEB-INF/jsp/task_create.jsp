<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/public/css/Admin.css"/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<c:url value="/public/js/jquery.min.js"/>"></script>
<title>Task Create</title>
</head>
<body>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="nen">
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div class="noidung" align="center">
		<h1 align="center">${task.header}</h1>
		<form name="form" action="${task.action}" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="hidden" name="id" value="${user.id}"><br>
			<input type="text" name="username" placeholder="assigneeid" class="keyword" required value="${task.assignee}"><br>
			<input type="password" name="password" placeholder="description" class="keyword" required value="${task.description}"><br>
			<label style="margin-left: -30%;" >Deadline:</label><br>
			<input type="date" name="dob" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${task.deadline}"/>'>
			<br> 
			<input class="nutdn" type="submit" value="Lưu">
		</form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>
</body>
</html>