<%@page import="java.util.List"%>
<%@page import="com.excilys.computerdatabase.model.Computer"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="pg" uri="../WEB-INF/pagetag.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags/" %>
    <%@include file="header.jsp" %>
    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                 <c:out value="${nbFound}"/></td> computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="Dashboard?page=${page.limit }&offset=0&order=${page.order }&search=&sort=${page.sort}" method="GET" class="form-inline">
						<input type="hidden" id="page" name="page" value="${page.limit }"/>
						<input type="hidden" id="offset" name="offset" value="0"/>
						<input type="hidden" id="sort" name="sort" value="${page.sort}" />
                        <input type="search" id="search" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="AddComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="Dashboard" method="POST">
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
                        	Computer name
                        	<a href="Dashboard?page=${page.limit}&offset=0&order=computer.name&search=${page.search}&sort=ASC" >^</a>
                        	<a href="Dashboard?page=${page.limit}&offset=0&order=computer.name&search=${page.search}&sort=DESC" >v</a>
                        </th>
                        <th>
                            Introduced date
                            <a href="Dashboard?page=${page.limit}&offset=0&order=computer.introduced&search=${page.search}&sort=ASC" >^</a>
                            <a href="Dashboard?page=${page.limit}&offset=0&order=computer.introduced&search=${page.search}&sort=DESC" >v</a>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                            <a href="Dashboard?page=${page.limit}&offset=0&order=computer.discontinued&search=${page.search}&sort=ASC" >^</a>
                            <a href="Dashboard?page=${page.limit}&offset=0&order=computer.discontinued&search=${page.search}&sort=DESC" >v</a>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                            <a href="Dashboard?page=${page.limit}&offset=0&order=computer.company_id&search=${page.search}&sort=ASC" >^</a>
                            <a href="Dashboard?page=${page.limit}&offset=0&order=computer.company_id&search=${page.search}&sort=DESC" >v</a>
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
	                		<a href="EditComputer?id=${computer.id}" onclick=""><c:out value="${computer.name}"/></a>
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
        
		
        
         <c:if test="${page.limit > 0}"><td>
         	<my:pagination page="${page}" />
<%--               <pg:pagetag page="${page}" nbEachPage="${page.nbPage}" offset="${page.offset}"/> --%>
		</c:if>
        <div class="btn-group btn-group-sm pull-right" role="group" >
	        <form action="Dashboard" Method="GET">
		    	<input type="submit" name="page" value="10" />
		    	<input type="submit" name="page" value="50" />
		    	<input type="submit" name="page" value="100" />
		    	<input type="submit" name="page" value="ALL" />
			</form>
		</div>

    </footer>
<script src="${request.getContextPath()}js/jquery.min.js"></script>
<script src="${request.getContextPath()}js/bootstrap.min.js"></script>
<script src="${request.getContextPath()}js/dashboard.js"></script>

</body>
</html>