<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertProcess</title>
</head>
<body>
<%
	String id = request.getParameter("mem_id");
	String pwd = request.getParameter("mem_pwd");
	String name = request.getParameter("mem_name");
	
	JDBConnect jdbc = new JDBConnect();
	Connection con = jdbc.getConnection();
	
	String sql = "insert into member(id, pass, name) values (?, ?, ?)";
	PreparedStatement psmt = con.prepareStatement(sql);
	
	psmt.setString(1, id);
	psmt.setString(2, pwd);
	psmt.setString(3, name);
	psmt.executeUpdate();
	
	psmt.close();
	jdbc.close();
	
 	if (id != null) {
 		request.getAttribute("msg"); //얘는 set
 		request.getRequestDispatcher("InsertSuccess.jsp").forward(request, response);
 	}
 	else {
	request.setAttribute("fmsg", "입력실패");
	request.getRequestDispatcher("InsertFail.jsp").forward(request, response);		
 	}
	

%>
</body>
</html>