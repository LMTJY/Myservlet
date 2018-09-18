<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form name="userForm" action="login" method="post" >
	<label>Your name:</label>
	
	<input type="text" name="username" value="${username}">
	
	<label>password:</label>
	<input type="password" name="userpassword">
	
	 <input type="submit"  value="submit"/> 
</form>

</body>
</html>


