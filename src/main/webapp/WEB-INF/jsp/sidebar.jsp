<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<img src="<c:url value="/public/img/SPKT.jpg"/>" alt="" style="width: 100%;" />
	<div class="main_ngang">
		<ul>
			<li><a href="Admin.html"><b>Trang Chủ</b></a></li>
			<li><a href="Admin_GT.html"><b>Giới thiệu</b></a></li>
			<li style="float: right">
				<c:if test="${currentUser == null}">
					<a href="login">Đăng Nhập</a>
				</c:if>
				<c:if test="${currentUser != null}">
					<form action="${context}/logout" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						Login as ${currentUser.role}
<!-- 							<a style="float: right">Đăng Xuất</a> -->
						<button type="submit" class="link">Đăng Xuất</button>
					</form>
				</c:if>
			</li>
		</ul>
		<div class="clear"></div>
	</div>
<div class="main_menu" style="float: left">
	<ul>
		<li><a href="Admin_TT.html"><b>Tin tức</b></a></li>
		<li><a href="Admin_TB.html"><b>Thông báo</b></a></li>
		<c:if test="${currentUser != null &&  currentUser.role =='ADMIN'}">
			<li><a href="${context}/user/list"><b>Quản lý tài khoản</b></a></li>
		</c:if>
		<c:if test="${currentUser != null &&  currentUser.role =='ADMIN'}">
			<li><a href="${context}/proof/list"><b>Xem minh chứng</b></a></li>
		</c:if>
		<c:if test="${currentUser != null &&  currentUser.role =='ADMIN'}">
			<li><a href="${context}/proof/create"><b>Tạo minh chứng</b></a></li>
		</c:if>
		<li><a href="Admin_NCV.html"><b>Nhập chức vụ</b></a></li>
	</ul>
</div>