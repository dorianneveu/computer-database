<%@page import="com.excilys.computerdatabase.model.Company"%>
<%@page import="java.util.List"%>
<%@page import="com.excilys.computerdatabase.model.Computer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${request.getContextPath()}css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/main.css" rel="stylesheet" media="screen">
</head>
<%Computer computer = (Computer) request.getAttribute("computer"); %>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: <% out.println(computer.getId()); %>
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="EditComputer" method="POST">
                        <input type="hidden" name="id" value="<% out.print(computer.getId()); %>"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" value="<% out.println(computer.getName()); %>" id="name" name="name" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" value="<% out.println(computer.getIntroduced()); %>" id="introduced" name="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" value="<% out.println(computer.getDiscontinued()); %>" id="discontinued" name="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="company" name="company">
                                    <%
                                    	for (Company comp : (List<Company>) request.getAttribute("companies"))
                                    	{
                                    		if (comp.getId() == computer.getCompany().getId()) {
                                    			out.println("<option value=\""+ comp.getId()+"\" selected=\"true\">"+ comp.getName() +"</option>");
                                    		} else {
                                    			out.println("<option value=\""+ comp.getId()+"\">"+ comp.getName() +"</option>");
                                    		}
                                    	}
                                    %>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="Dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>