<%@ tag body-content="empty" %>
<%@ attribute name="page" required="true" type="com.excilys.computerdatabase.helper.Page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="pagination">
<c:choose>
	<c:when test="${page.offset > 0}">
	<li>
 		<a href="dashboard?page=${page.page}&offset=${ page.offset-1 }&order=${page.order}&search=${page.search}&sort=${page.sort}" aria-label="Previous">
			<span aria-hidden="true">&laquo;</span>
		</a>
	</li>
	</c:when>
</c:choose>

<c:choose>
 <c:when test="${page.offset-2 >= 0 && page.offset+2 < page.nbPage+1}">
 	<c:forEach var="i" begin="${page.offset-2}" step="1" end="${page.offset+2}">
		<c:choose>
 		<c:when test="${i == page.offset}">
 				<li><a href="dashboard?page=${page.page}&offset=${i}&order=${page.order}&search=${page.search}&sort=${page.sort}"><b>${i+1}</b></a></li>
 			</c:when>
 			<c:otherwise>
 				<li><a href="dashboard?page=${page.page}&offset=${i}&order=${page.order}&search=${page.search}&sort=${page.sort}">${i+1}</a></li>
 		</c:otherwise>
		</c:choose>
 	</c:forEach>
 </c:when>

 <c:when test="${page.offset-2 < 0}">
 	<c:choose>
 		<c:when test="${page.nbPage < 4}">
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
 		</c:when>
 		<c:otherwise>
 			<c:forEach var="i" begin="0" step="1" end="4">
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
 </c:when>
 
 <c:when test="${page.offset+2 > page.nbPage}">
 	<c:forEach var="i" begin="${page.nbPage-4}" step="1" end="${page.nbPage}">
		<c:choose>
 		<c:when test="${i == page.offset}">
 				<li><a href="dashboard?page=${page.page}&offset=${i}&order=${page.order}&search=${page.search}&sort=${page.sort}"><b>${i+1}</b></a></li>
 			</c:when>
 			<c:otherwise>
 				<li><a href="dashboard?page=${page.page}&offset=${i}&order=${page.order}&search=${page.search}&sort=${page.sort}">${i+1}</a></li>
 		</c:otherwise>
		</c:choose>
 	</c:forEach>
 </c:when>
</c:choose>

<c:choose>
	<c:when test="${page.offset < page.nbPage}">
	<li>
 		<a href="dashboard?page=${page.page}&offset=${ page.offset+1 }&order=${page.order}&search=${page.search}&sort=${page.sort}" aria-label="Next">
			<span aria-hidden="true">&raquo;</span>
		</a>
	</li>
	</c:when>
</c:choose>
</ul>