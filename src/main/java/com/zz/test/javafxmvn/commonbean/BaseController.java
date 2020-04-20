/**
 * 
 */
package com.zz.test.javafxmvn.commonbean;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zz.test.javafxmvn.commontool.HttpUtils;


/**
 * <pre>
 * Desc： 
 *  
 * REVISIONS: 
 * Version 	   Date             Author      	  Description
 * ------------------------------------------------------------------- 
 * 1.0 		  Aug 21, 2017 	   peter.fu 	         1. Created this class. 
 *
 * </pre>
 * @author peter.fu
 * @date Aug 21, 2017 3:20:25 PM
 * @version 1.0
 */
public class BaseController extends BaseObject {

	/**
	 * <pre>
	 *  Desc   
	 * </pre>
	 * @param request
	 * @return
	 * @author peter.fu
	 * @date Aug 21, 2017 3:20:34 PM
	 */
	protected String getParameterStr(HttpServletRequest request) {

		return HttpUtils.getParameterStr(request);
	}

	/**
	 * <pre>
	 *  Desc 获取request中的参数  
	 * </pre>
	 * @param request
	 * @return
	 * @author peter.fu
	 * @date Aug 21, 2017 3:20:47 PM
	 */
	protected Map<String, Object> getParameterMap(HttpServletRequest request) {

		return HttpUtils.getParameterMap(request);
	}

}
