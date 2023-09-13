package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/myservlet03") 
public class MyServlet03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MyServlet03");
		resp.setContentType("text/html; charset=utf-8"); //plain => h2그대로 나옴
		PrintWriter out = resp.getWriter();
		out.println("<h2>이것은 서블릿에서 출력하는 문서입니다.</h2>");
		out.println("<h3>잘 보이면 성공</h3>");
		out.close();
	}
}
