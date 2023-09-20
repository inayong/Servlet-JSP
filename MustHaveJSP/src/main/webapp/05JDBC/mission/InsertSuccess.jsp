<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertSuccess</title>
</head>
<body>
	<%--얘는 get --%>
	<% 
		request.setAttribute("msg", "로그인 되었습니다.");	
		response.sendRedirect("InsertForm.jsp");
	%> 
</body>
</html>