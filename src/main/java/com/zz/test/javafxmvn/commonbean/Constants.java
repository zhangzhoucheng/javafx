/**
 * com.hpe.bbc.common.Constants.java Copyright (c) 2017 xx Development Company, L.P. All rights reserved.
 */
package com.zz.test.javafxmvn.commonbean;


/**
 * <pre>
 * Desc： 
 * &#64;author   peter.fu
 * &#64;date     Jul 19, 2017 12:49:32 PM
 * &#64;version  1.0
 *  
 * REVISIONS: 
 * Version 	   Date 		    Author 			  Description
 * ------------------------------------------------------------------- 
 * 1.0 		  Jul 19, 2017 	   peter.fu 	         1. Created this class.
 * </pre>
 */
public class Constants {

	public static final String SIMPLEDATEFORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	public static class System {

		public static class Result {

			public static final String STATUS_SUCCESS = "1";
			public static final String STATUS_SUCCESS_MESSAGE = "成功";

			public static final String STATUS_FAIL = "0";
			public static final String STATUS_FAIL_MESSAGE = "失败";

			public static final String STATUS_FAIL_NOT_LOGGED_IN = "-1";
			public static final String STATUS_FAIL_NOT_LOGGED_IN_MESSAGE = "用户未登录";

			public static final String STATUS_FAIL_ACCOUNT_IS_LOCKED = "-2";
			public static final String STATUS_FAIL_ACCOUNT_IS_LOCKED_MESSAGE = "账号被锁定";

			public static final String STATUS_FAIL_NOT_AUTH = "-3";
			public static final String STATUS_FAIL_NOT_AUTH_MESSAGE = "没有权限";

			public static final String STATUS_FAIL_REPEATED_SUBMIT = "-4";
			public static final String STATUS_FAIL_REPEATED_SUBMIT_MESSAGE = "重复提交";

			public static final String STATUS_FAIL_TIMEOUT = "-5";
			public static final String STATUS_FAIL_TIMEOUT_MESSAGE = "连接超时";

		}

		public static class Connection {

			public static final int TIME_OUT = 30;
		}
	}

	public static class FileMgr {

		public static class Status {

			public static final int VALID = 1;// "有效"
			public static final int INVALID = 0;// 无效

		}

		public static class FileType {

			public static final String OTHER = "other"; // 其他
			public static final String FLOW = "flow"; // 流程
			public static final String TICKET_ERROR = "ticket_error";// 稽核单差错
			public static final String SMRZ_IMPORT_FLAG = "smrz_import_flag";//实名认证，差错修改，导入数据中错误数据入库标志
			public static final String SMRZ_IMPORT_FK_FLAG = "smrz_import_fk_flag";

		}
	}
	
	public static class Uploadfilesuffix{
		public static class Swjh{
			public static final String[] SWJH_UPLOADFILESUFFIX= {"png", "jpg", "jpeg", "gif", "bmp", "flv", "swf", "mkv", "avi", "rm", "rmvb", "mpeg", "mpg", "ogg", "ogv", "mid", "rar", "zip", "tar", "gz", "7z", "bz2", "cab", "iso", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "pdf", "txt", "md", "xml", "msg", "eml"};
		}
	}
	//错误码
	public static class ErrorCode{
		public static String getErrorCode(String errorCode){
			switch (errorCode) {
			case "XM0001":return XM0001;
			case "XL0002":return XL0002;
			case "XL0003":return XL0003;
			default:return "";
			}
		}
		private static final String XM0001 = "此稽核单状态不能被操作。";
		private static final String XL0002 = "文件格式不正确";
		private static final String XL0003 = "稽核时间已超过24小时,不允许取消稽核通过/撤销差错";
	}
	
	/**
	 * 
	 * <note>
	 * Desc： redis枚举超时时间
	 * @author jld.zhangzhou
	 * @email idiot_jillidan@163.com;
	 * @re be willing to communicate
	 * @refactor for jld
	 * @datetime 2020-04-17 09:23:45
	 * @location mobile base 3th,BeiJing 
	 * version  1.0
	 *  
	 * @REVISIONS: 
	 * Version 	        Date 		         Author             Location                   Description          
	 * ------------------------------------------------------------------------------------------------------  
	 * 1.0 		  2020-04-17 09:23:45    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
	 * </note>
	 */
	public static enum ExpireTime {

		/**
		 * 无固定期限
		 */
		NONE(0, "无固定期限")

		/**
		 * 1秒钟
		 */
		,ONE_SEC(1, "1秒钟")

		/**
		 * 5秒钟
		 */
	    ,FIVE_SEC(5, "5秒钟")  
	  
	    /** 
	     * 10秒钟 
	     */  
	    ,TEN_SEC(10, "10秒钟")  
	  
	    /** 
	     * 30秒钟 
	     */  
	    ,HALF_A_MIN(30, "30秒钟")  
	  
	    /** 
	     * 1分钟 
	     */  
	    ,ONE_MIN(60, "1分钟")  
	  
	    /** 
	     * 5分钟 
	     */  
	    ,FIVE_MIN(5 * 60, "5分钟")  
	  
	    /** 
	     * 10分钟 
	     */  
	    ,TEN_MIN(10 * 60, "10分钟")  
	      
	    /** 
	     * 20分钟 
	     */  
	    ,TWENTY_MIN(20 * 60, "20分钟")  
	  
	    /** 
	     * 30分钟 
	     */  
	    ,HALF_AN_HOUR(30 * 60, "30分钟")  
	  
	    /** 
	     * 1小时 
	     */  
	    ,ONE_HOUR(60 * 60, "1小时")  
	    /** 
	     * 4小时 
	     */  
	    ,FOUR_HOUR(60 * 60 * 4, "4小时")  
	  
	    /** 
	     * 1天 
	     */  
	    ,ONE_DAY(24 * 60 * 60, "1天")  
	  
	    /** 
	     * 半天
	     */  
	    ,HALF_A_DAY(12 * 60 * 60, "半天")  
	    /** 
	     * 1个月 
	     */  
	    ,ONE_MON(30 * 24 * 60 * 60, "1个月")  
	  
	    /** 
	     * 1年 
	     */  
	    ,ONE_YEAR(365 * 24 * 60 * 60, "1年")  
	  
	    ;  
	      
	    /** 
	     * 时间 
	     */  
	    private final int time;  
	    /** 
	     * 描述 
	     */  
	    private final String desc;  
	      
	    ExpireTime(int time, String desc) {  
	        this.time = time;  
	        this.desc = desc;  
	    }  
	  
	      
	    /** 
	     * 获取具体时间 
	     * @return 
	     */  
	    public int getTime() {  
	        return time;  
	    }  
	  
	    /** 
	     * 获取时间描述信息 
	     * @return 
	     */  
	    public String getDesc() {  
	        return desc;  
	    }  
	      
	    /** 
	     * 根据时间匹配失效期限 
	     * @param time 
	     * @return 
	     */  
	    public static ExpireTime match(int time){  
	        if(NONE.getTime() == time){  
	            return NONE;  
	        }else if(ONE_SEC.getTime() ==  time){  
	            return ONE_SEC;  
	        }else if(FIVE_SEC.getTime() ==  time){  
	            return FIVE_SEC;  
	        }else if(TEN_SEC.getTime() ==  time){  
	            return TEN_SEC;  
	        }else if(HALF_A_MIN.getTime() ==  time){  
	            return HALF_A_MIN;  
	        }else if(ONE_MIN.getTime() ==  time){  
	            return ONE_MIN;  
	        }else if(FIVE_MIN.getTime() ==  time){  
	            return FIVE_MIN;  
	        }else if(TEN_MIN.getTime() ==  time){  
	            return TEN_MIN;  
	        }else if(TWENTY_MIN.getTime() == time){  
	            return TWENTY_MIN;  
	        }else if(HALF_AN_HOUR.getTime() ==  time){  
	            return HALF_AN_HOUR;  
	        }else if(ONE_HOUR.getTime() ==  time){  
	            return ONE_HOUR;  
	        }else if(ONE_DAY.getTime() ==  time){  
	            return ONE_DAY;  
	        }else if(ONE_MON.getTime() ==  time){  
	            return ONE_MON;  
	        }else if(ONE_YEAR.getTime() ==  time){  
	            return ONE_YEAR;  
	        }  
	        return HALF_AN_HOUR;  
	    }   
	}
}
