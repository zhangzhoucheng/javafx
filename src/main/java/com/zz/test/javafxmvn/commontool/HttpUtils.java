/**
 * com.hpe.bbc.common.utils.HttpUtils.java
 * Copyright (c) 2017 xx Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zz.test.javafxmvn.commonbean.CommonResult;


/**
 * 
 * <note>
 * Desc： 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-15 12:26:33
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-01-15 12:26:33    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class HttpUtils {

    private static PoolingHttpClientConnectionManager connMgr;
    private static final int			      DEFAULT_MAX_TIMEOUT = 30000;

    static {
	// 设置连接池
	connMgr = new PoolingHttpClientConnectionManager();
	// 设置连接池大小
	connMgr.setMaxTotal(100);
	connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

    }

    /**
     * <pre>
     * Desc  获取request中的参数,并结成string
     * &#64;param request
     * &#64;return
     * &#64;author peter.fu
     * Nov 17, 2016 10:09:25 AM
     * </pre>
     */
    public static String getParameterStr(HttpServletRequest request) {

	StringBuffer resultStr = request.getRequestURL();
	resultStr.append("?");
	Enumeration<?> enum_term = request.getParameterNames();
	while (enum_term.hasMoreElements()) {
	    String paramName = (String) enum_term.nextElement();
	    String paramValue = request.getParameter(paramName);
	    resultStr.append(paramName).append("=").append(paramValue).append("&");
	}
	return resultStr.toString();
    }

    /**
     * <pre>
    * Desc  获取request中的参数
    * &#64;param request
    * &#64;return
    * &#64;author 
    * &#64;refactor 
    * &#64;date   2016-9-19 下午4:09:15
     * </pre>
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
	Map<String, Object> pathMap = new HashMap<String, Object>(20);
	Enumeration<?> enum_term = request.getParameterNames();
	while (enum_term.hasMoreElements()) {
	    String paramName = (String) enum_term.nextElement();
	    if (request.getParameterValues(paramName).length > 1) {
		pathMap.put(paramName.replace("[]", ""), request.getParameterValues(paramName));
	    } else {
		pathMap.put(paramName, request.getParameter(paramName));
	    }
	}
	return pathMap;
    }

    public static CommonResult post(String apiUrl, Map<String, Object> params, int timeout) throws ClientProtocolException, IOException {

	String httpStr = doPost(apiUrl, params, timeout);
	return JsonUtils.fromJson(httpStr, CommonResult.class);
    }

    public static CommonResult post(String apiUrl, Map<String, Object> params) throws ClientProtocolException, IOException {

	String httpStr = doPost(apiUrl, params, DEFAULT_MAX_TIMEOUT);
	return JsonUtils.fromJson(httpStr, CommonResult.class);
    }

    public static String doPost(String apiUrl, Map<String, Object> params, int timeout) throws ClientProtocolException, IOException {

	CloseableHttpResponse response = null;
	try {
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(apiUrl);
	    httpPost.setConfig(buildRequestConfig(timeout));
	    List<NameValuePair> pairList = new ArrayList<>(params.size());
	    for (Map.Entry<String, Object> entry : params.entrySet()) {
		NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
		pairList.add(pair);
	    }
	    httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
	    response = httpClient.execute(httpPost);
	    HttpEntity entity = response.getEntity();
	    return EntityUtils.toString(entity, "UTF-8");
	} finally {
	    if (response != null) {
		try {
		    EntityUtils.consume(response.getEntity());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public static String doPost(String apiUrl,String body) throws ClientProtocolException, IOException {
    	String httpStr = doPost(apiUrl, body, DEFAULT_MAX_TIMEOUT);
    	return httpStr;
    }
    
    @SuppressWarnings("deprecation")
	public static String doPost(String apiUrl,String body,int timeout) throws ClientProtocolException, IOException {

    	CloseableHttpResponse response = null;
    	try {
    	    CloseableHttpClient httpClient = HttpClients.createDefault();
    	    HttpPost httpPost = new HttpPost(apiUrl);
    	    
    	    httpPost.setConfig(buildRequestConfig(timeout));
    	    StringEntity stringEntity = new StringEntity(body, "application/" + "xml", "utf-8");
    	    
    	    httpPost.setEntity(stringEntity);
    	    response = httpClient.execute(httpPost);
    	    HttpEntity entity = response.getEntity();
    	    return EntityUtils.toString(entity, "UTF-8");
    	} finally {
    	    if (response != null) {
    		try {
    		    EntityUtils.consume(response.getEntity());
    		} catch (IOException e) {
    		    e.printStackTrace();
    		}
    	    }
    	}
        
    }
    
    /**
     * 
     * <pre>
     * get 请求
     * @param apiUrl url
     * @return
     * @throws IOException String
     * @author debuggao  
     * 2018年3月23日 下午4:09:18
     * </pre>
     */
    public static String doGet(String apiUrl) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(apiUrl);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        httpClient.close();
        return response.toString();
    }
    
    
  /*  public static CommonResult postFile(String apiUrl, SysFiles sysFiles, MultipartFile file, int timeout) throws IOException {

	String httpStr = doPostFile(apiUrl, buildPostParams(sysFiles), file, timeout);
	return JsonUtils.fromJson(httpStr, CommonResult.class);

    }

    public static CommonResult postFile(String apiUrl, SysFiles sysFiles, MultipartFile file) throws IOException {

	String httpStr = doPostFile(apiUrl, buildPostParams(sysFiles), file,DEFAULT_MAX_TIMEOUT);
	return JsonUtils.fromJson(httpStr, CommonResult.class);

    }
    
    private static  Map<String, String> buildPostParams(SysFiles sysFiles){
	
	Map<String, String> params = new HashMap<String,String>();
	params.put("fileType", sysFiles.getFileType());
	params.put("operator", sysFiles.getOperator());
	params.put("operatorCode", sysFiles.getOperatorCode());
	params.put("operatorName", sysFiles.getOperatorName());
	//params.put("memo", sysFiles.getMemo());
	//other properties
	return params;
	
    }*/
 
    public static String doPostFile(String apiUrl, Map<String, String> params, MultipartFile file, int timeout) throws IOException {

	CloseableHttpResponse response = null;
	try {
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(apiUrl);
	    httpPost.setConfig(buildRequestConfig(timeout));
	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	    builder.addBinaryBody("fileContent", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
	    
	    Iterator<Entry<String, String>>  iter = params.entrySet().iterator();
	    ContentType contentType = ContentType.create("text/plain",Charset.forName("UTF-8"));
	    while(iter.hasNext()){
		Entry<String, String> entry = iter.next();
		    builder.addTextBody(entry.getKey(), entry.getValue(),contentType);
	    }
	    HttpEntity httpEntity = builder.build();
	    httpPost.setEntity(httpEntity);
	    response = httpClient.execute(httpPost);
	    HttpEntity entity = response.getEntity();
	    return EntityUtils.toString(entity, "UTF-8");
	} finally {
	    if (response != null) {
		try {
		    EntityUtils.consume(response.getEntity());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
    
    
    /*
     * 上传本地文件到fileMgr
     */
    public static String doPostFileByLocalFile(String apiUrl, Map<String, Object> params, String filePath, int timeout) throws IOException {

    	CloseableHttpResponse response = null;
    	try {
    	    CloseableHttpClient httpClient = HttpClients.createDefault();
    	    HttpPost httpPost = new HttpPost(apiUrl);
    	    httpPost.setConfig(buildRequestConfig(timeout));
    	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    	    File file = new File(filePath);
    	    String fileName =filePath.substring(filePath.lastIndexOf("/")+1);
    	    builder.addBinaryBody("fileContent", file, ContentType.MULTIPART_FORM_DATA,fileName);
    	    
    	    Iterator<Entry<String, Object>>  iter = params.entrySet().iterator();
    	    ContentType contentType = ContentType.create("text/plain",Charset.forName("UTF-8"));
    	    while(iter.hasNext()){
    		Entry<String, Object> entry = iter.next();
    		    builder.addTextBody(entry.getKey(), (String) entry.getValue(),contentType);
    	    }
    	    HttpEntity httpEntity = builder.build();
    	    httpPost.setEntity(httpEntity);
    	    response = httpClient.execute(httpPost);
    	    HttpEntity entity = response.getEntity();
    	    return EntityUtils.toString(entity, "UTF-8");
    	} finally {
    	    if (response != null) {
    		try {
    		    EntityUtils.consume(response.getEntity());
    		} catch (IOException e) {
    		    e.printStackTrace();
    		}
    	    }
    	}
        }
    

    /**@remark 通过文件id，服务间，发送http请求，获取文件流inputstream
     * @param apiUrl
     * @param params
     * @param timeout
     * @return
     * @throws IOException
     * @author for jld
     */
    public static HttpEntity getFileInputstream(String apiUrl, Map<String, Object> params, int timeout) throws IOException {

    	CloseableHttpResponse response = null;
    	
    	    CloseableHttpClient httpClient = HttpClients.createDefault();
    	    HttpPost httpPost = new HttpPost(apiUrl);
    	    httpPost.setConfig(buildRequestConfig(timeout));
    	    List<NameValuePair> pairList = new ArrayList<>(params.size());
    	    for (Map.Entry<String, Object> entry : params.entrySet()) {
    		NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
    		pairList.add(pair);
    	    }
    	    httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
    	    try{
    	    	response = httpClient.execute(httpPost);
    	    	if (response==null||response.getEntity()==null) {
        	    	return null;
        	    }
    	    }catch(Exception e) {
    	    	e.printStackTrace();
    	    }
    	    return response.getEntity();
    	
    }
    
    
    /**
     * @remark 通过input流，把该流对象上传到指定位置（形成文件）
     * @param apiUrl
     * @param params
     * @param input
     * @param timeout
     * @return
     * @throws IOException
     * @author for jld
     */
    public static String doPostFile8inp8opCode8type(String apiUrl, Map<String, Object> params, ByteArrayInputStream input, int timeout) throws IOException {

    	CloseableHttpResponse response = null;
    	try {
    	    CloseableHttpClient httpClient = HttpClients.createDefault();
    	    HttpPost httpPost = new HttpPost(apiUrl);
    	    httpPost.setConfig(buildRequestConfig(timeout));
    	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    	    //File file=new File("d:/img/tt.xls");
    	   // builder.addBinaryBody("fileContent", file);//本地file直接测试
    	    //builder.addBinaryBody("fileContent", input);
    	   // InputStream i=new FileInputStream(file);
    	    //builder.addBinaryBody("fileContent", i,ContentType.MULTIPART_FORM_DATA, (String) params.get("fileName"));
    	   builder.addBinaryBody("fileContent",input, ContentType.MULTIPART_FORM_DATA, (String) params.get("fileName"));
    	    Iterator<Entry<String, Object>>  iter = params.entrySet().iterator();
    	    ContentType contentType = ContentType.create("text/plain",Charset.forName("UTF-8"));
    	    while(iter.hasNext()){
    		Entry<String, Object> entry = iter.next();
    		    builder.addTextBody(entry.getKey(), (String) entry.getValue(),contentType);
    	    }
    	    HttpEntity httpEntity = builder.build();
    	    httpPost.setEntity(httpEntity);
    	    response = httpClient.execute(httpPost);
    	    HttpEntity entity = response.getEntity();
    	    return EntityUtils.toString(entity, "UTF-8");
    	} finally {
    	    if (response != null) {
    		try {
    		    EntityUtils.consume(response.getEntity());
    		} catch (IOException e) {
    		    e.printStackTrace();
    		}
    	    }
    	}
        }
    
    @SuppressWarnings("deprecation")
    private static RequestConfig buildRequestConfig(int timeout) {

	RequestConfig.Builder configBuilder = RequestConfig.custom();
	// 设置连接超时
	configBuilder.setConnectTimeout(timeout);
	// 设置读取超时
	configBuilder.setSocketTimeout(timeout);
	// 设置从连接池获取连接实例的超时
	configBuilder.setConnectionRequestTimeout(timeout);
	// 在提交请求之前 测试连接是否可用
	configBuilder.setStaleConnectionCheckEnabled(true);
	return configBuilder.build();
    }
    /**
     * 
     * <pre>
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
     * 如果网络上有负载均衡，需要负载均衡开启获取真实IP的功能，否则获取不到IP
     * url:https://blog.csdn.net/lh2420124680/article/details/77444066
     * @param request
     * @return String
     * @author debuggao  
     * 2019年8月17日 上午10:54:24
     * </pre>
     */
    public static String getIpAddress(HttpServletRequest request){
		String ip = request.getHeader("x-real-ip");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Forwarded-For");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {//获取第一个逗号之前的ip，例如返回：10.4.27.7, 10.4.27.7
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		
		return ip;
	}
    /**
     * 
     * <pre>
     * 获取http请求中header中的信息
     * @param httpRequest
     * @return String
     * @author debuggao  
     * 2019年9月27日 下午4:19:22
     * </pre>
     */
    public static String getHeaderContent(HttpServletRequest httpRequest){
    	StringBuffer buffer = new StringBuffer("\n");
    	List<String> headerNames = Collections.list(httpRequest.getHeaderNames());
    	headerNames.forEach(header->buffer.append(header+"="+httpRequest.getHeader(header)+"\n"));
    	return buffer.toString();
    }
    
}
