package com.zz.test.javafxmvn.maintabview.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.common.entity.PyProcessExample;
import com.zz.test.javafxmvn.common.entity.PyProcessExample.Criteria;
import com.zz.test.javafxmvn.common.entity.mapper.PyProcessMapper;
import com.zz.test.javafxmvn.commonbean.PageCommonRequest;
import com.zz.test.javafxmvn.commonbean.PageCommonResult;
import com.zz.test.javafxmvn.commondb.CommonDb;
import com.zz.test.javafxmvn.commontool.pytool.StartPy;
import com.zz.test.javafxmvn.maintabview.view.LoginFxmlView;

@Service
public class StartPyMainService {

	@Autowired
	private CommonDb db;
	
	@Autowired
	private PyProcessMapper pyProcessMapper;
	
	private LoginFxmlView loginFxmlView= new LoginFxmlView();
	
	public List<PyProcess> loginSerch(String type_code, String process_code) {
		// TODO Auto-generated method stub
		PyProcessExample pyProExa = new PyProcessExample();
		Criteria c = pyProExa.createCriteria();
		if(StringUtils.isNotBlank(type_code)) {
			c.andTypeCodeEqualTo(type_code);
		}
		if(StringUtils.isNotBlank(process_code)) {
			c.andProcessCodeEqualTo(process_code);
		}
		c.andDisableNotEqualTo(2);
		List<PyProcess> pyProList = pyProcessMapper.selectByExample(pyProExa);
		return pyProList;
	}

	public PageCommonResult loginSerchnew(PageCommonRequest request) {
		PyProcessExample pyProExa = new PyProcessExample();
		request.setMapper_example(pyProExa);
		Criteria c = pyProExa.createCriteria();
		if(StringUtils.isNotBlank((String) request.getValue("type_code"))) {
			c.andTypeCodeEqualTo((String) request.getValue("type_code"));
		}
		if(StringUtils.isNotBlank((String) request.getValue("process_code"))) {
			c.andProcessCodeEqualTo((String) request.getValue("process_code"));
		}
		c.andDisableNotEqualTo(2);
		
		return (PageCommonResult) db.getListExample(pyProcessMapper.namespace + ".selectByExample", request);
				
	}
	
	public int updatePyProcessList(PyProcess py) {
		return pyProcessMapper.updateByPrimaryKey(py);
	}
	
	public int insertPyProcess(PyProcess py) {
		return pyProcessMapper.insertSelective(py);
	}


	public int deleteByPrimaryKeySet2(PyProcess p) {
		PyProcessExample pyProExa = new PyProcessExample();
		
		Criteria c = pyProExa.createCriteria();
		p.setDisable(2);
		return pyProcessMapper.updateByPrimaryKey(p);
	}
	
	
	public void startPyFile(String key) {
		if("1".equals(key)) {
			StartPy.startByInterpreter(new String[]{"a =123123","print\\(a\\)"});
		}
		if("2".equals(key)) {
			StartPy.startByPy("D:\\demo.py");
		}
		if("3".equals(key)) {
			StartPy.startByRuntime("python  D:\\demo.py");
		}
		if("123".equals(key)) {
			StartPy.startByInterpreter(new String[]{"a =123123","print\\(a\\)"});
			StartPy.startByPy("D:\\demo.py");
			StartPy.startByRuntime("python  D:\\demo.py");
		}
		
	}
}
