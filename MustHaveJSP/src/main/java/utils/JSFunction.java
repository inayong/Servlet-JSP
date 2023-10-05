package utils;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.jsp.JspWriter;

public class JSFunction {
	
	public static void alertLocation(HttpServletResponse resp, String mag, String url) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
						  + "<script>"
						  + "	alert('" + resp + "');"
						  + "	location.href='" + url + "';"
						  + "</script>";
			writer.println(script);
		}
		catch (Exception e) {}
	}
	
	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
						  + "<script>"
					      + "	alert('" + msg + "');"
					      + "	history.back();"
					      + "</script>";
			writer.println(script);
		}
		catch (Exception e) {}
	}
}
