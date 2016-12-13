<%@ page language="java" contentType="text/html; charset=utf8"	pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/public/css/Admin.css"/>" rel="stylesheet" type="text/css"/>
<title>Create User</title>
</head>
<body>
<c:set var="context" value="${pageContext.request.contextPath}" />
	<div class="nen">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="noidung" align="center">
			<h1 align="center">THÊM TÀI KHOẢN</h1>
			<form name="form" action="${context}/user/create" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="text" name="username" placeholder="Tên Tài Khoản" class="keyword" required><br>
				<input type="password" name="password" placeholder="Mật khẩu" class="keyword" required><br>
				<input type="password" name="passwordRepeated" placeholder="Nhập lại mật khẩu" class="keyword"><br>
				<input type="text" name="fullName" placeholder="Tên người dùng" class="keyword"><br> 
				<input type="text" name="email" placeholder="Email" class="keyword" required><br> 
				<label style="margin-left: -30%;">Ngày sinh</label><br>
				<input type="date" name="dob">
				<br> 
				<select class="form-control" name="role" style="width: 38%;" required>
					<option value="">Quyền đăng nhập</option>
					<option value="USER1">Người quy định minh chứng</option>
					<option value="USER2">Người giao việc nhập quy định minh chứng</option>
					<option value="USER3">Người nhập minh chứng</option>
					<option value="USER4">Người kiểm tra minh chứng</option>
				</select><br>
				<input class="nutdn" type="submit" value="Lưu">
			</form>
		</div>
	</div>
</body>
</html>