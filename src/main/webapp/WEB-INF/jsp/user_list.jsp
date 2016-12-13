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
<title>User List</title>
</head>
<body>
<c:set var="context" value="${pageContext.request.contextPath}" />
	<div class="nen">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<a href="${context}/user/create" style="float: right; color: blue; margin-top: 1%">Tạo mới người dùng</a>
		<div class="noidung" align="center">
			<h1 align="center">DANH SÁCH NGƯỜI DÙNG</h1>
			<table>
				<tr>
					<th>Tên Đăng Nhập</th>
					<th>Họ Tên</th>
					<th>Ngày Sinh</th>
					<th>Email</th>
					<th>Số Điện Thoại</th>
					<th>Role</th>
					<th></th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.username}</td>
						<td>${user.fullName}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${user.dob}" /></td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td>${user.role}</td>
						<td>
							<a href="${context}/user/edit?id=${user.id}" style="color: blue">Edit</a>
							<a id="deleteUser${user.id}" url="${context}/user/delete?id=${user.id}" style="color: blue; cursor: pointer;">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("a[id^='deleteUser']").click(function(){
				var r = confirm("Are you sure want to delete this user?");
				if (r == true) {
					$.ajax({
                        url: $(this).attr("url"),
                        method: "get",
                        success: function (response) {
                        	alert("Delete successfull. Please refesh page to update");
                        }
                    });
				}
			});
		});
	</script>
</body>
</html>