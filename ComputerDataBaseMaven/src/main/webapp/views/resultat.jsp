<%@page import="com.excilys.computerdatabase.model.Computer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="com.company.om.*"%> --%>
<%-- <%@ taglib uri="/WEB-INF/session.tld" prefix="session" %> --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Résultat</title>
</head>
<body>
<TABLE border="1">
<%

// List list =  (List) request.getAttribute("resultat");
// Iterator it = list.iterator();
// while(it.hasNext()) {
	Computer p = (Computer) request.getAttribute("resultat");
	out.println("<TR>");
	out.println("<TD>" + p.getId() + "</TD>");
	out.println("<TD>" + p.getName() + "</TD>");
	out.println("<TD>" + p.getIntroduced() + "</TD>");
	out.println("<TD>" + p.getDiscontinued()+ "</TD>");
	out.println("</TR>");
// }

%>
</TABLE>
</body>
</html>