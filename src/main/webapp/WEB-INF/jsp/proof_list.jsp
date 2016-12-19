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
<title>Proof List</title>
</head>
<body>
<c:set var="context" value="${pageContext.request.contextPath}" />
	<div class="nen">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="noidung" align="center">
			<h1 align="center">DANH SÁCH MINH CHỨNG</h1>
			<table>
				<tr>
					<th>ID</th>
					<th>Tên</th>
					<th>Ngày Bắt Đầu</th>
					<th>Ngày Kết Thúc</th>
					<th>Ngày Tạo</th>
					<th>Người Tạo</th>
					<th>Type</th>
					<th></th>
				</tr>
				<c:forEach items="${proofs}" var="proof">
					<tr <c:if test="${not empty proof.proofs}">style="color: green"</c:if>>
						<td>${proof.id}</td>
						<td>${proof.title}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${proof.startDate}" /></td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${proof.endDate}" /></td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${proof.createdDate}" /></td>
						<td>${proof.createdBy}</td>
						<td>${proof.type}</td>
						<td>
							<a href="${context}/proof/view?id=${proof.id}" style="color: blue">View</a>
							<c:if test="${proof.type == 'LEAF' &&  currentUser != null && currentUser.role =='USER3'}">
								<a href="${context}/proof/edit?id=${proof.id}" style="color: blue">Edit</a>
								<a id="deleteProof${proof.id}" url="${context}/proof/delete?id=${proof.id}" style="color: blue; cursor: pointer;">Delete</a>
							</c:if>
							<c:if test="${proof.type == 'LEAF' &&  currentUser != null && currentUser.role =='USER1'}">
								<a href="${context}/proof/edit?id=${proof.id}" style="color: blue">Edit</a>
							</c:if>
							<c:if test="${proof.type == 'BRANCH' &&  currentUser != null && currentUser.role =='USER1'}">
								<a href="${context}/proof/edit?id=${proof.id}" style="color: blue">Edit</a>
								<a id="deleteProof${proof.id}" url="${context}/proof/delete?id=${proof.id}" style="color: blue; cursor: pointer;">Delete</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("a[id^='deleteProof']").click(function(){
				var r = confirm("Are you sure want to delete this proof?");
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