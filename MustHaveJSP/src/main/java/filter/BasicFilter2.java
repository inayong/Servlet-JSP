package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/15FilterListener/BasicFilter.jsp")
//@WebFilter(urlPatterns = "*")
public class BasicFilter2 implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		System.out.println("before - BasicFilter2");
		chain.doFilter(request, response);		
		System.out.println("after - BasicFilter2");
	}
}
