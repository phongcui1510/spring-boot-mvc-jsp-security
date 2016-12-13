<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/public/css/Admin.css"/>" rel="stylesheet" type="text/css"/>
<title>Proof Create</title>
</head>
<body>
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<div class="nen">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="noidung" align="center">
			<h1 align="center">THÊM MINH CHỨNG</h1>
			<form name="form" action="${context}/proof/create" method="post" enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="text" name="title" placeholder="Tên Minh Chứng" class="keyword" required><br>
				<input type="text" name="description" placeholder="Mô Tả" class="keyword" required><br>
				<input type="date" name="startDate" placeholder="Ngày Bắt Đầu" class="keyword"><br>
				<input type="date" name="endDate" placeholder="Ngày Kết Thúc" class="keyword"><br>
				Please select a file to upload : <input type="file" name="file" /><br>
				<input class="nutdn" type="submit" value="Lưu">
			</form>
		</div>
	</div>
</body>
</html>