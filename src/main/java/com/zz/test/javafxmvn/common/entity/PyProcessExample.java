package com.zz.test.javafxmvn.common.entity;

import java.util.ArrayList;
import java.util.List;

public class PyProcessExample  {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PyProcessExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }
    



	protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andProcessIdIsNull() {
            addCriterion("process_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessIdIsNotNull() {
            addCriterion("process_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessIdEqualTo(Integer value) {
            addCriterion("process_id =", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotEqualTo(Integer value) {
            addCriterion("process_id <>", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThan(Integer value) {
            addCriterion("process_id >", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("process_id >=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThan(Integer value) {
            addCriterion("process_id <", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThanOrEqualTo(Integer value) {
            addCriterion("process_id <=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdIn(List<Integer> values) {
            addCriterion("process_id in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotIn(List<Integer> values) {
            addCriterion("process_id not in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdBetween(Integer value1, Integer value2) {
            addCriterion("process_id between", value1, value2, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("process_id not between", value1, value2, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessCodeIsNull() {
            addCriterion("process_code is null");
            return (Criteria) this;
        }

        public Criteria andProcessCodeIsNotNull() {
            addCriterion("process_code is not null");
            return (Criteria) this;
        }

        public Criteria andProcessCodeEqualTo(String value) {
            addCriterion("process_code =", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotEqualTo(String value) {
            addCriterion("process_code <>", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeGreaterThan(String value) {
            addCriterion("process_code >", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeGreaterThanOrEqualTo(String value) {
            addCriterion("process_code >=", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeLessThan(String value) {
            addCriterion("process_code <", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeLessThanOrEqualTo(String value) {
            addCriterion("process_code <=", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeLike(String value) {
            addCriterion("process_code like", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotLike(String value) {
            addCriterion("process_code not like", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeIn(List<String> values) {
            addCriterion("process_code in", values, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotIn(List<String> values) {
            addCriterion("process_code not in", values, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeBetween(String value1, String value2) {
            addCriterion("process_code between", value1, value2, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotBetween(String value1, String value2) {
            addCriterion("process_code not between", value1, value2, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessNameIsNull() {
            addCriterion("process_name is null");
            return (Criteria) this;
        }

        public Criteria andProcessNameIsNotNull() {
            addCriterion("process_name is not null");
            return (Criteria) this;
        }

        public Criteria andProcessNameEqualTo(String value) {
            addCriterion("process_name =", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotEqualTo(String value) {
            addCriterion("process_name <>", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameGreaterThan(String value) {
            addCriterion("process_name >", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameGreaterThanOrEqualTo(String value) {
            addCriterion("process_name >=", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLessThan(String value) {
            addCriterion("process_name <", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLessThanOrEqualTo(String value) {
            addCriterion("process_name <=", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLike(String value) {
            addCriterion("process_name like", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotLike(String value) {
            addCriterion("process_name not like", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameIn(List<String> values) {
            addCriterion("process_name in", values, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotIn(List<String> values) {
            addCriterion("process_name not in", values, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameBetween(String value1, String value2) {
            addCriterion("process_name between", value1, value2, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotBetween(String value1, String value2) {
            addCriterion("process_name not between", value1, value2, "processName");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIsNull() {
            addCriterion("type_code is null");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIsNotNull() {
            addCriterion("type_code is not null");
            return (Criteria) this;
        }

        public Criteria andTypeCodeEqualTo(String value) {
            addCriterion("type_code =", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotEqualTo(String value) {
            addCriterion("type_code <>", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeGreaterThan(String value) {
            addCriterion("type_code >", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("type_code >=", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLessThan(String value) {
            addCriterion("type_code <", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("type_code <=", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeLike(String value) {
            addCriterion("type_code like", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotLike(String value) {
            addCriterion("type_code not like", value, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeIn(List<String> values) {
            addCriterion("type_code in", values, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotIn(List<String> values) {
            addCriterion("type_code not in", values, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeBetween(String value1, String value2) {
            addCriterion("type_code between", value1, value2, "typeCode");
            return (Criteria) this;
        }

        public Criteria andTypeCodeNotBetween(String value1, String value2) {
            addCriterion("type_code not between", value1, value2, "typeCode");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkIsNull() {
            addCriterion("process_remark is null");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkIsNotNull() {
            addCriterion("process_remark is not null");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkEqualTo(String value) {
            addCriterion("process_remark =", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkNotEqualTo(String value) {
            addCriterion("process_remark <>", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkGreaterThan(String value) {
            addCriterion("process_remark >", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("process_remark >=", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkLessThan(String value) {
            addCriterion("process_remark <", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkLessThanOrEqualTo(String value) {
            addCriterion("process_remark <=", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkLike(String value) {
            addCriterion("process_remark like", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkNotLike(String value) {
            addCriterion("process_remark not like", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkIn(List<String> values) {
            addCriterion("process_remark in", values, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkNotIn(List<String> values) {
            addCriterion("process_remark not in", values, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkBetween(String value1, String value2) {
            addCriterion("process_remark between", value1, value2, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkNotBetween(String value1, String value2) {
            addCriterion("process_remark not between", value1, value2, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIsNull() {
            addCriterion("process_status is null");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIsNotNull() {
            addCriterion("process_status is not null");
            return (Criteria) this;
        }

        public Criteria andProcessStatusEqualTo(String value) {
            addCriterion("process_status =", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotEqualTo(String value) {
            addCriterion("process_status <>", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusGreaterThan(String value) {
            addCriterion("process_status >", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusGreaterThanOrEqualTo(String value) {
            addCriterion("process_status >=", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLessThan(String value) {
            addCriterion("process_status <", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLessThanOrEqualTo(String value) {
            addCriterion("process_status <=", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLike(String value) {
            addCriterion("process_status like", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotLike(String value) {
            addCriterion("process_status not like", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIn(List<String> values) {
            addCriterion("process_status in", values, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotIn(List<String> values) {
            addCriterion("process_status not in", values, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusBetween(String value1, String value2) {
            addCriterion("process_status between", value1, value2, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotBetween(String value1, String value2) {
            addCriterion("process_status not between", value1, value2, "processStatus");
            return (Criteria) this;
        }

        public Criteria andDisableIsNull() {
            addCriterion("disable is null");
            return (Criteria) this;
        }

        public Criteria andDisableIsNotNull() {
            addCriterion("disable is not null");
            return (Criteria) this;
        }

        public Criteria andDisableEqualTo(Integer value) {
            addCriterion("disable =", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotEqualTo(Integer value) {
            addCriterion("disable <>", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableGreaterThan(Integer value) {
            addCriterion("disable >", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableGreaterThanOrEqualTo(Integer value) {
            addCriterion("disable >=", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableLessThan(Integer value) {
            addCriterion("disable <", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableLessThanOrEqualTo(Integer value) {
            addCriterion("disable <=", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableIn(List<Integer> values) {
            addCriterion("disable in", values, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotIn(List<Integer> values) {
            addCriterion("disable not in", values, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableBetween(Integer value1, Integer value2) {
            addCriterion("disable between", value1, value2, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotBetween(Integer value1, Integer value2) {
            addCriterion("disable not between", value1, value2, "disable");
            return (Criteria) this;
        }

        public Criteria andProcessCronIsNull() {
            addCriterion("process_cron is null");
            return (Criteria) this;
        }

        public Criteria andProcessCronIsNotNull() {
            addCriterion("process_cron is not null");
            return (Criteria) this;
        }

        public Criteria andProcessCronEqualTo(String value) {
            addCriterion("process_cron =", value, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronNotEqualTo(String value) {
            addCriterion("process_cron <>", value, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronGreaterThan(String value) {
            addCriterion("process_cron >", value, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronGreaterThanOrEqualTo(String value) {
            addCriterion("process_cron >=", value, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronLessThan(String value) {
            addCriterion("process_cron <", value, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronLessThanOrEqualTo(String value) {
            addCriterion("process_cron <=", value, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronLike(String value) {
            addCriterion("process_cron like", value, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronNotLike(String value) {
            addCriterion("process_cron not like", value, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronIn(List<String> values) {
            addCriterion("process_cron in", values, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronNotIn(List<String> values) {
            addCriterion("process_cron not in", values, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronBetween(String value1, String value2) {
            addCriterion("process_cron between", value1, value2, "processCron");
            return (Criteria) this;
        }

        public Criteria andProcessCronNotBetween(String value1, String value2) {
            addCriterion("process_cron not between", value1, value2, "processCron");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationIsNull() {
            addCriterion("cron_calibration is null");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationIsNotNull() {
            addCriterion("cron_calibration is not null");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationEqualTo(Integer value) {
            addCriterion("cron_calibration =", value, "cronCalibration");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationNotEqualTo(Integer value) {
            addCriterion("cron_calibration <>", value, "cronCalibration");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationGreaterThan(Integer value) {
            addCriterion("cron_calibration >", value, "cronCalibration");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationGreaterThanOrEqualTo(Integer value) {
            addCriterion("cron_calibration >=", value, "cronCalibration");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationLessThan(Integer value) {
            addCriterion("cron_calibration <", value, "cronCalibration");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationLessThanOrEqualTo(Integer value) {
            addCriterion("cron_calibration <=", value, "cronCalibration");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationIn(List<Integer> values) {
            addCriterion("cron_calibration in", values, "cronCalibration");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationNotIn(List<Integer> values) {
            addCriterion("cron_calibration not in", values, "cronCalibration");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationBetween(Integer value1, Integer value2) {
            addCriterion("cron_calibration between", value1, value2, "cronCalibration");
            return (Criteria) this;
        }

        public Criteria andCronCalibrationNotBetween(Integer value1, Integer value2) {
            addCriterion("cron_calibration not between", value1, value2, "cronCalibration");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagIsNull() {
            addCriterion("every_day_start_flag is null");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagIsNotNull() {
            addCriterion("every_day_start_flag is not null");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagEqualTo(Integer value) {
            addCriterion("every_day_start_flag =", value, "everyDayStartFlag");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagNotEqualTo(Integer value) {
            addCriterion("every_day_start_flag <>", value, "everyDayStartFlag");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagGreaterThan(Integer value) {
            addCriterion("every_day_start_flag >", value, "everyDayStartFlag");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("every_day_start_flag >=", value, "everyDayStartFlag");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagLessThan(Integer value) {
            addCriterion("every_day_start_flag <", value, "everyDayStartFlag");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagLessThanOrEqualTo(Integer value) {
            addCriterion("every_day_start_flag <=", value, "everyDayStartFlag");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagIn(List<Integer> values) {
            addCriterion("every_day_start_flag in", values, "everyDayStartFlag");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagNotIn(List<Integer> values) {
            addCriterion("every_day_start_flag not in", values, "everyDayStartFlag");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagBetween(Integer value1, Integer value2) {
            addCriterion("every_day_start_flag between", value1, value2, "everyDayStartFlag");
            return (Criteria) this;
        }

        public Criteria andEveryDayStartFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("every_day_start_flag not between", value1, value2, "everyDayStartFlag");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxIsNull() {
            addCriterion("execute_max is null");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxIsNotNull() {
            addCriterion("execute_max is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxEqualTo(Integer value) {
            addCriterion("execute_max =", value, "executeMax");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxNotEqualTo(Integer value) {
            addCriterion("execute_max <>", value, "executeMax");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxGreaterThan(Integer value) {
            addCriterion("execute_max >", value, "executeMax");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("execute_max >=", value, "executeMax");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxLessThan(Integer value) {
            addCriterion("execute_max <", value, "executeMax");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxLessThanOrEqualTo(Integer value) {
            addCriterion("execute_max <=", value, "executeMax");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxIn(List<Integer> values) {
            addCriterion("execute_max in", values, "executeMax");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxNotIn(List<Integer> values) {
            addCriterion("execute_max not in", values, "executeMax");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxBetween(Integer value1, Integer value2) {
            addCriterion("execute_max between", value1, value2, "executeMax");
            return (Criteria) this;
        }

        public Criteria andExecuteMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("execute_max not between", value1, value2, "executeMax");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeIsNull() {
            addCriterion("process_limittime is null");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeIsNotNull() {
            addCriterion("process_limittime is not null");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeEqualTo(Integer value) {
            addCriterion("process_limittime =", value, "processLimittime");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeNotEqualTo(Integer value) {
            addCriterion("process_limittime <>", value, "processLimittime");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeGreaterThan(Integer value) {
            addCriterion("process_limittime >", value, "processLimittime");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("process_limittime >=", value, "processLimittime");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeLessThan(Integer value) {
            addCriterion("process_limittime <", value, "processLimittime");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeLessThanOrEqualTo(Integer value) {
            addCriterion("process_limittime <=", value, "processLimittime");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeIn(List<Integer> values) {
            addCriterion("process_limittime in", values, "processLimittime");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeNotIn(List<Integer> values) {
            addCriterion("process_limittime not in", values, "processLimittime");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeBetween(Integer value1, Integer value2) {
            addCriterion("process_limittime between", value1, value2, "processLimittime");
            return (Criteria) this;
        }

        public Criteria andProcessLimittimeNotBetween(Integer value1, Integer value2) {
            addCriterion("process_limittime not between", value1, value2, "processLimittime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        
        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}