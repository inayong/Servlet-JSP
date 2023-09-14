<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gugudan3</title> <!-- 앞서 작성해두누 구구단1,2.jsp를 호출하도록 form 태그를 이용한 3.jsp생성 -->
</head>
<body>
	<h2>구구단 선택</h2>
	<form action="./Gugudan1.jsp" method="post" >
		<input type="radio" name="sel" value="type1" />Type1<br />
		<input type="radio" name="sel" value="type2" />Type2<br />
		<input type="text" name="val" value="" />단수/열수<br>
		<input type="submit" value="선택" />
	</form>
</body>
</html>