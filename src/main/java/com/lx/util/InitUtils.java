package com.lx.util;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lx.util.EnvironmentUtils;

public class InitUtils {
	private static final Set<String> FILES = new HashSet<String>();
	protected final static Logger logger = LoggerFactory.getLogger(InitUtils.class);
	private static Properties PROPERTIES = new Properties();
	// 这里的缓存不能放到memcached中，因为每天机器的配置可能不一样
	public static Map<String, Object> CACHE = new HashMap<String, Object>();
	static {
		// 默认加载文件init.properties
		FILES.add("/properties/init.properties");
		loadPropertiesFromClassPath();
		// 检查是否有配置别的需要加载的文件
		String initFiles = PROPERTIES.getProperty("init_properties_files");
		if (initFiles != null) {
			for (String initFile : initFiles.split(",")) {
				FILES.add(initFile);
			}
		}
	}

	/**
	 * @param key
	 * @param separators
	 * @return
	 */
	private static String[] splitStr(String key, String... separators) {
		String[] strArray = null;
		String str = extracted(key);
		String separator = ",";
		if (separators.length > 1) {
			separator = separators[0];
		}
		if (str != null) {
			strArray = ArrayUtils.trimElement(str.split(separator));
		}
		return strArray;
	}

	/**
	 *
	 * @param key
	 * @param separators
	 *            采用动态数据参数,兼容不传分隔符的情况
	 * @return
	 */
	public static String[] getStrArray(String key, String... separators) {
		Object value = CACHE.get(key);
		if (value != null) {
			return (String[]) value;
		} else {
			String[] strArray = splitStr(key, separators);
			CACHE.put(key, strArray);
			return strArray;
		}
	}

	public static int[] getIntArray(String key, String... separators) {
		Object value = CACHE.get(key);
		if (value != null) {
			return (int[]) value;
		} else {
			int[] intArray = null;
			String[] strArray = splitStr(key, separators);
			if (strArray != null) {
				intArray = new int[strArray.length];
				for (int i = 0; i < strArray.length; i++) {
					intArray[i] = Integer.valueOf(strArray[i]);
				}
				CACHE.put(key, intArray);
			}
			return intArray;
		}

	}

	public static long[] getLongArray(String key, String... separators) {
		Object value = CACHE.get(key);
		if (value != null) {
			return (long[]) value;
		} else {
			long[] longArray = null;
			String[] strArray = splitStr(key, separators);
			if (strArray != null) {
				longArray = new long[strArray.length];
				for (int i = 0; i < strArray.length; i++) {
					longArray[i] = Long.valueOf(strArray[i]);
				}
				CACHE.put(key, longArray);
			}
			return longArray;
		}

	}

	public static String getStr(String key) {
		Object value = CACHE.get(key);
		if (value != null) {
			return (String) value;
		} else {
			String property = extracted(key);
			CACHE.put(key, property);
			return property;
		}
	}

	public static Integer getInt(String key) {
		Object value = CACHE.get(key);
		if (value != null) {
			return (Integer) value;
		} else {
			String property = extracted(key);
			Integer cacheValue = property == null ? null : Integer.valueOf(property);
			CACHE.put(key, cacheValue);
			return cacheValue;
		}
	}

	public static Short getShort(String key) {
		Object value = CACHE.get(key);
		if (value != null) {
			return (Short) value;
		} else {
			String property = extracted(key);
			Short cacheValue = property == null ? null : Short.valueOf(property);
			CACHE.put(key, cacheValue);
			return cacheValue;
		}
	}

	public static Long getLong(String key) {
		Object value = CACHE.get(key);
		if (value != null) {
			return (Long) value;
		} else {
			String property = extracted(key);
			Long cacheValue = property == null ? null : Long.valueOf(property);
			CACHE.put(key, cacheValue);
			return cacheValue;
		}
	}

	public static Float getFloat(String key) {
		Object value = CACHE.get(key);
		if (value != null) {
			return (Float) value;
		} else {
			String property = extracted(key);
			Float cacheValue = property == null ? null : Float.valueOf(property);
			CACHE.put(key, cacheValue);
			return cacheValue;
		}
	}

	public static Double getDouble(String key) {
		Object value = CACHE.get(key);
		if (value != null) {
			return (Double) value;
		} else {
			String property = extracted(key);
			Double cacheValue = property == null ? null : Double.valueOf(property);
			CACHE.put(key, cacheValue);
			return cacheValue;
		}
	}

	public static Boolean getBoolean(String key) {
		Object value = CACHE.get(key);
		if (value != null) {
			return (Boolean) value;
		} else {
			String property = extracted(key);
			Boolean cacheValue = property == null ? null : Boolean.valueOf(property);
			CACHE.put(key, cacheValue);
			return cacheValue;
		}
	}

	public static void remove(String key) {
		try {
			CACHE.remove(key);
			PROPERTIES.remove(key);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			;
		}
	}

	private static void loadPropertiesFromClassPath() {
		try {
			if (FILES == null) {
				return;
			}
			for (String file : FILES) {
				PROPERTIES.load(new FileInputStream(EnvironmentUtils.getResource(file)));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			;
		}
	}

	private static String extracted(String key) {
		String property = PROPERTIES.getProperty(key);
		if (property != null) {
			return property.trim();
		}
		// 如果没有则重新加载配置文件，这样新增的配置不需要重启服务器
		synchronized (PROPERTIES) {
			property = PROPERTIES.getProperty(key);
			if (property != null) {
				return property.trim();
			}
			loadPropertiesFromClassPath();
			property = PROPERTIES.getProperty(key);
		}
		return property == null ? null : property.trim();
	}

	public static void main(String[] args) {

	}

}
