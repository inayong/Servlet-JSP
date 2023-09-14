<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gugudan1</title>
</head>
<body>
<%
	// 몇단을 출력할건지 읽어온다
	String sdan = request.getParameter("dan");
	// 문자를 읽어온 것을  숫자로 변경
	int dan = 2;
	if (sdan != null) {
		dan = Integer.parseInt(sdan);
	}
	// 루프 돌면서 해당 단수 출력 (out )
	
	out.println("<h1>");
	out.println(dan + "단입니다.");
	out.println("</h1>");
	
	for (int i = 1; i <= 9; i++) {
		out.println("<ul>");
		out.println(dan + "*" + i + "=" + (dan*i));
		out.println("</ul>");
	}
%>
</body>
</html>