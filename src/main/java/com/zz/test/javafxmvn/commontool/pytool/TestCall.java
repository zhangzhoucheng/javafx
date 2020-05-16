package com.zz.test.javafxmvn.commontool.pytool;

import java.io.IOException;

import org.python.modules.synchronize;

public class TestCall {

public  static void main(String[] args) {
	String pyCommand ="H:\\softwarenew\\softwareinstall\\Anaconda3\\envs\\py_for_mony\\python.exe H:/softwarenew/workspace/python_wp/python_money/py_task_disptch/ScheduleProcessManage.py --pack_root_path=H:/softwarenew/workspace/python_wp/python_money";
	 try {
		 long id =Thread.currentThread().getId();
		 System.out.println("mainid:"+id);
		 Runtime run = Runtime.getRuntime();
		 
		  Process proc= run.exec(new String[]{ "cmd", "/c", pyCommand});

		System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName());
		proc.notify();
	
		System.out.println(proc.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

synchronize  test() {
	return null;
	
}
}
