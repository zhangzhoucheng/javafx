package com.zz.test.javafxmvn.common.entity;

import java.util.Map;
import java.util.Map.Entry;

import com.zz.test.javafxmvn.commontool.threadtool.ButiToolClassZz;

public class PyProcess {
    private Integer processId;

    private String processCode;

    private String processName;

    private String typeCode;

    private String processRemark;

    private String processStatus;

    private Integer disable;

    private String processCron;

    private Integer cronCalibration;

    private Integer everyDayStartFlag;

    private Integer executeMax;

    private Integer processLimittime;
    
    public PyProcess() {
		
	}

	public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode == null ? null : processCode.trim();
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getProcessRemark() {
        return processRemark;
    }

    public void setProcessRemark(String processRemark) {
        this.processRemark = processRemark == null ? null : processRemark.trim();
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus == null ? null : processStatus.trim();
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public String getProcessCron() {
        return processCron;
    }

    public void setProcessCron(String processCron) {
        this.processCron = processCron == null ? null : processCron.trim();
    }

    public Integer getCronCalibration() {
        return cronCalibration;
    }

    public void setCronCalibration(Integer cronCalibration) {
        this.cronCalibration = cronCalibration;
    }

    public Integer getEveryDayStartFlag() {
        return everyDayStartFlag;
    }

    public void setEveryDayStartFlag(Integer everyDayStartFlag) {
        this.everyDayStartFlag = everyDayStartFlag;
    }

    public Integer getExecuteMax() {
        return executeMax;
    }

    public void setExecuteMax(Integer executeMax) {
        this.executeMax = executeMax;
    }

    public Integer getProcessLimittime() {
        return processLimittime;
    }

    public void setProcessLimittime(Integer processLimittime) {
        this.processLimittime = processLimittime;
    }
}