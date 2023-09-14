<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gugudan2</title>
</head>
<body>
<%
	//몇열을 출력할건지 읽어온다	
	String scol = request.getParameter("col");

	// 문자를 읽어온 것을  숫자로 변경
	int col = 2;
	if (scol != null) {
		col = Integer.parseInt(scol);
	}
	
	// 루프 돌면서 해당 열 출력 (out )
	for (int i = 2; i <= 9; i+=col) {
		for (int j = 1; j <= 9; j++) {
			for (int k = 0; k < col; k++) {
				if (10 <= i+k) {
					out.println("<br/>");
					break;
				}
			out.println((i+k) + "*" + j + "=" + ((i+k)*j));
			
			if (k < col -1) out.print("&nbsp");
			else out.print("<br/>");
			}
		}
		out.println("<br/>");
	}
%>
</body>
</html>