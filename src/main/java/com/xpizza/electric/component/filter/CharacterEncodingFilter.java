package com.xpizza.electric.component.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符编码过滤器
 */
public class CharacterEncodingFilter implements Filter {

	static final Logger logger = LoggerFactory.getLogger(CharacterEncodingFilter.class);

	private String encoding;

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("CharacterEncodingFilter:init...");
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		servletRequest.setCharacterEncoding(this.encoding);
		// 考虑到我们框架对html的支持,所以强制返回的编码格式
		servletResponse.setCharacterEncoding(this.encoding);
		// 放行
		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {
		logger.info("CharacterEncodingFilter:destroy...");
	}
}
