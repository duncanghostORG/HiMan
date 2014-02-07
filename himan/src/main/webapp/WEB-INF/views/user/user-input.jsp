<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Add new user</title>
</head>
<body>
	<form:form method="post" modelAttribute="user">
		<table>
			<thead>
				<tr>
					<th>User Information</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>User ID</td>
					<td><form:input path="id" /></td>
				</tr>
				<tr>
					<td>First Name</td>
					<td><form:input path="firstname" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><form:input path="lastname" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:password path="pwd" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>Group</td>
					<td><form:select path="group">
							<form:option value="" label="--请选择--" />
							<form:options items="${groupmap}" itemValue="id"
								itemLabel="name" />
						</form:select></td>
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