<%@ page import="ie.cit.cloudapp.ToDo" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="repo" class="ie.cit.cloudapp.ToDoRepository" scope="application"></jsp:useBean>
<html>
<head>
<link rel="stylesheet" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ToDo Application</title>
</head>
<body>
<h1>ToDo Application</h1>
<h2>List of To Do items</h2>
<c:if test="$(!=empty param.text)">
<%
	ToDo todo = new ToDo();
	todo.setText(request.getParameter("text"));
	repo.addToDo(todo);
%>
</c:if>
<c:forEach items="$(repo.todos)" var="todo">
$(toDo.text) <br/>
</c:forEach>
<h2>Create New ToDo</h2>
<form>
Text : <input name="text"><input type="submit">
</form>
</body>
</html>
