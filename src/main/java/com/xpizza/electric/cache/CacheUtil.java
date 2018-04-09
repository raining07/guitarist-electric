package com.xpizza.electric.cache;

import com.xpizza.bass.lang.StringUtil;

/**
 * 
 * @ClassName: CacheUtil
 * @Description: TODO 缓存工具
 * @author: Xpizza
 * @date: Jul 16, 2017 10:04:03 AM
 */
public class CacheUtil {

	/**
	 * 获取本地内存中的一项配置
	 */
	public static Object getCache(String key) {
		return CacheFactory.getInstance().getOne(key);
	}

	public static boolean isLinuxMode() {
		return StringUtil.getBooleanValue((String) getCache("os.isLinux"));
	}

	/**
	 * 获取系统根目录
	 */
	public static String getRootPath() {
		String rootPath = null;
		if (isLinuxMode()) {
			rootPath = (String) getCache("path.root.linux");
		} else {
			rootPath = (String) getCache("path.root.windows");
		}
		if (StringUtil.isEmpty(rootPath)) {
			throw new RuntimeException("The root path is empty!Please set it in " + CacheConfig.PROPERTIES_FILENAMES);
		}
		return rootPath;
	}

}
