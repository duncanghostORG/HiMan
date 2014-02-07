<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new Group</title>
</head>
<body>
<form:form method="post" modelAttribute="group">
		<table>
			<thead>
				<tr>
					<th>Group Information</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>ID</td>
					<td><form:input path="id" /></td>
				</tr>
 				<tr>
					<td>Name</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Type</td>
					<td><form:input path="type" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="OK" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
	 
					 <div> <h2>${message}</h2></div>
</body>
</html>