<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreeFall</title>
</head>
<body>
	<h2>자유 낙하 물체의 위치 구하기</h2>
	<form action="FreeFall.do" method="post">
		초기 위치 : <input type="text" name="x0"><br />
		이동 시간 : <input type="text" name="xt">
		<input type="submit" value="위치 구하기">
	</form>
</body>
</html>