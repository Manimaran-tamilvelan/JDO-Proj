<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome!</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<%
		response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
	
	if (session.getAttribute("userName") == null) {
		response.sendRedirect("login.jsp");
	}

	
	%>
	



	<h3>Your Details Information</h3>
	<%
		List<String> details = (ArrayList<String>) request.getAttribute("showUserDetail");
	out.print("<div class=\"formBorder\">");
	int size=1;
	for (String detailsIterate : details) {

		if(size==1)
			out.print("<b>UserName:</b>"+detailsIterate);
		if(size==2)
			out.print("<b>Password:</b>"+detailsIterate);
		if(size==3)
			out.print("<b>Mail ID:</b>"+detailsIterate);
		if(size==4)
			out.print("<b>DOB:</b>"+detailsIterate);
		if(size==5)
			out.print("<b>Mobile No:</b>"+detailsIterate);
		
		out.print("<br>");
		size++;
	}
	out.print("</div>");
	%>
	
	


	<div style="text-align: center;">

		<form action="logout">

			<input type="submit" id="button1" value="signout">

		</form>
	</div>

</body>
</html>