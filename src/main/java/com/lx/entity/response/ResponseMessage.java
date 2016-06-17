package com.lx.entity.response;

/**
 * 返回数据结构
 * {"code":状态码,"message":错误消息,"date":数据体}
 * @author lx
 *
 */
public class ResponseMessage {

	private int code;
	private String message;
	private Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public ResponseMessage(int code,String message){
		this.code=code;
		this.message=message;
	}
	public ResponseMessage(int code,String message,Object data){
		this.code=code;
		this.message=message;
		this.data=data;
	}
	/**
	 * 成功
	 * @param message
	 * @param data
	 * @return
	 */
	public static ResponseMessage success(String message,Object data){
		return new ResponseMessage(Code.SUCCESS, message, data);
	}
	public static ResponseMessage success(String message){
		return new ResponseMessage(Code.SUCCESS, message);
	}
	public static ResponseMessage success(Object data){
		return new ResponseMessage(Code.SUCCESS, "成功", data);
	}
	public static ResponseMessage success(){
		return new ResponseMessage(Code.SUCCESS, "成功");
	}
	/**
	 * 失败
	 * @param message
	 * @return
	 */
	public static ResponseMessage error(String message){
		return new ResponseMessage(Code.ERROR, message);
	}
	public static ResponseMessage error(String message,Object data){
		return new ResponseMessage(Code.ERROR, message, data);
	}
	public static ResponseMessage error(){
		return new ResponseMessage(Code.ERROR, "操作失败");
	}
	public static ResponseMessage error(Object data){
		return new ResponseMessage(Code.ERROR, "操作失败", data);
	}
}
