package com.zz.test.javafxmvn.commontool.threadtool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zz.test.javafaxmvn.commoninterceptor.AccessInfoContextHolder;
import com.zz.test.javafxmvn.commonbean.CommonRequest;
import com.zz.test.javafxmvn.commondb.CommonDb;


/**
 * 
 * <note>
 * Desc： 线程池工具类
 * @author jld.zhangzhou
 * @refactor for jld
 * @datetime 2018-12-10 14:14:27
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2018-12-10 14:14:27    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class ThreadPollTool {
	
	private static final Logger logger = LoggerFactory.getLogger(ThreadPollTool.class);
    private static  CommonDb mybatisDao=(CommonDb) SpringUtils.getBean("commonDb");
	private static final int count=14;//线程池数量10
	private static ExecutorService executor = Executors.newFixedThreadPool(count);//线程池数量

	public static void executorThread(Runnable runnable) {
		executor.execute(runnable);
	}

	/**
	 * Desc:根据sql数组，通过线程去执行，所有线程执行完，就继续下一步操作
	 * @author jld.zhangzhou
	 * @datetime 2018-12-10 14:35:59
	 * @modify_record:
	 * @param batchSql
	 * @param params
	 * @throws InterruptedException
	 */
	public static void  executorBatchSql(String[]batchSql,Map<String, Object> params) throws InterruptedException {
		int flag=batchSql.length;
		CountDownLatch countDownLatch = new CountDownLatch(flag);//一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
		for(int i=0;i<batchSql.length;i++) {	
			ThreadPollTool.executorThread(new BatchOperateRunnable(batchSql[i], params, countDownLatch));
		}
		countDownLatch.await();//等待flag个线程结束才，否则一直等待
	}

	/**
	 * Desc:根据sql数组，通过线程去执行，所有线程执行完，就继续下一步操作
	 * @author jld.zhangzhou
	 * @datetime 2020-03-17 18:33:41
	 * @modify_record:
	 * @param batchSql
	 * @param commonRequest
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean executorBatchSql(String [] batchSql, CommonRequest commonRequest) throws InterruptedException {

		Map<String,Boolean> mapFlag = new HashMap<>();
		int flag=batchSql.length;

		CountDownLatch countDownLatch = new CountDownLatch(flag);//一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
		for(int i=0;i<batchSql.length;i++) {	
			int inew = i;//匿名内部类final问题
			
			ThreadPollTool.executorThread(new Runnable() {
				@Override
				public void run() {					
					try {
						ThreadPollTool.mybatisDao.update(batchSql[inew], commonRequest);
						
					}catch(Exception e){
						mapFlag.put("errTrue", true);//其中一个线程出问题。则记录
						logger.error(batchSql[inew] + " error!", e);
						e.printStackTrace();
						
					}finally{
						countDownLatch.countDown();
					}
				}
			});
			
			
		}
		countDownLatch.await();//等待flag个线程结束才，否则一直等待
		
		if(mapFlag.get("errTrue") != null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Desc:异步执行多个sql，不关注是否执行成功，或者报错；
	 * @author jld.zhangzhou
	 * @datetime 2020-03-17 18:33:41
	 * @modify_record:
	 * @param batchSql
	 * @param commonRequest
	 * @return
	 * @throws InterruptedException
	 */
	public static void executorBatchSqlAsyn(String [] batchSql, CommonRequest commonRequest) throws InterruptedException {

		//CountDownLatch countDownLatch = new CountDownLatch(flag);//一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
		for(int i=0;i<batchSql.length;i++) {	
			int inew = i;//匿名内部类final问题
			
			ThreadPollTool.executorThread(new Runnable() {
				@Override
				public void run() {					
					try {
						ThreadPollTool.mybatisDao.update(batchSql[inew], commonRequest);
						logger.warn("@@planId" + commonRequest.getParams().get("planId") + "@executorBatchSqlAsyn," + batchSql[inew] + " success!");
						
					}catch(Exception e){
						logger.error("@@planId" + commonRequest.getParams().get("planId") + "@executorBatchSqlAsyn," + batchSql[inew] + " error!");
						String traceId = AccessInfoContextHolder.getTraceId() == null ? StringUtils.trimToEmpty(commonRequest.getTraceId()) : AccessInfoContextHolder.getTraceId();
						logger.error("@@taceid:" + traceId + ","+batchSql[inew] + " error!", e);
						e.printStackTrace();
					}finally{
						//countDownLatch.countDown();
					}
				}
			});
			
			
		}
		
	}
	
	/**
	 * Desc:该方法用于多线程执行sql 数组，之后把结果合并起来。
	 * @author jld.zhangzhou
	 * @datetime 2019-08-19 15:27:41
	 * @modify_record:
	 * @param batchSql
	 * @param commonRequest
	 * @return
	 * @throws InterruptedException
	 */
	public static List<Map<String, Object>>   executorBatchSqlToGetServalList(String[]batchSql,CommonRequest commonRequest) throws InterruptedException {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<Integer,List<Map<String, Object>>> map = new HashMap<>();
		int flag=batchSql.length;
		CountDownLatch countDownLatch = new CountDownLatch(flag);//一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
		for(int i=0;i<batchSql.length;i++) {	
			int inew = i;//匿名内部类final问题
			ThreadPollTool.executorThread(new Runnable() {
				@SuppressWarnings("unchecked")
				@Override
				public void run() {					
					try {
						System.out.println("@@@sql:"+inew);
						//list.addAll((Collection<? extends Map<String, Object>>) ThreadPollTool.mybatisDao.getList(batchSql[inew], commonRequest).getBody());
						//Object ob = ThreadPollTool.mybatisDao.getList(batchSql[inew], commonRequest).getBody();
						map.put(inew, (List<Map<String, Object>>) ThreadPollTool.mybatisDao.getList(batchSql[inew], commonRequest).getBody());
					}catch(Exception e){
						String traceId = AccessInfoContextHolder.getTraceId() == null ? StringUtils.trimToEmpty(commonRequest.getTraceId()) : AccessInfoContextHolder.getTraceId();
						logger.error("@@taceid:" + traceId + ","+batchSql[inew] + " error!", e);
						e.printStackTrace();
					}finally{
						countDownLatch.countDown();
					}
				}
			});
		}
		countDownLatch.await();//等待flag个线程结束才，否则一直等待
		for(int j = 0; j < batchSql.length; j++) {
			list.addAll(map.get(j));
		}
		return list;
	}
	
	/**
	 * Desc:多线程执行 methods中的n个方法，返回查询到的多个结果的合集。
	 * @author jld.zhangzhou
	 * @datetime 2020-03-24 20:05:11
	 * @modify_record:
	 * @param methods
	 * @param commonRequest
	 * @return
	 * @throws InterruptedException
	 */
	public static List<Map<String, Object>> executorBatchMethodByMoreThreadGetList(String [] methods, CommonRequest commonRequest) throws InterruptedException {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<Integer,List<Map<String, Object>>> map = new HashMap<>();
		Map<String,Boolean> mapFlag = new HashMap<>();
		int flag=methods.length;

		CountDownLatch countDownLatch = new CountDownLatch(flag);//一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
		for(int i=0;i<methods.length;i++) {	
			int inew = i;//匿名内部类final问题
			
			ThreadPollTool.executorThread(new Runnable() {
				@SuppressWarnings("unchecked")
				@Override
				public void run() {					
					try {
						System.out.println("@@@:"+inew+","+methods[inew]);
						map.put(inew, (List<Map<String, Object>>) ThreadPollTool.reflexMethod(methods[inew].substring(methods[inew].lastIndexOf(".")+1), methods[inew].substring(0, methods[inew].lastIndexOf(".")), commonRequest));
					}catch(Exception e){
						mapFlag.put("errTrue", true);//其中一个线程出问题。则记录
						String traceId = AccessInfoContextHolder.getTraceId() == null ? StringUtils.trimToEmpty(commonRequest.getTraceId()) : AccessInfoContextHolder.getTraceId();
						logger.error("@@taceid:" + traceId + ","+methods[inew] + " error!", e);
						e.printStackTrace();
					}finally{
						countDownLatch.countDown();
					}
				}
			});
			
			
		}
		countDownLatch.await();//等待flag个线程结束才，否则一直等待
		
		if(mapFlag.get("errTrue") != null) {
			return null;
		}
		
		for(int j = 0; j < methods.length; j++) {
			list.addAll(map.get(j));
		}
		return list;
	}
	
	/**
	 * Desc:多线程执行 methods中的n个方法，返回查询到的多个结果（Map<Long, Map<String, Object>>）的合集 （ List<Map<Long, Map<String, Object>>>）
	 * @author jld.zhangzhou
	 * @datetime 2020-03-24 20:05:11
	 * @modify_record:
	 * @param methods
	 * @param commonRequest
	 * @return
	 * @throws InterruptedException
	 */
	public static List<Map<Long, Map<String, Object>>> executorBatchMethodByMoreThreadGetListMM(String [] methods, CommonRequest commonRequest) throws InterruptedException {
		List<Map<Long, Map<String, Object>>> list = new ArrayList<>();
		Map<Integer,Map<Long, Map<String, Object>>> map = new HashMap<>();
		Map<String,Boolean> mapFlag = new HashMap<>();
		int flag=methods.length;

		CountDownLatch countDownLatch = new CountDownLatch(flag);//一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
		for(int i=0;i<methods.length;i++) {	
			int inew = i;//匿名内部类final问题
			
			ThreadPollTool.executorThread(new Runnable() {
				@SuppressWarnings("unchecked")
				@Override
				public void run() {					
					try {
						System.out.println("@@@:"+inew+","+methods[inew]);
						map.put(inew, (Map<Long, Map<String, Object>>) ThreadPollTool.reflexMethod(methods[inew].substring(methods[inew].lastIndexOf(".")+1), methods[inew].substring(0, methods[inew].lastIndexOf(".")), commonRequest));
					}catch(Exception e){
						mapFlag.put("errTrue", true);//其中一个线程出问题。则记录
						String traceId = AccessInfoContextHolder.getTraceId() == null ? StringUtils.trimToEmpty(commonRequest.getTraceId()) : AccessInfoContextHolder.getTraceId();
						logger.error("@@taceid:" + traceId + ","+methods[inew] + " error!", e);
						
						e.printStackTrace();
					}finally{
						countDownLatch.countDown();
					}
				}
			});
			
			
		}
		countDownLatch.await();//等待flag个线程结束才，否则一直等待
		
		if(mapFlag.get("errTrue") != null) {
			return null;
		}
		
		for(int j = 0; j < methods.length; j++) {
			list.add(map.get(j));
		}
		return list;
	}
	
	/**
	 * Desc:多线程执行 methods中的n个方法，成功返回true(注意当false需要考虑手动事务回滚）
	 * @author jld.zhangzhou
	 * @datetime 2020-03-17 16:17:16
	 * @modify_record:
	 * @param methods
	 * @param commonRequest
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean executorBatchMethodByMoreThread(String [] methods, CommonRequest commonRequest) throws InterruptedException {

		Map<String,Boolean> mapFlag = new HashMap<>();
		int flag=methods.length;

		CountDownLatch countDownLatch = new CountDownLatch(flag);//一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
		for(int i=0;i<methods.length;i++) {	
			int inew = i;//匿名内部类final问题
			
			ThreadPollTool.executorThread(new Runnable() {
				@SuppressWarnings("unchecked")
				@Override
				public void run() {					
					try {
						System.out.println("@@@:"+inew+","+methods[inew]);
						ThreadPollTool.reflexMethod(methods[inew].substring(methods[inew].lastIndexOf(".")+1), methods[inew].substring(0, methods[inew].lastIndexOf(".")), commonRequest);
						
					}catch(Exception e){
						mapFlag.put("errTrue", true);//其中一个线程出问题。则记录
						e.printStackTrace();
						String traceId = AccessInfoContextHolder.getTraceId() == null ? StringUtils.trimToEmpty(commonRequest.getTraceId()) : AccessInfoContextHolder.getTraceId();
						logger.error("@@taceid:" + traceId + ","+methods[inew] + " error!", e);
					}finally{
						countDownLatch.countDown();
					}
				}
			});
			
			
		}
		countDownLatch.await();//等待flag个线程结束才，否则一直等待
		
		if(mapFlag.get("errTrue") != null) {
			return false;
		}
		return true;
	}
	
	public static boolean executorBatchMethodByMoreThread(String [] methods, Map<String, Object> map) throws InterruptedException {

		Map<String,Boolean> mapFlag = new HashMap<>();
		int flag=methods.length;

		CountDownLatch countDownLatch = new CountDownLatch(flag);//一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
		for(int i=0;i<methods.length;i++) {	
			int inew = i;//匿名内部类final问题
			
			ThreadPollTool.executorThread(new Runnable() {
				@SuppressWarnings("unchecked")
				@Override
				public void run() {					
					try {
						System.out.println("@@@:"+inew+","+methods[inew]);
						ThreadPollTool.reflexMethodMap(methods[inew].substring(methods[inew].lastIndexOf(".")+1), methods[inew].substring(0, methods[inew].lastIndexOf(".")), map);
						
					}catch(Exception e){
						mapFlag.put("errTrue", true);//其中一个线程出问题。则记录
						String traceId = AccessInfoContextHolder.getTraceId() == null ? StringUtils.trimToEmpty((String) map.get("traceId")) : AccessInfoContextHolder.getTraceId();
						logger.error("@@taceid:" + traceId + ","+methods[inew] + " error!", e);
						e.printStackTrace();
					}finally{
						countDownLatch.countDown();
					}
				}
			});
			
			
		}
		countDownLatch.await();//等待flag个线程结束才，否则一直等待
		
		if(mapFlag.get("errTrue") != null) {
			return false;
		}
		return true;
	}
	
	public static Object reflexMethod(String methodName, String beanName, CommonRequest commonRequest) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		//Object theClass=Class.forName(tableList.classMethod).newInstance();//获取class实例，即获取service层的对应class
		Object ob=SpringUtils.getBean(beanName);
		Method method=ob.getClass().getMethod(methodName,commonRequest.getClass());
		return method.invoke(ob, commonRequest);
	}
	
	public static Object reflexMethodMap(String methodName, String beanName, Map<String, Object> map) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		//Object theClass=Class.forName(tableList.classMethod).newInstance();//获取class实例，即获取service层的对应class
		Object ob=SpringUtils.getBean(beanName);
		Method method=ob.getClass().getMethod(methodName,map.getClass());
		return method.invoke(ob, map);
	}
}
