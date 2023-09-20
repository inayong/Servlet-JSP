<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertForm</title>
</head>
<body>
	<h2>Member</h2>
	<form action="./InsertProcess.jsp" method="post">
		id : <input type="text" name="mem_id"/><br>
		pwd : <input type="text" name="mem_pwd" /><br>
		name : <input type="text" name="mem_name" />
		<input type="submit" value="insert" />
	</form>
</body>
</html>