<html>
<head>
<title>My Date JSP </title>
<style>
span{
color:blue;
}
</style>
</head>
<body>
<%
      java.util.Date today = new java.util.Date();
      out.println("<h1> Current and time is <span>" + today +"</span></h1>");
  %>
</body>
</html>