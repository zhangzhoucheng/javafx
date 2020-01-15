package com.zz.test.javafxmvn.commonbean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseObject {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected CommonResult getSuccessCommonResult() {
		return getCommonResult(Constants.System.Result.STATUS_SUCCESS, Constants.System.Result.STATUS_SUCCESS_MESSAGE);
	}
	protected CommonResult getSuccessCommonResult(String msg) {
		return getCommonResult(Constants.System.Result.STATUS_SUCCESS, msg);
	}

	protected CommonResult getFailCommonResult() {

		return getCommonResult(Constants.System.Result.STATUS_FAIL, Constants.System.Result.STATUS_FAIL_MESSAGE);
	}
	protected CommonResult getFailCommonResult(String msg) {
		
		return getCommonResult(Constants.System.Result.STATUS_FAIL, msg);
	}

	protected CommonResult getCommonResult(String status) {
		return getCommonResult(status, "");
	}

	protected CommonResult getCommonResult(String status, String msg) {

		CommonResult returnResult = new CommonResult();
		returnResult.setStatus(status);
		returnResult.setMessage(msg);
		return returnResult;
	}

	protected PageCommonResult getSuccessPageResult() {
		return getCommonPageResult(Constants.System.Result.STATUS_SUCCESS, Constants.System.Result.STATUS_SUCCESS_MESSAGE);
	}

	protected PageCommonResult getFailPageResult() {

		return getCommonPageResult(Constants.System.Result.STATUS_FAIL, Constants.System.Result.STATUS_FAIL_MESSAGE);
	}

	protected PageCommonResult getPageResult(String status) {
		return getCommonPageResult(status, "");
	}

	protected PageCommonResult getCommonPageResult(String status, String msg) {

		PageCommonResult returnResult = new PageCommonResult();
		returnResult.setStatus(status);
		returnResult.setMessage(msg);
		return returnResult;
	}

	protected boolean isSuccessResult(CommonResult returnResult) {

		if (returnResult == null) {
			return false;
		}
		if (returnResult.getStatus().equalsIgnoreCase(Constants.System.Result.STATUS_SUCCESS)) {
			return true;
		}
		return false;
	}
}
