package com.wechat.util;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import com.lx.util.StringUtils;
/**
 * 读取微信支付wechat.yaml配置文件
 * .yml配置如 key: web222  value要和key有空格
 * @author lx
 * @date 2016-06-16
 *
 */
public class WechatPayYamlConfig {
	private final static Logger log=LoggerFactory.getLogger("yaml");
	private static Map<String, Object> wechat;
	
	static{
		try{
			Yaml yaml = new Yaml();
			URL url = WechatPayCommenConstants.class.getClassLoader().getResource("properties/wechat.yaml");
	        if (url != null) {
	        	wechat= (Map<String, Object>) yaml.load(new FileInputStream(url.getFile()));
	            } 
		}catch(Exception e){
		  log.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 获取指定支付的配置
	 * @param target 目标公众号
	 * @param type 类型（APP、JSAPI）
	 * @return
	 * @author lx 
	 * @date 2016-06-16
	 */
	public static Map<String, String> getWechatPayConfig(String targetWechat,String tragetType)throws Exception{
		if (StringUtils.isBlank(targetWechat) || StringUtils.isBlank(tragetType)) {
		  return null;	
		}
		Map<String, Map<String, String>> targetMap=(Map<String, Map<String, String>>)wechat.get(targetWechat);
		if(targetMap == null){
			return null;
		}
		Map<String, String> typeMap= targetMap.get(tragetType);
		System.out.println(typeMap.toString());
		
		return typeMap;
	}
	/**
	 * 获取指定地址
	 * @param UrlName
	 * @return
	 * @author lx 
	 * @date 2016-06-16
	 */
	public static String getWechatUrl(String UrlName) throws Exception{
		Map<String, Object> map=(Map<String, Object>) wechat.get("wechatURL");
		String url=(String) map.get(UrlName);
		return url;
	}
//	public static void main(String[] args) throws Exception {
//		getWechatPayConfig("defult1", "APP");
//	}
}
