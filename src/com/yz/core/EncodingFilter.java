package com.yz.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encoding = filterConfig.getInitParameter("encoding");
		System.out.println("=======>FILTER:" + encoding);
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html,charset=UTF-8");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
