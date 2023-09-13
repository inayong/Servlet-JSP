package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Gugudan3")
public class Gugudan3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("Gugudan3");
		arg1.setContentType("text/html; charset=utf-8");
		PrintWriter out = arg1.getWriter();
		
		String sdan = arg0.getParameter("dan");
		int dan = 2;
		if (sdan != null) {
			dan = Integer.parseInt(sdan);
		}
		
		for (int i = 2; i < 10; i+=dan) {
			for (int j = 1; j < 10; j++ ) {
				for (int k = 0; k < dan; k++) {
					if (10 <= i+k) {
						out.println("<br>");
						break;
					}
					out.println((i+k) + "*" + j + "=" + ((i+k)*j));
					
					if (k < dan -1) out.print("&nbsp");
					else out.print("<br>");
				}
			}
			out.println("<br>");
		}
		out.close();
		
	}
}
