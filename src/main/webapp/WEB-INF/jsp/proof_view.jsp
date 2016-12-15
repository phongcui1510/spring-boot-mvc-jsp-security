<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/public/css/Admin.css"/>" rel="stylesheet" type="text/css"/>
<title>Proof View</title>
</head>
<body>
<c:set var="context" value="${pageContext.request.contextPath}" />
	<div class="nen">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="noidung" align="center">
			<h1 align="center">XEM MINH CHỨNG</h1>
			<table>
				<tr>
					<td style="width: 30%">Tên Minh Chứng</td>
					<td>${proof.title}</td>
				</tr>
				<tr>
					<td style="width: 30%">Mô Tả</td>
					<td>${proof.description}</td>
				</tr>
				<tr>
					<td style="width: 30%">Ngày Bắt Đầu</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${proof.startDate}" /></td>
				</tr>
				<tr>
					<td style="width: 30%">Ngày Kết Thúc</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${proof.endDate}" /></td>
				</tr>
				<tr>
					<td style="width: 30%">Ngày tạo</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${proof.createdDate}" /></td>
				</tr>
				<tr>
					<td style="width: 30%">Người tạo</td>
					<td>${proof.createdBy}</td>
				</tr>
				<tr>
					<td style="width: 30%">Người chỉnh sửa</td>
					<td>${proof.modifiedBy}</td>
				</tr>
				<tr>
					<td style="width: 30%">Ngày chỉnh sửa</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${proof.modifiedDate}" /></td>
				</tr>
				<tr>
					<td style="width: 30%">File đính kèm</td>
					<td>
						<a href="${context}/proof/download?id=${proof.id}" style="color: blue">Download</a>
						<a href="#" style="color: blue">View</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>