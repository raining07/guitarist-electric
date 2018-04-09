package com.xpizza.electric.http;

import com.xpizza.bass.lang.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: RequestUtil
 * @Description: 请求工具
 * @author: Xpizza
 * @date: Jul 16, 2017 3:37:19 PM
 */
public class RequestUtil {

	/**
	 * 
	 * @Title: getRemoteAddr
	 * @Description: 从请求中获取客户端IP
	 * @param request
	 * @return
	 * @return: String
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String[] headNames = { "X-FORWARDED-FOR", "Proxy-Client-IP", "WL-Proxy-Client-IP", "x-real-ip" };
		String ip = null;
		for (String headName : headNames) {
			ip = request.getHeader(headName);
			if (StringUtil.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

}
