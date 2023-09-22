<%@page import="model1.board.BoardDTO"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>    
<%
String num = request.getParameter("num");
BoardDAO dao = new BoardDAO(application);
BoardDTO dto = dao.selectView(num);
String sessionId = session.getAttribute("UserId").toString();
if (!sessionId.equals(dto.getId())) {
	JSFunction.alertBack("작성자 본인만 수정할 수 있습니다.", out);
	return;
}
dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
<script type="text/javascript">
function validateForm(form) {
	if (form.title.value == "") {
		alert("제목을 입력하세요.");
		form.title.focus();
		return false;
	}
	if (form.content.value == "") {
		alert("내용을 입력하세요.");
		form.content.focus();
		return false;
	}
}
</script>
</head>
<body>

</body>
</html>