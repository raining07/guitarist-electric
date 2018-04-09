package com.xpizza.electric.component.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.xpizza.bass.lang.Numbers;
import com.xpizza.electric.page.PageParams;

/**
 * 
 * @ClassName: PageParamsResolver
 * @Description: TODO 分页参数解析器
 * @author: Xpizza
 * @date: Jul 18, 2017 11:51:08 AM
 */
public class PageParamsResolver implements HandlerMethodArgumentResolver {

	static final Logger logger = LoggerFactory.getLogger(PageParamsResolver.class);

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> parameterType = parameter.getParameterType();
		return parameterType.equals(PageParams.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		PageParams pageParams = new PageParams();
		int pageNo = Numbers.toInt(webRequest.getParameter("page"));
		int pageSize = Numbers.toInt(webRequest.getParameter("limit"));
		pageParams.setPageNo(pageNo);
		pageParams.setPageSize(pageSize);
		// 排序
		/*
		 * String sort = (String) webRequest.getAttribute("sort",
		 * RequestAttributes.SCOPE_REQUEST); pageParams.addOrder(direction,
		 * property);
		 */
		return pageParams;
	}

}