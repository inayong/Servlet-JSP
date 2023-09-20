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
	<h2>게시판 추가 테스트</h2>
	<%
	JDBConnect jdbc = new JDBConnect();
	Connection con = jdbc.getConnection();
	int cnt = 0;
	
	String sql = "insert into board(title, content, id) values (?, ?, ?)";
	PreparedStatement psmt = con.prepareStatement(sql);
	
	String [] ids = {"musthave", "willhave", "dohave"};
	
	for (int i = 0; i < ids.length; i++) {
		for(int j = 1; j < 10; j++) {
		psmt.setString(1, "제목" + j + "입니다");
		psmt.setString(2, "내용" + j + "입니다");
		psmt.setString(3, ids[i]);
		
		cnt += psmt.executeUpdate();
		}
		out.println(cnt + "행이 입력되었습니다.");
	}
	psmt.close();
	
	jdbc.close();
	%>
</body>
</html>