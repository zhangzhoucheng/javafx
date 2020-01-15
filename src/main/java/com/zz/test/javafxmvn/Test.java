package com.zz.test.javafxmvn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class Test {
	@RequestMapping("/test1")
	public void test1() {
		System.out.println(1234567);
	}
}
