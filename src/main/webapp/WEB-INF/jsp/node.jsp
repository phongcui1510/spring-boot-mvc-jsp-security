<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<li <c:if test="${node.type == 'BRANCH'}">class="parent_li"</c:if> id="${node.id}">${node.title}
	<c:if test="${node.type == 'BRANCH'}">
		<ul>
			<c:forEach var="node" items="${node.proofs}">
				<c:set var="node" value="${node}" scope="request"/>
				<jsp:include page="node.jsp"/>
			</c:forEach>
		</ul>
	</c:if>
</li>