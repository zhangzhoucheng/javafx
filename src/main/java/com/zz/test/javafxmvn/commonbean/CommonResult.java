package com.zz.test.javafxmvn.commonbean;

import java.util.HashMap;

public class CommonResult {

    protected String status  = "1";
    protected String message = "";

    // 结果集数据行
    protected Object body    = new HashMap<>();

    public CommonResult() {
	super();
    }

    public CommonResult(Object body) {
		super();
		this.body = body;
	}

	public CommonResult(String status, String message) {
	super();
	this.status = status;
	this.message = message;
    }

    @Override
    public String toString() {
	return "CommonResult [status=" + status + ", message=" + message + ", body=" + body + "]";
    }

    public String getStatus() {
	return this.status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getMessage() {
	return this.message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * @return the body
     */
    public Object getBody() {
	return this.body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Object body) {
	this.body = body;
    }

}
