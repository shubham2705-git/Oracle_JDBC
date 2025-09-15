<html>
<head>
<title>Counter JSP </title>

</head>
<body>
<%!
 int count=0;
%>
<%
	 ++count;
      out.println("<h1> I have been accessed "+count+" times</h1>");
  %>
</body>
</html>