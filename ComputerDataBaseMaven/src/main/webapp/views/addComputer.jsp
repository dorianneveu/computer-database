<%@page import="com.excilys.computerdatabase.model.Company"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="th"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@include file="header.jsp" %>

	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1><spring:message code="dashboard.add"/></h1>
				<th:form id="addcomputer" action="addComputer" method="POST" modelAttribute="computerDTO" >
					<fieldset>
						<div class="form-group">
							<label for="computerName"><spring:message code="dashboard.name"/></label> <th:input path="name"
								type="text" class="form-control" id="name" name="name" onkeyup="$.fn.checkvalue()"
								placeholder=""></th:input>
<%-- 								<spring:message code="dashboard.name"/> --%>
								    <th:errors path="name" cssclass="error"></th:errors>
						</div>
						<div class="form-group">
							<label for="introduced"><spring:message code="dashboard.introduced"/></label> <th:input path="introduced"
								type="date" class="form-control" id="introduced"
								name="introduced" placeholder=""></th:input>
<%-- 								<spring:message code="dashboard.introduced"/> --%>
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message code="dashboard.discontinued"/></label> <th:input path="discontinued"
								type="date" class="form-control" id="discontinued"
								name="discontinued" placeholder=""></th:input>
<%-- 								<spring:message code="dashboard.discontinued"/> --%>
						</div>
						<div class="form-group">
							<label for="companyId"><spring:message code="dashboard.company"/></label> <th:select path="companyId" 
								class="form-control" id="companyId" name="companyId">
								<option value="0"><c:out value="--" /></option>
								<c:forEach var="company" items="${companies}">
									<option value="${company.id}"><c:out value="${company.name}" /></option>
								</c:forEach>
							</th:select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input id="send" type="submit" value="<spring:message code="add.add"/>" class="btn btn-primary">
						<a href="dashboard" class="btn btn-default"><spring:message code="add.cancel"/></a>
					</div>
				</th:form>
			</div>
		</div>
	</div>
	</section>
<script src="${request.getContextPath()}js/jquery.min.js"></script>
<script src="${request.getContextPath()}js/addcomputer.js"></script>
</body>
</html>