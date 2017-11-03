package com.mlink.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class ManageFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		req.getContextPath();
		if (session == null) {
			res.sendRedirect(req.getContextPath() + "/login");
		} else {
			String mlink = (String) session.getAttribute("mlink");
			if (StringUtils.isNotBlank(mlink)) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/login");
			}
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
