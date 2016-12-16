<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Search Result</title>
</head>
<body>
<c:set var="context" value="${pageContext.request.contextPath}" />
<table>
	<tr>
		<th>Tên</th>
		<th>Ngày Bắt Đầu</th>
		<th>Ngày Kết Thúc</th>
		<th>Ngày Tạo</th>
		<th>Người Tạo</th>
		<th>Type</th>
		<th></th>
	</tr>
	<c:forEach items="${proofs}" var="proof">
		<tr>
			<td>${proof.title}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${proof.startDate}" /></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${proof.endDate}" /></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${proof.createdDate}" /></td>
			<td>${proof.createdBy}</td>
			<td>${proof.type}</td>
			<td>
				<a href="${context}/proof/view?id=${proof.id}" style="color: blue">View</a>
				<a href="${context}/proof/edit?id=${proof.id}" style="color: blue">Edit</a>
				<a id="deleteProof${proof.id}" url="${context}/proof/delete?id=${proof.id}" style="color: blue; cursor: pointer;">Delete</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>