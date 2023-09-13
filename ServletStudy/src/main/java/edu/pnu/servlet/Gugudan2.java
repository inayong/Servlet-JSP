package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gugudan2")
public class Gugudan2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("Gugudan2");
		arg1.setContentType("text/plain; charset=utf-8");
		PrintWriter out = arg1.getWriter();
		
		String dir = arg0.getParameter("dir");
		if (dir == null) {
			dir = "hor";
		}
		if (dir.equals("ver")) {
			// 수직으로 출력
			
			out.println("구구단입니다.");
			
			
			for (int i = 2; i <= 9; i++) {
				for (int j = 1; j <= 9; j++) {			
					
					out.println(i + "*" + j + "=" + (i*j));
					
				}
				out.println();
			}
		} else if (dir.equals("hor")) {
			// 수평으로 출력
			
			out.println("구구단입니다.");
			
			
				for (int i = 2; i < 10; i++) {
					for (int j = 1; j < 10; j++) {
						
						out.print(i + "*" + j + "=" + (i*j) + "\t");
					}
					out.println();
				}
			
			
			
		}

		out.close();
		
	}
}
