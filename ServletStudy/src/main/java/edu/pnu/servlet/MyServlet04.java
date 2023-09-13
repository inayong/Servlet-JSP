package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/myservlet04")
public class MyServlet04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("MyServlet04");
		arg1.setContentType("text/html; charset=utf-8");
		PrintWriter out = arg1.getWriter();
		out.println("<table border=\"1\">");
		out.println("<tr>");
		out.println("<td>번호</td><td>나라</td><td>수도</td><td>인구</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>1</td><td>대한민국</td><td>서울</td><td>1000만</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>2</td><td>미국</td><td>워싱턴</td><td>70만</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>3</td><td>일본</td><td>도쿄</td><td>1400만</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>4</td><td>중국</td><td>베이징</td><td>2100만</td>");
		out.println("</tr>");
		out.println("</table>");
		out.close();
		
	}
}
