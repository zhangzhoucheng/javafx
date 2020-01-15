package com.zz.test.javafxmvn.jna;

/**
 * 
 * <note>
 * Desc：jna常量 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-14 10:19:06
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-01-14 10:19:06    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class JnaConst {
	public static final int WM_MOUSEACTIVATE = 0x21; // 获得扩展窗口风格
	public static final int GWL_EXSTYLE = -20; // 获得扩展窗口风格
	public static final int WS_EX_NOACTIVATE = 0x8000000; // 不激活窗口
	public static final int MA_NOACTIVATE = 3; // 鼠标进来不获得焦点
	
	public static final String TITILE_TIM1 = "TIM_"; // 指定句柄 titie，tim的title
	public static final String TITILE_TIM2 = "qq"; // 指定句柄 titie，tim的title
	public static final String [] APPOINT_LIST = new String[] {//需要处理的句柄列表。
			JnaConst.TITILE_TIM1,
			JnaConst.TITILE_TIM2
			
	};

}
