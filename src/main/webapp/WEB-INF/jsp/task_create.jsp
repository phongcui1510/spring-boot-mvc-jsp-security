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
			<c:if test="${task.header == 'TẠO NHIỆM VỤ'}">
<%-- 				<input type="text" name="assigneeid" placeholder="Giao việc cho ..." class="keyword" required value="${task.assignee.username}"><br> --%>
				<label style="margin-left: -30%;" >Nguời nhận việc:</label><br>
				<select name="assigneeid" required>
					<c:forEach var="assignee" items="${task.assigneeLst}">
						<option value="${assignee.id}">${assignee.username}</option>
					</c:forEach>
				</select><br/>
			</c:if>
			<c:if test="${task.header != 'TẠO NHIỆM VỤ'}">
				<label style="margin-left: -30%;" >Nguời giao việc:</label><br>
				<input type="text" class="keyword" required value="${task.assigner.username}"><br>
				<input type="hidden" name="assignerid" value="${task.assigner.id}">
				<input type="hidden" name="id" value="${task.id}">
			</c:if>
			<input type="text" name="description" placeholder="Mô Tả Công Việc" class="keyword" value="${task.description}"><br>
			<label style="margin-left: -30%;" >Deadline:</label><br>
			<input type="date" name="deadline" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${task.deadline}"/>'><br/>
			<label style="margin-left: -30%;" >Tình trạng:</label><br>
			<select name="status">
				<option value="NEW">TẠO MỚI</option>
				<option value="INPROGRESS">ĐANG LÀM</option>
				<option value="DONE">HOÀN TẤT</option>
			</select>
			<br> 
			<input class="nutdn" type="submit" value="Lưu">
		</form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("select").val("${task.status}");
	});
</script>
</body>
</html>