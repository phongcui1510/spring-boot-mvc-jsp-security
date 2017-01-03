<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/public/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/public/css/Admin.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/public/css/easyTree.css"/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<c:url value="/public/js/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/public/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/public/js/easyTree.js"/>"></script>
<title>Proof List</title>
</head>
<body>
<c:set var="context" value="${pageContext.request.contextPath}" />
	<div class="nen">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="noidung">
			<h1 align="center">DANH SÁCH MINH CHỨNG</h1>
			<div class="col-md-9">
				<div class='easy-tree'>
					<ul>
						<c:forEach var="proof" items="${proofs}">
							<c:set var="node" value="${proof}" scope="request"/>
							<jsp:include page="node.jsp"/>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$('.easy-tree').EasyTree(
			        {
			            addable: true,
			            editable: true,
			            deletable: true,
			            viewable: true,
			            i18n: {
			            	deleteNull: 'Vui lòng chọn mục bạn muốn xóa',
			                deleteConfirmation: 'Bạn có chắc muốn xóa？',
			                confirmButtonLabel: 'OK',
			                editNull: 'Vui lòng chọn mục bạn muốn chỉnh sửa',
			                editMultiple: '',
			                addMultiple: '',
			                collapseTip: '',
			                expandTip: '',
			                selectTip: '',
			                unselectTip: '',
			                editTip: 'Chỉnh sửa',
			                addTip: 'Thêm',
			                deleteTip: 'Xóa',
			                cancelButtonLabel: 'Cancel'
			            },
			            role: '${currentUser.role}'
			        }
			    );
			
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