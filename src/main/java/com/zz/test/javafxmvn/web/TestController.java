package com.zz.test.javafxmvn.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zz.test.javafxmvn.commonbean.MenuNode;
import com.zz.test.javafxmvn.commondb.CommonDb;
import com.zz.test.javafxmvn.main.service.MainService;



@RestController
@RequestMapping("/testa")
public class TestController {
	@Autowired
	private MainService ms;
	
	@Autowired
	private MenuNode menuNode;
	
	@Autowired
	private CommonDb db;
	
	@RequestMapping("/test1")
	private String test1(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("nihao,honey!");
		List<Map<String, Object>> list =  db.getList("main.getProcessListNew");
		MenuNode m = MenuNode.getMenu(list);
		
		return "hi!";
	}
	
	@RequestMapping("/test2")
	private String test2(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("nihao,honey!");
		return "hi!";
	}
	@RequestMapping("/test3")
	private String test3(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("nihao,honey!");
		return "hi!";
	}
}
