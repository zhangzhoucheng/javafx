package com.zz.test.javafxmvn.commontool.threadtool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.zz.test.javafxmvn.commondb.CommonDb;




/**
 * 
 * <note>
 * Desc： 分线程地批量修改
 * @author jld.zhangzhou
 * @refactor for jld
 * @datetime 2018-12-10 13:13:00
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2018-12-10 13:13:00    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class BatchOperateRunnable implements Runnable {
	//@Autowired//线程类暂时无法捕捉注解bean,所以通过如下方法
    private  CommonDb commonDb=(CommonDb) SpringUtils.getBean("commonDb");
	private String batchSql;//批量update mapper语句
	//private int times;//控制线程数量
	private Map<String, Object> params = new HashMap<String, Object>();//参数
	private CountDownLatch count;//线程操作辅助函数
	
	public BatchOperateRunnable(String batchSql,Map<String, Object> params,CountDownLatch countDownLatch) {//构造方法
		this.batchSql=batchSql;
		this.params=params;
		this.count=countDownLatch;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.commonDb.update(batchSql, params);
			//System.out.println("当前线程："+Thread.currentThread().getId()+"。sql:"+batchSql+"。params:"+params.toString());
		} 
		catch(Exception e){

			e.printStackTrace();
			//System.out.println("exception:"+e.getMessage());
		}finally {
			// TODO: handle finally clause
			count.countDown();
		}
	}

}
