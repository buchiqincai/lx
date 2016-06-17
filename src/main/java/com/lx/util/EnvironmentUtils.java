package com.lx.util;

import java.io.InputStream;
import java.util.Properties;

public class EnvironmentUtils {
	private static String PROJECT_HOME;

	/**
	 * 获取工程部署目录，需要考虑不同容器之间的兼容性
	 * 
	 * @return
	 */
	private String generateWarProjectHome() {
		String path = this.getClass().getResource("").getPath();
		int index = path.toLowerCase().lastIndexOf("web-inf");
		return path.substring(1, index);
	}

	public static void main(String[] args) {
		System.out.print(EnvironmentUtils.getResource("/init.properties"));
	}

	public static String getProjectHome() {
		if (PROJECT_HOME == null) {
			PROJECT_HOME = new EnvironmentUtils().generateWarProjectHome();
		}
		return PROJECT_HOME;
	}

	/**
	 * 获取指定文件的输入流，可以是class下的文件或jar里的文件
	 * 
	 * @param file
	 * @return
	 */
	public static InputStream getInputStream(String file) {
		// this.getClass().getClassLoader().getResource(File.separator).getPath()
		// Thread.currentThread().getContextClassLoader().getResource(File.separator).getPath();
		// Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		return new EnvironmentUtils().getClass().getResourceAsStream(file);
	}

	/**
	 * 获取class目录下的资源文件路径
	 * 
	 * @param string
	 * @return
	 */
	public static String getResource(String fileName) {
		String path = new EnvironmentUtils().getClass().getResource(fileName).getPath();
		if (path != null && isWindowns()) {
			int start = path.indexOf("/");
			path = path.substring(start + 1);
		}
		return path;
	}

	public static boolean isWindowns() {
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name").toLowerCase();
		return os.startsWith("win");
	}
}
