<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Tasks</title>
</head>
<body>
<h3>Hi,${username}. </h3> Please handle the <span style="color: red;"> ${taskno} </span>tasks below.
 <c:forEach var="task" items="${tasks}"> 
  <c:out value="${task}"/><br> 
</c:forEach> 
</body>
</html>