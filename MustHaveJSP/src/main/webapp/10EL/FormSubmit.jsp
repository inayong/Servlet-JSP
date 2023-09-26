<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어(EL) - 폼값 처리</title>
</head>
<body>
	<h2>폼값 전송하기</h2>
	<form action="FormResult.jsp" name="frm" method="post">
		이름 : <input type="text" name="name" /><br />
		성별 : <input type="radio" name="gender" value="Man" />남자
			  <input type="radio" name="gender" value="Woman" />여자<br />
		학력 :
			<select name="grade">
				<option value="ele">초등학교</option>
				<option value="mid">중학교</option>
				<option value="high">고등학교</option>
				<option value="uni">대학교</option>
			</select><br />
		관심 사항 :
			<input type="checkbox" name="inter" value="pol" />정치
			<input type="checkbox" name="inter" value="eco" />경제
			<input type="checkbox" name="inter" value="ent" />연예
			<input type="checkbox" name="inter" value="spo" />운동<br />
		이수 과목 :
			<input type="checkbox" name="classes" value="Java" />Java
			<input type="checkbox" name="classes" value="Python" />Python
			<input type="checkbox" name="classes" value="C/C#" />C/C#
			<input type="checkbox" name="classes" value="Javascript" />JavaScript<br />
		<input type="submit" value="전송하기" />
	</form>
</body>
</html>