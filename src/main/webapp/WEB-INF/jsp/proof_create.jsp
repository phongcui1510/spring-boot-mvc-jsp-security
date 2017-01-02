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
<title>Proof Create</title>
</head>
<body>
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<div class="nen">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="noidung" align="center">
			<h1 align="center">${proof.header}</h1>
			<form name="form" action="${proof.action}" method="post" enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="hidden" name="id" value="${proof.id}"><br>
				<input type="text" name="title" placeholder="Tên Minh Chứng" class="keyword" required value="${proof.title}"><br>
				<textarea type="text" name="description" placeholder="Mô Tả" class="keyword" required cols="50" style="resize: none;">${proof.description}</textarea><br>
				<label>Ngày Bắt Đầu</label><br>
				<input type="date" name="startDate" placeholder="Ngày Bắt Đầu" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${proof.startDate}"/>'><br>
				<label>Ngày Kết Thúc</label><br>
				<input type="date" name="endDate" placeholder="Ngày Kết Thúc" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${proof.endDate}"/>'><br>
				<select name="type" class="keyword" required>
					<option value="">Choose type</option>
					<c:if test="${currentUser != null && currentUser.role == 'USER1'}">
						<option value="BRANCH">Branch</option>
					</c:if>
					<c:if test="${currentUser != null && currentUser.role == 'USER3'}">
						<option value="LEAF">Leaf</option>
					</c:if>
				</select><br>
				<select name="parentId" class="keyword">
					<option value="">Choose parent</option>
					<c:forEach items="${proof.proofs}" var="proof">
						<option value="${proof.id}">${proof.title}</option>
					</c:forEach>
				</select><br>
				Please select a file to upload : <input type="file" name="file" /><br>
				<input class="nutdn" type="submit" value="Lưu">
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("select[name='parentId']").val("${proof.parentId}");
			$("select[name='type']").val("${proof.type}");
		});
	</script>
</body>
</html>