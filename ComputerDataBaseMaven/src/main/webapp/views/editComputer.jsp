<%@page import="com.excilys.computerdatabase.model.Company"%>
<%@page import="java.util.List"%>
<%@page import="com.excilys.computerdatabase.model.Computer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@include file="header.jsp" %>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="EditComputer" method="POST">
                        <input type="hidden" name="id" value="${computer.id}"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" value="${computer.name}" id="name" name="name" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" value="${computer.introduced}" id="introduced" name="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" value="${computer.discontinued}" id="discontinued" name="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="company" name="company">
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