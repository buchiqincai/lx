package com.wechat.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.lx.util.HttpClientUtils;
import com.lx.util.MD5Util;
import com.lx.util.StringUtils;
import com.wechat.entity.PrepayOrderEntity;
import com.wechat.util.DateUtils;
import com.wechat.util.NumberUtil;
import com.wechat.util.PrepayOrderShift;
import com.wechat.util.WechatPayCommenConstants;
import com.wechat.util.WechatPayYamlConfig;
import com.wechat.util.XmlParse;

public class WechatPay {

	/**
	 * 微信同一下单,成功返回调起微信支付的配置
	 * @author lx
	 * @date 2016-06-15
	 * @param prepayOrderEntity
	 * @param tradeType 支付方式
	 * @return map :flag - 0 支付失败，1 成功 map{flag=1,config=Map<String,Object>}
	 */
	public static Map<String, Object> placeAnOrder(PrepayOrderEntity prepayOrderEntity,String targetWechat) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		if(StringUtils.isBlank(prepayOrderEntity.getTrade_type()) || StringUtils.isBlank(targetWechat)){
			return map;
		}
		String tragetType=prepayOrderEntity.getTrade_type();
		Map<String, String> payConfig=WechatPayYamlConfig.getWechatPayConfig(targetWechat, tragetType);
		//支付成功回调地址
		prepayOrderEntity.setNotify_url(payConfig.get("callBackURL"));
		prepayOrderEntity.setAppid(payConfig.get("appid"));
		prepayOrderEntity.setMch_id(payConfig.get("mchId"));
		prepayOrderEntity.setNonce_str(NumberUtil.getCharAndNumber(32));
		Date date=new Date();
		prepayOrderEntity.setTime_start(DateUtils.dateToString(date, DateUtils.SECOND_FORMAT));
		prepayOrderEntity.setTime_expire(DateUtils.addDay(date, DateUtils.SECOND_FORMAT, 2));	
		/**
		 * 不可使用信用卡，具体业务分析
		 */
//		if(prepayOrderEntity.getBody().contains("充值")){
//			prepayOrderEntity.setLimit_pay(payConfig.get("limitPay"));	
//		}
		
		String prepay=PrepayOrderShift.prepayOrderToString(prepayOrderEntity);
		String pay=prepay+"key="+payConfig.get("key");
		//System.out.println(pay);
		String sign=MD5Util.MD5(pay).toUpperCase();	
		Map<String, Object> payMap=PrepayOrderShift.prepayOrderToMap(prepayOrderEntity);
		payMap.put("sign", sign);
		
		String payOrder=WechatPayYamlConfig.getWechatUrl("weixinPayUrl");
		
		String res=HttpClientUtils.postWithJson(payOrder, XmlParse.MapToXml(payMap), "UTF-8", true);
		
		Map<String, Object> resMap = XmlParse.xmlGetMap(res);
		if(WechatPayCommenConstants.FailCode.equals(resMap.get("return_code")) || 
				WechatPayCommenConstants.FailCode.equals(resMap.get("result_code"))){
			map.put("flag", 0);
		}
		map.put("flag", 1);
		
		payConfig.put("prepay_id", (String) resMap.get("prepay_id"));
		//公众号支付
		if(WechatPayCommenConstants.WebTradeType.equals(tragetType)){
			Map<String, Object> config = forWebPayConfig(payConfig);
			map.put("config", config);
		}
		//app支付
		if(WechatPayCommenConstants.AppTradeType.equals(tragetType)){
			Map<String, Object> config =forAPPPayConfig(payConfig);
			map.put("config", config);
		}
		return map;
	}
	/**
	 * web支付页面签名配置
	 * @param map
	 * @author lx
	 * @date 2016-06-17
	 * @return
	 */
	private static Map<String, Object> forWebPayConfig(Map<String, String> payConfig) throws Exception{
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("appId", payConfig.get("appid"));
		resMap.put("timeStamp", System.currentTimeMillis());
		resMap.put("nonceStr", NumberUtil.getCharAndNumber(32));
		resMap.put("package", "prepay_id="+payConfig.get("prepay_id"));
		resMap.put("signType", payConfig.get("signType"));
		
		String mapOrderAsc = PrepayOrderShift.mapOrderAsc(resMap);
		String pay=mapOrderAsc+"key="+payConfig.get("key");
		String sign=MD5Util.MD5(pay);
		
		resMap.put("paySign", sign);
		return resMap;
	} 
	/**
	 * APP支付页面签名配置
	 * @param map
	 * @author lx
	 * @date 2016-06-17
	 * @return
	 */
	private static Map<String, Object> forAPPPayConfig(Map<String, String> payConfig) throws Exception{
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("appId", payConfig.get("appid"));
		resMap.put("partnerid", payConfig.get("mchId"));
		resMap.put("prepayid", payConfig.get("prepay_id"));
		resMap.put("package",  payConfig.get("package"));
		resMap.put("signType", payConfig.get("signType"));
		resMap.put("timeStamp", System.currentTimeMillis());
		resMap.put("nonceStr", NumberUtil.getCharAndNumber(32));
		
		String mapOrderAsc = PrepayOrderShift.mapOrderAsc(resMap);
		String pay=mapOrderAsc+"key="+payConfig.get("key");		
		String sign=MD5Util.MD5(pay);
		
		resMap.put("paySign", sign);
		return resMap;
	} 
//	public static void main(String[] args) throws Exception {
//		PrepayOrderEntity prepayOrderEntity=new PrepayOrderEntity();
//		prepayOrderEntity.setDevice_info("WEB");
//		prepayOrderEntity.setBody("测试");
//		prepayOrderEntity.setOut_trade_no("qqq");
//		prepayOrderEntity.setFee_type("CNY");
//		prepayOrderEntity.setTotal_fee("1");
//		prepayOrderEntity.setSpbill_create_ip("120.0.0.1");
//		prepayOrderEntity.setTrade_type("JSAPI");
//		prepayOrderEntity.setOpenid("123");
//		placeAnOrder(prepayOrderEntity, WechatPayCommenConstants.TargetOneWechat);
//		
//		String string="appid=web111&body=测试&device_info=WEB&fee_type=CNY&mch_id=web111&"
//				+ "nonce_str=324q8B6ENr8s5706BP8K3kVcI6RACz1C&notify_url=http://localhost:8080/a.htm&"
//				+ "openid=123&out_trade_no=qqq&spbill_create_ip=120.0.0.1&time_expire=20160618162903&"
//				+ "time_start=20160616162903&total_fee=1&trade_type=JSAPI&key=web11111";
//	}
}
