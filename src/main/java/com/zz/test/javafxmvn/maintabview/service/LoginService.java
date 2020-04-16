package com.zz.test.javafxmvn.maintabview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.common.entity.PyProcessExample;
import com.zz.test.javafxmvn.common.entity.mapper.PyProcessMapper;
import com.zz.test.javafxmvn.commondb.CommonDb;

@Service
public class LoginService {

	@Autowired
	private CommonDb db;
	
	@Autowired
	private PyProcessMapper pyProcessMapper;
	
	public void loginSerch() {
		// TODO Auto-generated method stub
		PyProcessExample pyProExa = new PyProcessExample();
		List<PyProcess> pyProList = (List<PyProcess>) pyProExa.createCriteria().andTypeCodeEqualTo("login");
		System.out.println(1);
	}

}
