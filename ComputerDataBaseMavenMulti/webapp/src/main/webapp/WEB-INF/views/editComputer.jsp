<%@page import="com.excilys.computerdatabase.model.Company"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="th"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="js/datePicker/jquery-ui.min.css">
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                    <h1><spring:message code="edit.edit"/></h1>

                    <th:form action="editComputer" method="POST" modelAttribute="computer">
                        <input type="hidden" name="id" value="${computer.id}"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="dashboard.name"/></label>
                                <input type="text" class="form-control" value="${computer.name}" id="name" name="name" onkeyup="$.fn.checkvalue()" placeholder="<spring:message code="dashboard.name"/>">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="dashboard.introduced"/></label>
                                <input type="text" class="form-control" value="${computer.introduced}" id="introduced" name="introduced" placeholder="<spring:message code="dashboard.introduced"/>">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="dashboard.discontinued"/></label>
                                <input type="text" class="form-control" value="${computer.discontinued}" id="discontinued" name="discontinued" placeholder="<spring:message code="dashboard.discontinued"/>">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="dashboard.company"/></label>
                                <select class="form-control" id="companyId" name="companyId">
								<option value="0"><c:out value="--" /></option>
                                	<c:forEach var="company" items="${companies}">
                                		<c:choose>
                                			<c:when test="${company.id == computer.companyId }">
                                				<option value="${company.id}" selected="true"><c:out value="${company.name}"/></option>
                                			</c:when>
                                			<c:otherwise>
                                				<option value="${company.id}"><c:out value="${company.name}"/></option>
                                			</c:otherwise>
                                		</c:choose>
                                	</c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input id="send" type="submit" value="<spring:message code="dashboard.edit"/>" class="btn btn-primary">
                            
                            <a href="dashboard" class="btn btn-default"><spring:message code="add.cancel"/></a>
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
	$(function() {
	    $( "#introduced" ).datepicker({changeMonth: true,
	        changeYear: true});
	    $( "#introduced" ).datepicker( "option", $.datepicker.regional["<spring:message code='lang' javaScriptEscape='true' />"] );
		$( "#introduced" ).datepicker( "setDate", "${computer.introduced}" );
	});
	
	$(function() {
	    $( "#discontinued" ).datepicker({changeMonth: true,
	        changeYear: true});
	    $( "#discontinued" ).datepicker( "option", $.datepicker.regional["<spring:message code='lang' javaScriptEscape='true' />"]);
		$( "#discontinued" ).datepicker( "setDate", "${computer.discontinued}" );
	});
</script>
</body>
</html>