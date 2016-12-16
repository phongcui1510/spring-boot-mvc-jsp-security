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
<title>Proof Search</title>
</head>
<body>
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<div class="nen">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="noidung" align="center">
			<h1 align="center">${proof.header}</h1>
			<form id="searchForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="text" name="id" placeholder="ID" class="keyword"><br>
				<input type="text" name="title" placeholder="Tên Minh Chứng" class="keyword"><br>
				<input class="nutdn" type="button" value="Tìm" id="submitSearch">
			</form>
			<br/>
			<div id="searchResult"></div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#submitSearch").click(function(){
				$.ajax({
                    url: "${context}/proof/submitSearch",
                    method: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    data: $("#searchForm").serializeArray(),
                    success: function (response) {
                    	$("#searchResult").html(response);
                    }
                });
			});
		});
	</script>
</body>
</html>