package com.zz.test.javafxmvn.commontool.pytool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.python.modules.synchronize;

public class TestCall {

public  static void main(String[] args) {
	

		 long id =Thread.currentThread().getId();
		 System.out.println("mainid:"+id);
		
	
			 Runtime run = Runtime.getRuntime();RuntimePermission t = null;
			 String pyCommand ="H:\\softwarenew\\softwareinstall\\Anaconda3\\envs\\py_for_mony\\python.exe H:/softwarenew/workspace/python_wp/python_money/py_task_disptch/PyStartMain.py --pack_root_path=H:/softwarenew/workspace/python_wp/python_money";
			 
			 
			  Process process = null;
			try {
				process = run.exec(new String[]{ "cmd", "/c", pyCommand});//这种方式无法成功，由于python属于子进程，在当前总应用主进程没执行完，导致python里面定时任务出现bug，当然python普通脚本没问题。只是无法开启定时任务。这里很头疼，解决了很久。后来采用启动jar包，再去调用python
				
				BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
				BufferedReader ine = new BufferedReader(new InputStreamReader(process.getErrorStream(),"GBK"));
	            String line = null;
		        while ((line = in.readLine()) != null) {  
		            System.out.println("in:"+line);  
		        }  
		        while ((line = ine.readLine()) != null) {  
		            System.out.println("ine:"+line);  
		        }  
		        in.close();
		        ine.close();
		        //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
	            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
		        int re = process.waitFor();  
		        System.out.println(re);
		        

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			  System.out.println(process.toString());
		

		System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName());
		//proc.notify();
		
		System.out.println(1);
		try {
			System.out.println("沉睡中!!!");
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

}

synchronize  test() {
	return null;
	
}
}
