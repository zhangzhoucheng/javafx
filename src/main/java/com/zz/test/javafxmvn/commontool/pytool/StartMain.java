package com.zz.test.javafxmvn.commontool.pytool;

import java.io.IOException;

/**
 * 
 * <note>
 * Desc： 在StartPy启动有问题。导致py中的定时任务不能正常执行。所以构建了该StartMain类
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-05-15 19:38:09
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-05-15 19:38:09    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class StartMain {
	public static void main(String[] args) {

				try {
					Runtime.getRuntime().exec(new String[] { "cmd", "/c", args[0] });
					Thread.currentThread().notify();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		

	}
}
