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
<link href="${request.getContextPath()}css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/main.css" rel="stylesheet" media="screen">
</head>
<%List<Computer> computers = (List<Computer>) request.getAttribute("computers"); %>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                <% out.println(computers.size() +" Computers found"); %>
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
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
                <% 
                for(Computer computer : computers) { 
                	out.println("<tr>");
                	out.println("<td class=\"editMode\">" + computer.getId());
                	out.println("<input type=\"checkbox\" name=\"cb\" class=\"cb\" value=\""+computer.getId()+"\">");
                	out.println("</td>");
                	out.println("<td>");
                	out.println("<a href=\"GoToEditComputer?id="+computer.getId()+"\" onclick=\"\">"+computer.getName()+"</a>");
                	out.println("</td>");
                	out.println("<td>" + computer.getIntroduced() + "</td>");
                	out.println("<td>" + computer.getDiscontinued()+ "</td>");
                	out.println("<td>" + computer.getCompany().getName()+ "</td>");
                }
                %>

                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
        <% if ((int)request.getAttribute("page") != 0) {
                %>
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              <% 
              if ((int)request.getAttribute("page") > 15) {
            	  for (int i=0;i<(int)request.getAttribute("page");i+=5) {
                	  out.println("<li><a href=\"Dashboard?page="+request.getAttribute("nbeachpage")+"&offset="+i+"\">" + (i+1) + "</a></li>");
                  }
              } else {
            	  for (int i=0;i<(int)request.getAttribute("page");i++) {
                	  out.println("<li><a href=\"Dashboard?page="+request.getAttribute("nbeachpage")+"&offset="+i+"\">" + (i+1) + "</a></li>");
            	  }
              }
          %> 
<!--               <li><a href="#">1</a></li> -->
<!--               <li><a href="#">2</a></li> -->
<!--               <li><a href="#">3</a></li> -->
<!--               <li><a href="#">4</a></li> -->
<!--               <li><a href="#">5</a></li> -->
              <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul><%} %>

        <div class="btn-group btn-group-sm pull-right" role="group" >
        <form action="Dashboard" Method="GET">
	    	<input type="submit" name="page" value="10" />
	    	<input type="submit" name="page" value="50" />
	    	<input type="submit" name="page" value="100" />
	    	<input type="submit" name="page" value="ALL" />
		</form>
		
<!--                  <a class="btn btn-success" id="addComputer" href="Dashboard?page=10">10</a>  -->
<!--                  <a class="btn btn-success" id="addComputer" href="Dashboard?page=50">50</a>  -->
<!--                  <a class="btn btn-success" id="addComputer" href="Dashboard?page=100">100</a>  -->
<!--             <button type="button" class="btn btn-default" value="10" onclick="Dashboard">10</button> -->
<!--             <button type="button" class="btn btn-default">50</button> -->
<!--             <button type="button" class="btn btn-default">100</button> -->
        </div>

    </footer>
<script src="${request.getContextPath()}js/jquery.min.js"></script>
<script src="${request.getContextPath()}js/bootstrap.min.js"></script>
<script src="${request.getContextPath()}js/dashboard.js"></script>

</body>
</html>