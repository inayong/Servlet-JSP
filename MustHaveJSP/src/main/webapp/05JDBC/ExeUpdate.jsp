<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
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
	<h2>회원 추가 테스트(executeUpdate() 사용)</h2>
	<%
	JDBConnect jdbc = new JDBConnect();
	Connection con = jdbc.getConnection(); 
	int cnt = 0;
	
	String sql = "INSERT INTO member(id, pass, name) VALUES (?, ?, ?)";
	PreparedStatement psmt = con.prepareStatement(sql);
	
	String [] ids = { "musthave", "willhave", "dohave" };
	String [] names = { "머스트해브", "윌해브", "두해브" };
	
	for (int i = 0; i < ids.length; i++) {
	psmt.setString(1, ids[i]);
	psmt.setString(2, "1234");
	psmt.setString(3, names[i]);		
	
	cnt += psmt.executeUpdate();
	}
	out.println(cnt + "행이 입력되었습니다.");
	
	psmt.close();
	jdbc.close();
	%>
</body>
</html>