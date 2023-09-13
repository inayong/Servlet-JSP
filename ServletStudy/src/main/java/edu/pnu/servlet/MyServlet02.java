package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//톰캣에서 기본으로 지원해주는 펑션
@WebServlet("/myservlet02") //톰캣 컨테이너에 등록
public class MyServlet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override //HttpServlet에 정의되어있는 service
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp 서버에 응답할 수 있는 객체
		System.out.println("MyServlet02");
		resp.setContentType("text/plain; charset=utf-8"); 
		PrintWriter out = resp.getWriter();//resp객체로부터 printwriter 를불러옴
		out.println("이것은 서블릿에서 출력하는 문서입니다.");
		out.println("잘 보이면 성공");
		out.close();
	}
}
