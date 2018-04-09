package com.xpizza.electric.component.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xpizza.electric.cache.CacheFactory;


/**
 * Web容器监听器
 */
public class WebContainerListener implements ServletContextListener {
	static final Logger logger = LoggerFactory.getLogger(WebContainerListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("系统正在摧毁!");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("系统正在启动!");
		try {
			// 1.实例化
			CacheFactory Cache = CacheFactory.getInstance();
			// 2.从配置文件中加载到Cache
			Cache.init();
			logger.info(Cache.getMappings().toString());
		} catch (Exception e) {
			logger.error("Failure to load config into Cache!");
			logger.error(e.getMessage(), e);
		}
	}
}
