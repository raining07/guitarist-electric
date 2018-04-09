package com.xpizza.electric.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.xpizza.bass.util.MapPlus;
import com.xpizza.bass.util.PropertyUtil;

/**
 * 
 * @ClassName: CacheFactory
 * @Description: TODO 缓存工厂
 * @author: Xpizza
 * @date: Jul 15, 2017 10:52:15 AM
 */
public class CacheFactory implements MapPlus<String, Object> {

	/**
	 * factory
	 */
	private volatile static CacheFactory cacheFatory;

	private CacheFactory() {
	}

	/**
	 * get instance
	 *
	 * @return The cacheFactory instance
	 */
	public static CacheFactory getInstance() {
		if (cacheFatory == null) {
			synchronized (CacheFactory.class) {
				if (cacheFatory == null) {
					cacheFatory = new CacheFactory();
				}
			}
		}
		return cacheFatory;
	}

	/**
	 * static map
	 */
	private static Map<String, Object> factoryMap = new HashMap<>();

	@Override
	public Object getOne(String key) {
		return factoryMap.get(key);
	}

	@Override
	public Collection<Object> getAll() {
		return factoryMap.values();
	}

	@Override
	public boolean containsKey(String key) {
		return factoryMap.containsKey(key);
	}

	@Override
	public void addOne(String key, Object object) {
		if (containsKey(key)) {
			remove(key);
		}
		factoryMap.put(key, object);
	}

	@Override
	public void putAll(Map<String, Object> map) {
		factoryMap.putAll(map);
	}

	@Override
	public void remove(String key) {
		factoryMap.remove(key);
	}

	@Override
	public void init() {
		for (String fileName : CacheConfig.PROPERTIES_FILENAMES) {
			loadPropsIntoMemory(fileName);
		}
	}

	private void loadPropsIntoMemory(String fileName) {
		Map<String, String> properties = PropertyUtil.getProperties(fileName);
		Iterator<String> it = properties.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String val = properties.get(key);
			addOne(key, val);
		}
	}

	@Override
	public void reload() {
		clear();
		init();
	}

	@Override
	public void clear() {
		factoryMap.clear();
	}

	@Override
	public Map<String, Object> getMappings() {
		Map<String, Object> map = new HashMap<>();
		map.putAll(factoryMap);
		return map;
	}

}
