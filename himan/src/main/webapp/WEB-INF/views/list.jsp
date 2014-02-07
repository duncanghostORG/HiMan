<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The Task List</title>
</head>
<body>
<form action="/start" method="post">
<table>
 <thead>
  <tr>
   <th>Task Name</th>
   <th></th>
  </tr>
 </thead>
 <tbody>
  <tr>
   <td>Process 1</td>
   <td><input type="checkbox" name="processname" value="process1"/></td>
  </tr>
    <tr>
   <td>Process 2</td>
   <td><input type="checkbox" name="processname" value="process2"/></td>
  </tr>
  <tr><td></td><td><input type="submit" value="OK"/></td></tr>
 </tbody>
</table>
 </form>
</body>
</html>