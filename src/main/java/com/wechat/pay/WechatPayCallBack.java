package com.wechat.pay;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.lx.util.MD5Util;
import com.lx.util.StringUtils;
import com.wechat.util.PrepayOrderShift;
import com.wechat.util.WechatPayCommenConstants;
import com.wechat.util.WechatPayYamlConfig;
import com.wechat.util.XmlParse;
/**
 * 处理微信支付回调和主动查询订单是否支付成功
 * @author lx
 * @date 2016-06-17
 *
 */
public class WechatPayCallBack {
	/**
	 * 微信回调
	 * @param request
	 */
	public static Map<String, Object> PayCallBack(HttpServletRequest request)throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		//获取回调微信返回参数
		Map<String, Object> callBackMap=XmlParse.xmlGetMap(request);
		String returnCode=(String)callBackMap.get("return_code");
		String resultCode = (String)callBackMap.get("result_code");
		//只有两者都是SUCCESS才为成功
		if(WechatPayCommenConstants.SuccessCode.equals(returnCode)){
			if (WechatPayCommenConstants.SuccessCode.equals(resultCode)) {
				//校验签名
				String sign=(String)callBackMap.get("sign");
				if(StringUtils.isBlank(sign)){
					return map;
				}  
				
				
				callBackMap.remove("sign");
				String reString=PrepayOrderShift.mapOrderAsc(callBackMap);
				String rString=reString+"key="+
				WechatPayYamlConfig.getWechatPayConfig((String)callBackMap.get("appid"), (String)callBackMap.get("trade_type"));
				String md=MD5Util.MD5(rString);
				if(!md.equals(sign)){
					map.put("code", 500);
					map.put("message", "返回签名校验不通过");
					return map;
				}
				//微信订单编号
				//String transaction_id=(String)callBackMap.get("transaction_id");
				//商户订单编号
				//String out_trade_no=(String)callBackMap.get("out_trade_no");
				/**
				 * 处理业务
				 * @TODO
				 */
			}
			map.put("code", 500);
			map.put("message", callBackMap.get("err_code_des"));
		}
		map.put("code", 500);
		map.put("message", callBackMap.get("return_msg"));
		return map;
	} 
	
	public static void main(String[] args) {
	}
}
