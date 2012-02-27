<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Application Project</title>
</head>
<body>
Hello, how are you?<br>
This is my first cloud app. This is feature xyz
<br>
<% for(int i = 0; i<10; i++) {%>
This text will be repeated <br>
<% }%>
<%= new java.util.Date()%>
This is the master branch
</body>
</html>
