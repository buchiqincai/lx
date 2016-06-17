package com.wechat.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import com.lx.util.StringUtils;
import com.wechat.entity.PrepayOrderEntity;
/**
 * @author lx
 * @date 2016-06-15
 */
public class PrepayOrderShift {
	/**
	 * PrepayOrderEntity 实体类转为 map
	 * @param prepayOrderEntity
	 * @return
	 * @author lx 
	 * @date 2016-06-15
	 */
	public static  Map<String, Object> prepayOrderToMap(PrepayOrderEntity prepayOrderEntity)throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		String appid=prepayOrderEntity.getAppid();
		if(!StringUtils.isBlank(appid)){
			map.put("appid", appid);
		}
		String attach = prepayOrderEntity.getAttach();
		if(!StringUtils.isBlank(attach)){
			map.put("attach", attach);
		}
		String body = prepayOrderEntity.getBody();
		if(!StringUtils.isBlank(body)){
			map.put("body", body);
		}
		String detail = prepayOrderEntity.getDetail();
		if(!StringUtils.isBlank(detail)){
			map.put("detail", detail);
		}
		String device_info = prepayOrderEntity.getDevice_info();
		if(!StringUtils.isBlank(device_info)){
			map.put("device_info", device_info);
		}
		String fee_type = prepayOrderEntity.getFee_type();
		if(!StringUtils.isBlank(fee_type)){
			map.put("fee_type", fee_type);
		}
		String goods_tag = prepayOrderEntity.getGoods_tag();
		if(!StringUtils.isBlank(goods_tag)){
			map.put("goods_tag", goods_tag);
		}
		String limit_pay = prepayOrderEntity.getLimit_pay();
		if(!StringUtils.isBlank(limit_pay)){
			map.put("limit_pay", limit_pay);
		}
		String mch_id = prepayOrderEntity.getMch_id();
		if(!StringUtils.isBlank(mch_id)){
			map.put("mch_id", mch_id);
		}
		String nonce_str = prepayOrderEntity.getNonce_str();
		if(!StringUtils.isBlank(nonce_str)){
			map.put("nonce_str", nonce_str);
		}
		String notify_url = prepayOrderEntity.getNotify_url();
		if(!StringUtils.isBlank(notify_url)){
			map.put("notify_url", notify_url);
		}
		String openid = prepayOrderEntity.getOpenid();
		if(!StringUtils.isBlank(openid)){
			map.put("openid", openid);
		}
		String out_trade_no = prepayOrderEntity.getOut_trade_no();
		if(!StringUtils.isBlank(out_trade_no)){
			map.put("out_trade_no", out_trade_no);
		}
		String product_id = prepayOrderEntity.getProduct_id();
		if(!StringUtils.isBlank(product_id)){
			map.put("product_id", product_id);
		}
		String spbill_create_ip = prepayOrderEntity.getSpbill_create_ip();
		if(!StringUtils.isBlank(spbill_create_ip)){
			map.put("spbill_create_ip", spbill_create_ip);
		}
		String time_expire = prepayOrderEntity.getTime_expire();
		if(!StringUtils.isBlank(time_expire)){
			map.put("time_expire", time_expire);
		}
		String trade_type = prepayOrderEntity.getTrade_type();
		if(!StringUtils.isBlank(trade_type)){
			map.put("trade_type", trade_type);
		}
		String total_fee = prepayOrderEntity.getTotal_fee();
		if(!StringUtils.isBlank(total_fee)){
			map.put("total_fee", total_fee);
		}
		String time_start = prepayOrderEntity.getTime_start();
		if(!StringUtils.isBlank(time_start)){
			map.put("time_start", time_start);
		}
		return map;
	}
	/**
	 * 将参数以它的参数名的字典升序排列
	 * @date 2016-01-15
	 * @author lx
	 * @param map
	 * @return
	 */
	public static String mapOrderAsc(Map<String, Object> map) throws Exception{
		StringBuilder sb=new StringBuilder();
		//将参数以它的参数名的字典升序排列
		Map<String, Object> resmap=new TreeMap<String, Object>(map);
		Set<Entry<String, Object>> entrys=resmap.entrySet();
		for(Entry<String, Object> entry:entrys){
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");	
		}	
		return java.net.URLDecoder.decode(sb.toString(), "utf-8");
	}
	/**
	 * 实体类转为 xx=xx&ss=ss&
	 * @param prepayOrderEntity
	 * @author lx
	 * @dete 2016--6-15
	 * @return
	 * @throws Exception
	 */
	public static String prepayOrderToString(PrepayOrderEntity prepayOrderEntity) throws Exception{
		Map<String, Object> map=prepayOrderToMap(prepayOrderEntity);
		return mapOrderAsc(map);
	}
}
