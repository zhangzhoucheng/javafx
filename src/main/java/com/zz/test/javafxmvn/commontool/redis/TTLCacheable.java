package com.zz.test.javafxmvn.commontool.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zz.test.javafxmvn.commonbean.Constants;

/**
 * 
 * <note>
 * Desc： 枚举超时时间设置
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 09:24:42
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 09:24:42    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Retention(RetentionPolicy.RUNTIME)    
@Target({ElementType.METHOD})   
public @interface TTLCacheable{  
	/**key=0表示将参数列表的第一个值和type组合作为缓存的key值,key=0,1为第一个、第二个值与type组合...eg:key=type:123*/
    public String key() default "0"; 
    /**和key组合作为缓存的key*/
    public String type() default "";
    /**缓存失效时间*/
    public Constants.ExpireTime expire() default Constants.ExpireTime.NONE; // 缓存时效,默认无限期  
    /**是否重置TTL时间，默认false，不重置*/
    public boolean isRsetTTL() default false;
    /**反序列化的class类*/
    @SuppressWarnings("rawtypes")
	public Class clazzType();
  
}  