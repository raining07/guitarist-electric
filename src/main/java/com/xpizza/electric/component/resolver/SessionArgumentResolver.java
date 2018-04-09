package com.xpizza.electric.component.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.xpizza.electric.anno.Session;

/**
 * 自定义参数解析器.从session中取值
 */
public class SessionArgumentResolver implements HandlerMethodArgumentResolver {

	static final Logger logger = LoggerFactory.getLogger(SessionArgumentResolver.class);

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Session.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Session sessionAttr = parameter.getParameterAnnotation(Session.class);
		String name = sessionAttr.value();
		return webRequest.getAttribute(name, RequestAttributes.SCOPE_SESSION);
	}

}