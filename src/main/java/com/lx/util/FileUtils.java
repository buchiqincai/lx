package com.lx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtils extends org.apache.commons.io.FileUtils {
	/**
	 * 获取文件扩展名
	 *
	 * @param file
	 * @return
	 */
	public static String getExtension(File file) {
		String extension = "";
		String fileName = file.getName();
		int lastIndex = fileName.lastIndexOf(".");
		if (lastIndex != -1 && lastIndex != 0) {
			extension = fileName.substring(lastIndex + 1).toLowerCase();
		}
		return extension;
	}

	/**
	 * 转换成kb单位
	 *
	 * @param slaveFile
	 * @return
	 */
	public static float convertKbSize(File slaveFile) {
		return (float) slaveFile.length() / 1024;
	}

	/**
	 * 根据全路径创建文件
	 * @param fullPath
	 * @return
	 */
	public static File createFile(String fullPath) {
		String path = null;
		int index = fullPath.lastIndexOf("/");
		if (index == -1) {
			path = fullPath;
		} else {
			path = fullPath.substring(0, index);
		}
		if (path != null) {
			new File(path).mkdirs();
		}
		return new File(fullPath);
	}
	
	/**
	 * 读取txt文件的内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String txt2String(File file,String charsetName ) {
		StringBuffer result = new StringBuffer();
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),charsetName));
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result =result.append(s) ;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
