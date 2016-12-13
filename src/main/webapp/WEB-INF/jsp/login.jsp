<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="public/css/TrangChu.css" rel="stylesheet" type="text/css"/>
<link href="public/css/Admin.css" rel="stylesheet" type="text/css"/>
<title>Login</title>
</head>
<body>
	<div class="nen">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="noidung" align="center">
			<form action="login" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				Tên Tài Khoản:<input type="text" name="username"><br>
				Mật Khẩu: <input type="password" name="password" style="margin-left: 42px;"><br> 
					<input class="nutdn" type="submit" value="Đăng Nhập">
			</form>

		</div>
	</div>
</body>
</html>