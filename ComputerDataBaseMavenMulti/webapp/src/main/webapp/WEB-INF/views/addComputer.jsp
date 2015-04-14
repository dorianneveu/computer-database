<%@page import="com.excilys.computerdatabase.model.Company"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="th"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="header.jsp"%>

<link rel="stylesheet" href="js/datePicker/jquery-ui.min.css">

<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>
					<spring:message code="dashboard.add" />
				</h1>
				<th:form id="addcomputer" action="addComputer" method="POST"
					modelAttribute="computerDTO">
					<fieldset>
						<div class="form-group">
							<label for="computerName"><spring:message
									code="dashboard.name" /></label> <input type="text"
								class="form-control" id="name" name="name" value="${computerDTO.name }"
								onkeyup="$.fn.checkvalue()"
								placeholder="<spring:message code="dashboard.name"/>"></input>
							<th:errors path="name" cssclass="alert alert-danger"></th:errors>
						</div>
						<div class="form-group">
							<label for="introduced"><spring:message
									code="dashboard.introduced" /></label> <input type="text"
								class="form-control" id="introduced" name="introduced" value="${computerDTO.introduced }"
								placeholder="<spring:message code="dashboard.introduced"/>"></input>
							<th:errors path="introduced" cssclass="alert alert-danger"></th:errors>
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message
									code="dashboard.discontinued" /></label> <input type="text"
								class="form-control" id="discontinued" name="discontinued" value="${computerDTO.discontinued }"
								placeholder="<spring:message code="dashboard.discontinued"/>"></input>
							<th:errors path="discontinued" cssclass="alert alert-danger"></th:errors>
						</div>
						<div class="form-group">
							<label for="companyId"><spring:message
									code="dashboard.company" /></label>
							<th:select path="companyId" class="form-control" id="companyId"
								name="companyId">
								<option value="0"><c:out value="--" /></option>
								<c:forEach var="company" items="${companies}">
									<c:choose>
										<c:when test="${company.id == computerDTO.companyId }">
											<option value="${company.id}" selected="true"><c:out
													value="${company.name}" /></option>
										</c:when>
										<c:otherwise>
											<option value="${company.id}"><c:out
													value="${company.name}" /></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</th:select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input id="send" type="submit"
							value="<spring:message code="add.add"/>" class="btn btn-primary">
						<a href="dashboard" class="btn btn-default"><spring:message
								code="add.cancel" /></a>
					</div>
				</th:form>
			</div>
		</div>
	</div>
</section>


<script src="${request.getContextPath()}js/jquery.min.js"></script>
<script src="${request.getContextPath()}js/addcomputer.js"></script>
<script src="js/datePicker/jquery-ui.js"></script>
<script src="js/datePicker/datepicker-fr.js"></script>
<script src="js/datePicker/datepicker-en.js"></script>
<script type="text/javascript">
		var strings = new Array();
		strings['add.errorname'] = "<spring:message code='add.errorname' javaScriptEscape='true' />";
		strings['lang'] = "<spring:message code='lang' javaScriptEscape='true' />";
		    $(function() {
		        $( "#introduced" ).datepicker({changeMonth: true,
		            changeYear: true});
		        $( "#introduced" ).datepicker( "option", $.datepicker.regional["<spring:message code='lang' javaScriptEscape='true' />"] );
		       
		    });
		    
		    $(function() {
		        $( "#discontinued" ).datepicker({changeMonth: true,
		            changeYear: true});
		        $( "#discontinued" ).datepicker( "option", $.datepicker.regional["<spring:message code='lang' javaScriptEscape='true' />"] );
		    });
	</script>
</body>
</html>