<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<%@ page language="java" import="java.util.*"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

 <!-- from的action地址，以及用户名密码的name 。都是spring-security固定的。 -->  
     <form action= "j_spring_security_check" method="post" >
    <label for="j_username"> Login</ label>:
    <input id="j_username" name= "j_username" size="20" maxlength="50" type= "text"/>
    <br />
    <input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>
    <label for="_spring_security_remember_me" >Remember Me?</label>
    <br />
    <label for="j_password" >Password </label> :
    <input id="j_password" name= "j_password" size="20" maxlength="50" type= "password"/>
    <br />
    <input type="submit" value= "Login"/><br/>
     <h2>${sign}</h2>
</form>   

</body>
</html>