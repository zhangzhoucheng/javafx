package com.zz.test.javafxmvn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zz.test.javafxmvn.main.service.MainService;



@RestController
@RequestMapping("/testa")
public class TestController {
	@Autowired
	private MainService ms;
	
	@RequestMapping("/test1")
	private String test1(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("nihao,honey!");
		ms.handlerBtnClick();
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
