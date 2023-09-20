<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>회원 목록 조회 테스트(executeQuery() 사용)</h2>
	<%
	JDBConnect jdbc = new JDBConnect();
	Connection con = jdbc.getConnection();
	
	String sql = "select title, content, id from board";
	Statement stmt = con.createStatement();
	
	ResultSet rs = stmt.executeQuery(sql);
	out.println("<table border=\"1\">");
	out.println("<tr>");
	out.println("<td>title</td><td>content</td><td>id</td>");
	out.println("</tr>");
	while (rs.next()) {
		String title = rs.getString(1);
		String content = rs.getString(2);
		String id = rs.getString(3);
		
// 		out.println(String.format("%s %s %s", title, content, id) + "<br/>");
		out.println("<tr>");
		out.println(String.format("<td>%s</td><td>%s</td><td>%s</td>", title, content, id));
		out.println("</tr>");
	}
	out.println("</table>");
	stmt.close();
	jdbc.close();
	%>
</body>
</html>