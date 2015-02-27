<%@page import="java.util.List"%>
<%@page import="com.excilys.computerdatabase.model.Computer"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--     <jsp:include page="/Dashboard" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="pg" uri="../WEB-INF/pagetag.tld"%>

<link href="${request.getContextPath()}css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="Dashboard"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                 ${ fn:length(computers) } computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="Dashboard" method="POST" class="form-inline">

                        <input type="search" id="search" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="GoToAddComputer">Add Computer</a> 
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
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
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
	                		<a href="GoToEditComputer?id=${computer.id}" onclick=""><c:out value="${computer.name}"/></a>
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
         <c:if test="${page != 0}">
              <pg:pagetag page="${page}" nbEachPage="${nbeachpage}" offset="${offset}"/>
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