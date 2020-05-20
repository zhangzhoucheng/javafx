package com.zz.test.javafxmvn.commontool.pytool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.util.StringUtil;
import com.mysql.jdbc.StringUtils;
import com.zz.test.javafaxmvn.config.PyConfig;
import com.zz.test.javafxmvn.commonbean.BaseObjectViewOth;
import com.zz.test.javafxmvn.commontool.MapDataStrcture;
import com.zz.test.javafxmvn.commontool.RegexpTool;

/**
 * 
 * <note>
 * Desc： 在StartPy启动有问题。导致py中的定时任务不能正常执行。所以构建了该StartMain类
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-05-15 19:38:09
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-05-15 19:38:09    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Component
public class StartMain extends BaseObjectViewOth{
	@Autowired
	private PyConfig pyConfig;
	
	/**
	 * Desc:startPy
	 * @author jld.zhangzhou
	 * @datetime 2020-05-20 21:01:46
	 * @modify_record:
	 * @param args
	 * @return python.exe pid
	 */
	public  String startPy(String[] args) {
		
		String pid = "";
		try {
			//通过启动jar包，之后jar中的main再去启动python
			
			//String pyCommand = "java -jar E:\\workspace\\linshi\\workspace\\bbc-4\\z3_javafx_new_maven\\src\\main\\resources\\templates\\pyjar\\pyStartJar.jar";
			//拼接py启动命令和参数到pyCommand
			String pyCommand = pyConfig.getJar_url();
			for(String s : args) {
				pyCommand = pyCommand + " " + s;
			}
			
			Process pro = null;
			String encode = "UTF-8";
			if("windows".equals(args[0])) {
				encode = "GBK";
				pro = Runtime.getRuntime().exec(new String[] { "cmd", "/c", pyCommand });//启动jar
				
			}if("linux".equals(args[0])) {
				pro = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", pyCommand });
				
			}
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream(), encode));
            String line = null;
	        while ((line = in.readLine()) != null) {  
	            System.out.println(line); 
	            System.out.println(line);  
	            if(StringUtils.startsWithIgnoreCase(line, "@@return pid:")) {
	            	pid = line.substring("@@return pid:".length());
	            }
	        }  
	        in.close();
	        //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
	        int re = pro.waitFor();  
	        if(re == 0) {
	        	logger.warn("@@StartMain success command: '" + pyCommand + "' ");
	        }else if(re == 1) {
	        	logger.warn("@@StartMain fail command: '" + pyCommand + "' ");
	        }else if(re == 3) {
	        	logger.warn("@@StartMain force end,kill sub java.exe command: '" + pyCommand + "' ");
	        }
	        logger.warn("@@StartMain end, command: '" + pyCommand + "' ");
	        
	        return pid;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return pid;

	}
	
	public Map<String, Boolean> getPid(String platform, String exeName) {
		try {
			Process pro = null;
			String encode = "UTF-8";
			if ("windows".equals(platform)) {
				encode = "GBK";
				System.out.println("e1");
				pro = Runtime.getRuntime().exec(new String[] { "cmd", "/c", "tasklist | findstr " + exeName });// 查询进程
				System.out.println("e2");
				BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream(),encode));
	            String line = null;
	            Map<String, Boolean> map = new HashMap<>();
		        while ((line = in.readLine()) != null) {  
		            System.out.println(line);
		            //line = RegexpTool.replaceByPattern(line, ",", "[ ]+");
		            String [] pids = line.split("[ ]+");
		            map.put(pids[1], true);
		            
		        }  
		        in.close();
		        
		        return map;

			}
			if ("linux".equals(platform)) {
				pro = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", "tasklist | findstr" + exeName });

			}
			
			
		} catch (Exception e) {

		}
		return null;
		
	}
	
	
	public  String startPyTest(String[] args) {

		try {
			//通过启动jar包，之后jar中的main再去启动python
			
			//拼接py启动命令和参数到pyCommand
			//String pyCommand = "java -jar E:\\workspace\\linshi\\workspace\\bbc-4\\z3_javafx_new_maven\\src\\main\\resources\\templates\\pyjar\\pyStartJar.jar";
			String pyCommand = "java -jar E:/workspace/linshi/workspace/bbc-4/z3_javafx_new_maven/src/main/resources/templates/pyjar/pyStartJar.jar";
			//String pyCommand = pyConfig.getJar_url();
			
			for(String s : args) {
				pyCommand = pyCommand + " " + s;
			}
			
			Process pro = null;
			String encode = "UTF-8";
			if("windows".equals(args[0])) {
				encode = "GBK";
				pro = Runtime.getRuntime().exec(new String[] { "cmd", "/c", pyCommand });//启动jar
				
			}if("linux".equals(args[0])) {
				pro = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", pyCommand });
				
			}
			
			String pid = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream(), encode));
            String line = null;
	        while ((line = in.readLine()) != null) {  
	            System.out.println(line);  
	            if(StringUtils.startsWithIgnoreCase(line, "@@return pid:")) {
	            	pid = line.substring("@@return pid:".length());
	            }
	        }  
	        in.close();
	        //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
	        int re = pro.waitFor();  
	        if(re == 0) {
	        	logger.warn("@@StartMain success command: '" + pyCommand + "' ");
	        }else if(re == 1) {
	        	logger.warn("@@StartMain fail command: '" + pyCommand + "' ");
	        }else if(re == 3) {
	        	logger.warn("@@StartMain force end,kill sub java.exe command: '" + pyCommand + "' ");
	        }
	        logger.warn("@@StartMain end, command: '" + pyCommand + "' ");
	        return pid;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		/*try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return "";
	}

	@Test
	public void test() {
		String []args =new String[] {"windows","H:\\softwarenew\\softwareinstall\\Anaconda3\\envs\\py_for_mony\\python.exe H:/softwarenew/workspace/python_wp/python_money/py_task_disptch/PyStartMain.py --pack_root_path=H:/softwarenew/workspace/python_wp/python_money"};
		StartMain st = new StartMain();
		System.out.println("pid:"+st.startPyTest(args));
		
	}
	
	//@Test
	public void test1() {
		Map<String, Boolean>  map = this.getPid("windows", "et");
		System.out.println(1);
	}
	
	
}
