<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Context and Initialization Servlet Parameters</title>
</head>
<body>
	 <h2>Please fill the form below:</h2>
        <form action="${pageContext.request.contextPath}/user" method="post">
            <label for="name"><strong>Name:</strong></label>
            <input type="text" name="name" id="name">
            <label for="email"><strong>Email:</strong></label>
            <input type="text" name="email" id="email">
            <input type="submit" value="Send">
        </form>
</body>
</html>