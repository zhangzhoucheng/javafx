package com.zz.test.javafxmvn.maintabview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.common.entity.PyProcessExample;
import com.zz.test.javafxmvn.common.entity.mapper.PyProcessMapper;
import com.zz.test.javafxmvn.commondb.CommonDb;
import com.zz.test.javafxmvn.maintabview.view.LoginFxmlView;

@Service
public class LoginService {

	@Autowired
	private CommonDb db;
	
	@Autowired
	private PyProcessMapper pyProcessMapper;
	
	private LoginFxmlView loginFxmlView= new LoginFxmlView();
	
	public List<PyProcess> loginSerch() {
		// TODO Auto-generated method stub
		PyProcessExample pyProExa = new PyProcessExample();
		pyProExa.createCriteria().andTypeCodeEqualTo("login");
		List<PyProcess> pyProList = pyProcessMapper.selectByExample(pyProExa);
		return pyProList;
	}

	
	public void updatePyProcessList(PyProcess py) {
		pyProcessMapper.updateByPrimaryKey(py);
	}
	
	public void insertPyProcess(PyProcess py) {
		pyProcessMapper.insertSelective(py);
	}
}
