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
}
