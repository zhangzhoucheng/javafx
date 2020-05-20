package com.zz.test.javafxmvn.commontool.pytool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.junit.Test;
import org.python.modules.thread.thread;
import org.python.util.PythonInterpreter;

public class StartPyDemo {
	
//解决调用python出现的Cannot import site module and its dependencies: No module named site问题
	static void init() {
		Properties props = new Properties();
		props.put("python.home", "path to the Lib folder");
		props.put("python.console.encoding", "UTF-8");
		props.put("python.security.respectJavaAccessibility", "false");
		props.put("python.import.site", "false");
		Properties preprops = System.getProperties();
		PythonInterpreter.initialize(preprops, props, new String[0]);
	}
	
	
	//直接执行Python脚本代码
	//引用 org.python包	
	/**
	 * Desc:传入py代码行execLine，执行代码
	 * @author jld.zhangzhou
	 * @datetime 2020-05-09 21:19:44
	 * @modify_record:
	 * @param execLine
	 * @param arg
	 */
	public static void startByInterpreter(String [] execLine, String... arg) {
		init();
		
		PythonInterpreter interpreter = new PythonInterpreter();  
		//interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");   ///执行python脚本
		
		//测试
		//interpreter.exec("a='hello world'; ");
        //interpreter.exec("print a;");
		
		for(String str : execLine) {
			interpreter.exec(str);
		}

	}


	//2.  执行python .py文件
	/**
	 * Desc:执行py文件
	 * @author jld.zhangzhou
	 * @datetime 2020-05-09 21:20:42
	 * @modify_record:
	 * @param pyFile
	 */
	public static void startByPy(String pyFile){
		init();

		PythonInterpreter interpreter = new PythonInterpreter();  
		InputStream filepy = null;
		try {
			filepy = new FileInputStream(pyFile);
			interpreter.execfile(filepy);  ///执行python py文件

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				filepy.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}



	public static void  startByRuntime(String pyCommand) {
		//startMain.startPy(new String[] {pyCommand});
		
	}
	
	
	//3.  使用Runtime.getRuntime()执行脚本文件
		//这种方式和.net下面调用cmd执行命令的方式类似。如果执行的python脚本有引用第三方包的，建议使用此种方式。使用上面两种方式会报错java ImportError: No module named arcpy。
		/**
		 * Desc:执行python脚本，通过commnod，（推荐方式）
		 * @author jld.zhangzhou
		 * @datetime 2020-05-10 15:14:59
		 * @modify_record:
		 * @param pyCommand
		 */
	public  void startByRuntime1() {
		Process proc;
        try {
        		String [] commands = new String[] {"dir"};
        		String [] args = new String[] {"bb=123","aa=5"};
    		 proc = Runtime.getRuntime().exec(new String[]{ "cmd", "/c","python d:/demo.py --a=1 --b=2"});
             BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
             
             String line = null;
             while ((line = in.readLine()) != null) {
                 System.out.println(line);
             }
             in.close();
             proc.waitFor();
        
           
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } 
		
	}

}
