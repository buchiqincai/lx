package com.wechat.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * xml解析
 * @author lx
 * @date 2016-06-16
 *
 */
public class XmlParse {
	private final Logger log=LoggerFactory.getLogger(getClass());
	/**
	 * 将微信支付返回的xml转换为
	 * @param xml 
	 * @author lx
	 * @date 2016-06-16
	 * @return
	 */
	public static Map<String, Object> xmlGetMap(String xml)throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		Document document=DocumentHelper.parseText(xml);
		Element node=document.getRootElement();
		 List<Element> elements = node.elements();
		 for(Element element:elements){
			 map.put(element.getName(), element.getStringValue());
			 //System.out.println(element.getName()+"------"+element.getStringValue()+"----"+element.getText());
		 }
		return map;
	}
	/**
	 * 通过HttpServletRequest 解析xml
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> xmlGetMap(HttpServletRequest request)throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		InputStream inputStream=request.getInputStream();
		try {
			SAXReader reader=new SAXReader();	
			Document document=reader.read(inputStream);
			Element node=document.getRootElement();
			 List<Element> elements = node.elements();
			 for(Element element:elements){
				 map.put(element.getName(), element.getStringValue());
				 //System.out.println(element.getName()+"------"+element.getStringValue()+"----"+element.getText());
			 }	
		} catch (Exception e) {
			
		}finally {
			inputStream.close();
		}
		return map;
	}

	/**
	 * 将map数据拼装为xml格式数据
	 * @param map
	 * @return
	 */
	public static String MapToXml(Map<String, Object> map){
		StringBuilder sb=new StringBuilder();
		sb.append("<xml>");
		for(Entry<String, Object> entry:map.entrySet()){
			String key=entry.getKey();
			Object value=entry.getValue();
			sb.append("<").append(key).append("><![CDATA[").append(value).append("]]></").append(key).append(">");
		}
		sb.append("</xml>");
		return sb.toString();
	}
//	public static void main(String[] args) throws Exception{
//		String string="<xml>"
//				+ "<return_code><![CDATA[SUCCESS]]></return_code>"
//				+ "<return_msg><![CDATA[OK]]></return_msg>"
//				+ "<appid><![CDATA[wx2421b1c4370ec43b]]></appid>"
//				+ "<mch_id><![CDATA[10000100]]></mch_id>"
//				+ "<nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>"
//				+ "<sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>"
//				+ "<result_code><![CDATA[SUCCESS]]></result_code>"
//				+ "<prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>"
//				+ "<trade_type><![CDATA[JSAPI]]></trade_type>"
//				+ "</xml>";
//		System.out.println(xmlGetMap(string).toString());
//		
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("a", "qwer");
//		map.put("b", "测试");
//		map.put("c", 123);
//		System.out.println(MapToXml(map));
//		
//		System.out.println(xmlGetMap(MapToXml(map)).toString());
//	}
}
