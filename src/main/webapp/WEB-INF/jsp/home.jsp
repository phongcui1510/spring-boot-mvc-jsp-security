<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Home</title>
<link href="public/css/Admin.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
	.link {
	     background:none!important;
	     border:none; 
	     padding:0!important;
	     font: inherit;
	     /*border is optional*/
	     border-bottom:1px solid #444; 
	     cursor: pointer;
	}
</style>
</head>
<body>
	<div class="nen">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="noidung" align="center"></div>
	</div>
</body>
</html>