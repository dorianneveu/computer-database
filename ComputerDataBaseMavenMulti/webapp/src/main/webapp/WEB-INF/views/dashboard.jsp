<%@page import="java.util.List"%>
<%@page import="com.excilys.computerdatabase.model.Computer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <%@include file="header.jsp" %>
    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                 <c:out value="${nbFound}"/></td> <spring:message code="dashboard.found"/>
            </h1>
            <c:url value="/j_spring_security_logout" var="logoutUrl" />
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="dashboard?page=${page.page }&offset=0&order=${page.order}&search=&sort=${page.sort}" method="GET" class="form-inline">
						<input type="hidden" id="page" name="page" value="${page.page }"/>
						<input type="hidden" id="offset" name="offset" value="0"/>
						<input type="hidden" id="sort" name="sort" value="${page.sort}" />
                        <input type="search" id="search" name="search" class="form-control" placeholder="<spring:message code="dashboard.search"/>" />
                        <input type="submit" id="searchsubmit" value="<spring:message code="dashboard.filter"/>"
                        class="btn btn-primary" />
                    </form>
                </div>
                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="dashboard.add"/></a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="dashboard.edit"/></a>
                </div>
                </sec:authorize>
            </div>
        </div>

        <form id="deleteForm" action="dashboard" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->
                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                        	<spring:message code="dashboard.name"/>
                        	<a href="dashboard?page=${page.page}&offset=0&order=cmp.name&search=${page.search}&sort=ASC" ><i class="fa fa-arrow-circle-up"></i></a>
                        	<a href="dashboard?page=${page.page}&offset=0&order=cmp.name&search=${page.search}&sort=DESC" ><i class="fa fa-arrow-circle-down"></i></a>
                        </th>
                        <th>
                            <spring:message code="dashboard.introduced"/>
                            <a href="dashboard?page=${page.page}&offset=0&order=cmp.introduced&search=${page.search}&sort=ASC" ><i class="fa fa-arrow-circle-up"></i></a>
                            <a href="dashboard?page=${page.page}&offset=0&order=cmp.introduced&search=${page.search}&sort=DESC" ><i class="fa fa-arrow-circle-down"></i></a>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <spring:message code="dashboard.discontinued"/>
                            <a href="dashboard?page=${page.page}&offset=0&order=cmp.discontinued&search=${page.search}&sort=ASC" ><i class="fa fa-arrow-circle-up"></i></a>
                            <a href="dashboard?page=${page.page}&offset=0&order=cmp.discontinued&search=${page.search}&sort=DESC" ><i class="fa fa-arrow-circle-down"></i></a>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <spring:message code="dashboard.company"/>
                            <a href="dashboard?page=${page.page}&offset=0&order=company_id&search=${page.search}&sort=ASC" ><i class="fa fa-arrow-circle-up"></i></a>
                            <a href="dashboard?page=${page.page}&offset=0&order=company_id&search=${page.search}&sort=DESC" ><i class="fa fa-arrow-circle-down"></i></a>
                        </th>
                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                <c:forEach var="computer" items="${computers}">
	                <tr>
	                	<td class="editMode"> 
	                		<input type="checkbox" name="cb" class="cb" value="${computer.id}">
	                	</td>
	                	<td>
	                		<a href="editComputer?id=${computer.id}" onclick=""><c:out value="${computer.name}"/></a>
	                	</td>
	                	<td><c:out value="${computer.introduced}"/></td>
	                	<td><c:out value="${computer.discontinued}"/></td>
	                	<td><c:out value="${computer.companyName}"/></td>
	                </tr>
				</c:forEach>

                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
        
         <c:if test="${page.page > 0}"><td>
         	<my:pagination page="${page}" />
		</c:if>
        <div class="btn-group btn-group-sm pull-right" role="group" >
	        <form action="dashboard" Method="GET">
		    	<input type="submit" name="page" value="10" />
		    	<input type="submit" name="page" value="50" />
		    	<input type="submit" name="page" value="100" />
			</form>
		</div>
    </footer>
    
    <script type="text/javascript">
		var strings = new Array();
		strings['dashboard.view'] = "<spring:message code='dashboard.view' javaScriptEscape='true' />";
		strings['dashboard.edit'] = "<spring:message code='dashboard.edit' javaScriptEscape='true' />";
	</script>
<script src="${request.getContextPath()}js/jquery.min.js"></script>
<script src="${request.getContextPath()}js/bootstrap.min.js"></script>
<script src="${request.getContextPath()}js/dashboard.js"></script>

</body>
</html>