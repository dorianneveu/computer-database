<%@page import="com.excilys.computerdatabase.model.Company"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="${request.getContextPath()}css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/main.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="Dashboard"> Application -
			Computer Database </a>
	</div>
	</header>

	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>Add Computer</h1>
				<form id="addcomputer" action="AddComputer" method="POST" >
					<fieldset>
						<div class="form-group">
							<label for="computerName">Computer name</label> <input
								type="text" class="form-control" id="name" name="name" onkeyup="$.fn.checkvalue()"
								placeholder="Computer name">
						</div>
						<div class="form-group">
							<label for="introduced">Introduced date</label> <input
								type="date" class="form-control" id="introduced"
								name="introduced" placeholder="Introduced date">
						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued date</label> <input
								type="date" class="form-control" id="discontinued"
								name="discontinued" placeholder="Discontinued date">
						</div>
						<div class="form-group">
							<label for="companyId">Company</label> <select
								class="form-control" id="company" name="company">
								<option value="0"><c:out value="--" /></option>
								<c:forEach var="company" items="${companies}">
									<option value="${company.id}"><c:out value="${company.name}" /></option>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input id="send" type="submit" value="Add" class="btn btn-primary">
						or <a href="Dashboard" class="btn btn-default">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
<script src="${request.getContextPath()}js/jquery.min.js"></script>
<script src="${request.getContextPath()}js/bootstrap.min.js"></script>
<script src="${request.getContextPath()}js/addcomputer.js"></script>
</body>
</html>