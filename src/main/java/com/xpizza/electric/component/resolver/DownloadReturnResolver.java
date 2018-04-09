package com.xpizza.electric.component.resolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.xpizza.bass.io.FileWithName;
import com.xpizza.bass.io.StreamWithName;

/**
 * Json返回处理器
 */
public class DownloadReturnResolver implements HandlerMethodReturnValueHandler {

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		Class<?> parameterType = returnType.getParameterType();
		return parameterType.equals(FileWithName.class);
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		mavContainer.setRequestHandled(true);// 如果没有视图,则必须设置为true，否则会返回视图层
		String fileName = "";
		InputStream inputStream = null;
		ServletOutputStream out = null;
		FileWithName fileWithName = null;
		HttpServletResponse response = (HttpServletResponse) webRequest.getNativeResponse();
		Class<?> parameterType = returnType.getParameterType();
		try {
			if (parameterType.equals(File.class)) {
				File srcFile = (File) returnValue;
				fileName = srcFile.getName();
				inputStream = new FileInputStream(srcFile);
			} else if (parameterType.equals(FileWithName.class)) {
				fileWithName = (FileWithName) returnValue;
				fileName = fileWithName.getDownloadName();
				inputStream = new FileInputStream(fileWithName.getFile());
			} else if (parameterType.equals(StreamWithName.class)) {
				StreamWithName streamWithName = (StreamWithName) returnValue;
				fileName = streamWithName.getDownloadName();
				inputStream = streamWithName.getInputStream();
			} else {
				throw new ServletException("Controller result processor exception ! ");
			}
			response.reset();
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", " ") + "\"");
			out = response.getOutputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = inputStream.read(b)) > 0) {
				out.write(b, 0, len);
			}
			out.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (null != fileWithName) {
				fileWithName.release();
			}
		}
	}

}
