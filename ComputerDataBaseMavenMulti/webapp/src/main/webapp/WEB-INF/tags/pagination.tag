<%@ tag body-content="empty" %>
<%@ attribute name="page" required="true" type="com.excilys.computerdatabase.helper.Page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="pagination">
	<li>
 		<a href="dashboard?page=${page.page}&offset=${ page.offset-1 }&order=${page.order}&search=${page.search}&sort=${page.sort}" aria-label="Previous">
			<span aria-hidden="true">&laquo;</span>
		</a>
	</li>

<c:choose>
 <c:when test="${page.nbPage > 15}">
 	<c:forEach var="i" begin="0" step="5" end="${page.nbPage}">
 		<li><a href="dashboard?page=${page.page}&offset=${i}&order=${page.order}&search=${page.search}&sort=${page.sort}">${i+1}</a></li>
 	</c:forEach>
 </c:when>

 <c:otherwise>
 	<c:forEach var="i" begin="0" step="1" end="${page.nbPage}">
		<c:choose>
 			<c:when test="${i == page.offset}">
 				<li><a href="dashboard?page=${page.page}&offset=${i}&order=${page.order}&search=${page.search}&sort=${page.sort}"><b>${i+1}</b></a></li>
 			</c:when>
 			<c:otherwise>
 				<li><a href="dashboard?page=${page.page}&offset=${i}&order=${page.order}&search=${page.search}&sort=${page.sort}">${i+1}</a></li>
 			</c:otherwise>
 		</c:choose>
 	</c:forEach>
 </c:otherwise>
</c:choose>
	<li>
 		<a href="dashboard?page=${page.page}&offset=${ page.offset+1 }&order=${page.order}&search=${page.search}&sort=${page.sort}" aria-label="Next">
			<span aria-hidden="true">&raquo;</span>
		</a>
	</li>
</ul>