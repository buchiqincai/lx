package com.lx.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils implements Runnable {
	//private static final Integer IDLE_TIME = InitUtils.getInt("httpclient.idleTime");
	private static final Integer IDLE_TIME = 30000;
	protected final static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	static PoolingHttpClientConnectionManager cm;
	static CloseableHttpClient httpClient;
	static {
		cm = new PoolingHttpClientConnectionManager();
		// 将最大连接数增加到200
		cm.setMaxTotal(200);
		// 将每个路由基础的连接增加到20
		cm.setDefaultMaxPerRoute(20);
		// 将目标主机的最大连接数增加到50
		HttpHost localhost = new HttpHost("api.weixin.qq.com", 443);
		cm.setMaxPerRoute(new HttpRoute(localhost), 5);
		httpClient = HttpClients.custom().setConnectionManager(cm).build();
		new Thread(new HttpClientUtils()).start();
	}

	@Override
	public void run() {
		// test();
		while (true) {
			try {
				synchronized (this) {
					Thread.sleep(IDLE_TIME);
					// 关闭失效的连接
					cm.closeExpiredConnections();
					// 经测试连接用完后会变成close_wait状态，无法重新使用，需要通过这个来释放关闭30秒内不活动的连接
					cm.closeIdleConnections(IDLE_TIME, TimeUnit.MILLISECONDS);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}

	}

	/***
	 * get方式获取返回字符串结果，适用于返回内容比较少的场景
	 *
	 * @param url
	 * @param onlyOK
	 * @param charSet
	 * @return
	 * @throws Exception
	 */
	public static String getAsStr(String url, boolean onlyOK, String charSet) throws Exception {
		String responseString = null;
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
			HttpEntity entity = response.getEntity();
			if (onlyOK) {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					responseString = EntityUtils.toString(entity, charSet);
				}
			} else {
				responseString = EntityUtils.toString(entity, charSet);
			}
		} catch (Exception e) {
			httpGet.abort();
			logger.error(e.getMessage(),e);
		} finally {
			response.close();
		}
		return responseString;
	}

	/**
	 * get方式获取详细的放回实体，包含所有返回内容
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static HttpEntity getAsEntity(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		HttpEntity entity = null;
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
			entity = response.getEntity();
		} catch (Exception e) {
			httpGet.abort();
			logger.error(e.getMessage(),e);
		} finally {
			response.close();
		}
		return entity;
	}

	/**
	 * get方式获取并保存返回内容到path
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static File getAsFile(String url, String path) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		HttpEntity entity = null;
		OutputStream output = null;
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		File file = null;
		try {
			entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			String fullPath = path + "/" + StringUtils.substringBetween(url, "//", "?");
			file = FileUtils.createFile(fullPath);
			InputStream input = entity.getContent();
			output = new FileOutputStream(file);
			IOUtils.copy(input, output);
			output.flush();

		} catch (Exception e) {
			httpGet.abort();
			logger.error(e.getMessage(),e);
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			} finally {
				if (output != null) {
					output.close();
				}
			}
		}
		return file;
	}

	/**
	 * get方式获取url的输入流
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static InputStream getAsStream(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		HttpEntity entity = null;
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
			entity = response.getEntity();
		} catch (Exception e) {
			httpGet.abort();
			logger.error(e.getMessage(),e);
		} finally {
			response.close();
		}
		return entity == null ? null : entity.getContent();
	}

	/**
	 * post方式提交参数，返回字符串
	 *
	 * @param url
	 * @param params
	 * @param charSet
	 * @param onlyOK
	 * @return
	 * @throws Exception
	 */
	public static String postAsStr(String url, List<NameValuePair> params, String charSet, boolean onlyOK)
			throws Exception {
		String responseString = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(params, charSet));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			HttpEntity entity = response.getEntity();
			if (onlyOK) {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					responseString = EntityUtils.toString(entity, charSet);
				}
			} else {
				responseString = EntityUtils.toString(entity, charSet);
			}
		} catch (Exception e) {
			httpPost.abort();
			logger.error(e.getMessage(),e);
		} finally {
			response.close();
		}
		return responseString;
	}

	/**
	 * post方式获取反回实体
	 *
	 * @param url
	 * @param params
	 * @param charSet
	 * @return
	 * @throws Exception
	 */
	public static HttpEntity postAsEntity(String url, List<NameValuePair> params, String charSet) throws Exception {
		HttpEntity entity = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(params, charSet));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			entity = response.getEntity();
		} catch (Exception e) {
			httpPost.abort();
			logger.error(e.getMessage(),e);
		} finally {
			response.close();
		}
		return entity;
	}

	/**
	 * 直接json方式提交
	 *
	 * @param url
	 * @param params
	 * @param charSet
	 * @param onlyOK
	 * @return
	 * @throws Exception
	 */
	public static String postWithJson(String url, String params, String charSet, boolean onlyOK) throws Exception {
		String responseString = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(params, charSet));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			HttpEntity entity = response.getEntity();
			if (onlyOK) {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					responseString = EntityUtils.toString(entity, charSet);
				}
			} else {
				responseString = EntityUtils.toString(entity, charSet);
			}
		} catch (Exception e) {
			httpPost.abort();
			logger.error(e.getMessage(),e);
		} finally {
			response.close();
		}
		return responseString;
	}
	
	/**
	 * get方式获取并保存返回内容到path
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static File getAsFullFile(String url, String fullPath) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		HttpEntity entity = null;
		CloseableHttpResponse response = httpClient.execute(httpGet);
		File file = null;
		InputStream input = null;
		OutputStream output = null;
		try {
			entity = response.getEntity();
			if (entity != null) {
				file = FileUtils.createFile(fullPath);
				input = entity.getContent();
				output = new FileOutputStream(file);
				IOUtils.copy(input, output);
				output.flush();
			}
		} catch (Exception e) {
			httpGet.abort();
			logger.error(e.getMessage(), e);
		} finally {
			if(input!=null){
				input.close();
			}
			if(output!=null){
				output.close();
			}
			response.close();
		}
		return file;
	}
}