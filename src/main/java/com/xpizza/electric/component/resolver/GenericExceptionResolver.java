package com.xpizza.electric.component.resolver;

import com.xpizza.bass.lang.exception.NotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局错误页面解析器
 */
public class GenericExceptionResolver extends SimpleMappingExceptionResolver {

	static final Logger logger = LoggerFactory.getLogger(GenericExceptionResolver.class);

	/**
	 * 空参构造器
	 */
	public GenericExceptionResolver() {
		super.setExceptionAttribute("error");
		setDefaultErrorView("500");
		addStatusCode("500", HttpStatus.INTERNAL_SERVER_ERROR.value());
		addStatusCode("404", HttpStatus.NOT_FOUND.value());
		addStatusCode("403", HttpStatus.FORBIDDEN.value());
		super.setWarnLogCategory(GenericExceptionResolver.class.getName());
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		logger.error(exception.getMessage(), exception);
		ModelAndView mav = super.doResolveException(request, response, handler, exception);
		/* 2.ajax请求处理 */
		if (isAjaxRequest(request)) {
			response.setStatus(HttpStatus.OK.value());
			return mav;
			// mav.addObject("ajaxResult", ajaxResult);
		}
		if (exception instanceof NotAuthorizedException) {
			mav.setViewName("auth/sign-in");
			return mav;
		}
		return mav;

	}

	/**
	 * 
	 * @Title: isAjaxRequest
	 * @Description: 判断客户端请求是否是Ajax请求
	 * @param request
	 * @return
	 * @return: Boolean
	 */
	protected Boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}

}