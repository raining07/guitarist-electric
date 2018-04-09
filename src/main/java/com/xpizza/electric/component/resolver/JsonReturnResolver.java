package com.xpizza.electric.component.resolver;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.google.gson.GsonBuilder;
import com.xpizza.electric.anno.Json;

/**
 * Json返回处理器
 */
public class JsonReturnResolver implements HandlerMethodReturnValueHandler {

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		Method method = returnType.getMethod();
		return method.isAnnotationPresent(Json.class);
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		mavContainer.setRequestHandled(true);// 如果没有视图,则必须设置为true，否则会返回视图层
		String encoding = returnType.getMethod().getAnnotation(Json.class).encoding();
		HttpServletResponse response = (HttpServletResponse) webRequest.getNativeResponse();
		OutputStream os = null;
		try {
			String returnJson = new GsonBuilder().serializeNulls().create().toJson(returnValue);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("application/json;charset=" + encoding);
			byte[] bs = returnJson.getBytes(encoding);
			response.setContentLength(bs.length);
			os = response.getOutputStream();
			os.write(bs);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
