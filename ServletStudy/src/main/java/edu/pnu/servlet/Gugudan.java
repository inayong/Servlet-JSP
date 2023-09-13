package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class Gugudan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("Gugudan");
		arg1.setContentType("text/html; charset=utf-8");
		PrintWriter out = arg1.getWriter();
		
		String snum = arg0.getParameter("num");
		int num = 2;
		if (snum != null) {
			num = Integer.parseInt(snum);
		}
		out.println("<h1>");
		out.println(num + "단입니다.");
		out.println("</h1>");

		for (int i = 1; i <= 9; i++) {
			out.println("<ul>");
			out.println(num + "*" + i + "=" + (num*i));
			out.println("</ul>");
		}
		out.close();
		
	}
}
