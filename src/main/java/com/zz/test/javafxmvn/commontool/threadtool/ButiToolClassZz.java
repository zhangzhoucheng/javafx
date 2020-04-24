package com.zz.test.javafxmvn.commontool.threadtool;



import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zz.test.javafxmvn.common.entity.PyProcess;
import com.zz.test.javafxmvn.commonbean.CommonRequest;
import com.zz.test.javafxmvn.commonbean.CommonResult;
import com.zz.test.javafxmvn.commonbean.PageCommonResult;
import com.zz.test.javafxmvn.commontool.RegexpTool;

import javafx.scene.control.TextField;




/**
 * 
 * <note>
 * Desc： 关于logic，vary，等便捷操作
 * @author jld.zhangzhou
 * @refactor for jld
 * @datetime 2019-08-15 10:44:00
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2019-08-15 10:44:00    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
 public class ButiToolClassZz {
	 private static final Logger logger = LoggerFactory.getLogger(ButiToolClassZz.class);

	@SuppressWarnings("unused")
	private static void getPageDataBySeveralList(List<List<Map<String, Object>>> serveralListMap,int pageNo,int pageSize) {
		List<Integer> eachListSize = new ArrayList<>();
		for(List<Map<String, Object>> list : serveralListMap) {
			eachListSize.add(list.size());
			for(Map<String, Object> map : list) {
				
			}
		}
	}
	
	/**
	 * Desc:通过sql数据分线程获取数据，pageFlag进行分页开启。当然分页操作可以更优化，list不用直接放入内存，可以用mysql limit获取（当然这需要计算serval list对应的limit，可以设计algorithm，由于任务多，并未实现，待续。。。)
	 *      所以这就降低了创造力。
	 * @author jld.zhangzhou
	 * @datetime 2019-08-19 10:43:00
	 * @modify_record:
	 * @param commonRequest
	 * @param querySql
	 * @param pageFlag
	 * @return
	 * @throws InterruptedException
	 */
	public static CommonResult queryTicketDataErrAlg(CommonRequest commonRequest, String[] querySql,int pageFlag) throws InterruptedException {
		Map<String, Object> params = commonRequest.getParams();
		List<Map<String, Object>> list = ThreadPollTool.executorBatchSqlToGetServalList(querySql, commonRequest);
		//分页
		if(pageFlag == 1) {
			PageCommonResult pageCom = new PageCommonResult();
			pageCom.setPageNo(params.get("pageNo")!=null?(int) params.get("pageNo"):1);
			pageCom.setPageSize(params.get("pageSize")!=null?(int) params.get("pageSize"):10);
			pageCom.setTotalRecords(list.size());
			pageCom.setBody(list.subList((pageCom.getPageNo()-1)*pageCom.getPageSize(), (int) (pageCom.getPageNo()*pageCom.getPageSize()<=pageCom.getTotalRecords()?pageCom.getPageNo()*pageCom.getPageSize():
				pageCom.getTotalRecords())));
			return pageCom;
		}
		//不分页
		if(pageFlag == 0) {
			return new CommonResult(list);
		}
		return new CommonResult(new ArrayList<>());
	}
	
	/**
	 * Desc:获取 yyyymm-yyyymm的集合，’-‘左右间隔两个月，
	 * @author jld.zhangzhou
	 * @datetime 2019-12-11 16:39:31
	 * @modify_record:
	 * @param monthRange 集合size， 若<=0 则monthRange=18
	 * @param date 日期
	 * @param interval 间隔，若<0 则interval=2
	 * @param datepattern 显示时间格式，若空，则 datepattern=‘yyyyMM’
	 * @return
	 */
	public static List<String> queryYearRodMonthByNowTest(int monthRange, Date date, int interval, String datepattern) {
		// TODO Auto-generated method stub
		if (monthRange <= 0) {
			monthRange = 18;
		}
		if(interval < 0) {
			interval = 2;
		}
		if(StringUtils.isBlank(datepattern)) {
			datepattern = "yyyyMM";
		}
		SimpleDateFormat sf = new SimpleDateFormat(datepattern);
		List<String> yearRodMonthList = new ArrayList<>();
		for(int i = 0 ; i < monthRange ; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, -(i+1));
			String str = sf.format(cal.getTime());
			cal.add(Calendar.MONTH, -interval);
			str = sf.format(cal.getTime()) + "-" + str ;
			yearRodMonthList.add(str);
		}
		return yearRodMonthList;
	}
	
	/**
	 * Desc:异或加密解密,初级，有中文乱码问题
	 * @author jld.zhangzhou
	 * @datetime 2019-12-12 14:28:59
	 * @modify_record:
	 * @param value
	 * @param sercet
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String myjiaSecretTool(String value, char sercet) throws UnsupportedEncodingException {
		byte[] bt = value.getBytes("UTF-8");
		for(int i = 0 ; i < bt.length ; i++) {
			bt[i]=(byte)(bt[i]^(int)sercet);
		}
		return new String(bt, 0, bt.length);
	}
	
	/**
	 * 
	 * <note>
	 * Desc：aes加密,解密类 
	 * @author jld.zhangzhou
	 * @email idiot_jillidan@163.com;
	 * @re be willing to communicate
	 * @refactor for jld
	 * @datetime 2019-12-12 14:43:14
	 * @location mobile base 3th,BeiJing 
	 * version  1.0
	 *  
	 * @REVISIONS: 
	 * Version 	        Date 		         Author             Location                   Description          
	 * ------------------------------------------------------------------------------------------------------  
	 * 1.0 		  2019-12-12 14:43:14    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
	 * </note>
	 */
	public static class AESUtil{
		
		/**
		 * Desc:相同content，key 会生成不同 码，
		 * @author jld.zhangzhou
		 * @datetime 2019-12-12 15:49:23
		 * @modify_record:
		 * @param content
		 * @param password
		 * @return
		 */
		public static byte[] encrypt(String content, String password) {
	        try {
	            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
	            kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
	            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
	            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
	            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
	            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器AES  或者：AES/"+EncryptMode+"/PKCS5Padding
	            byte[] byteContent = content.getBytes("utf-8");
	            
	            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
	            byte[] result = cipher.doFinal(byteContent);// 加密
	            return result;
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

		public static  byte[] decrypt(byte[] content, String password) {
	        try {
	            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
	            kgen.init(128, new SecureRandom(password.getBytes()));
	            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
	            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
	            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
	            byte[] result = cipher.doFinal(content);  
	            return result; // 明文   
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
		
		/**
		 * Desc:指定 EncryptMode='ECB'模式，可以相同content，key 会生成相同码记为：@ECB-C，
		 * @author jld.zhangzhou
		 * @datetime 2019-12-12 15:49:58
		 * @modify_record:
		 * @param plaintext
		 * @param Key
		 * @param EncryptMode 模式‘ECB','CBC','CFB'等
		 * @return
		 */
		 public static String AES_Encrypt(Object plaintext, String Key,String EncryptMode) {
			 	if(StringUtils.isBlank((String) plaintext)) {
			 		return "";
			 	}
		        String PlainText=null;
		        try {
		            PlainText=plaintext.toString();
		            if (Key == null) {
		                return null;
		            }
		            Key = getMD5(Key);
		            byte[] raw = Key.getBytes("utf-8");
		            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		            Cipher cipher = Cipher.getInstance("AES/"+EncryptMode+"/PKCS5Padding");
		            if(EncryptMode=="ECB") {
		                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		            }else {
		                IvParameterSpec iv = new IvParameterSpec(Key.getBytes("utf-8"));//使用CBC模式，需要一个向量iv，可增加加密算法的强度
		                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		            }
		            byte[] encrypted = cipher.doFinal(PlainText.getBytes("utf-8"));
		            String encryptedStr=new String(Base64.encodeBase64(encrypted));
		            return encryptedStr;
		            //return new String(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		            return null;
		        }
		    }

		 public static String AES_Decrypt(Object cipertext, String Key,String EncryptMode) {
			 	if(StringUtils.isBlank((String) cipertext)) {
			 		return "";
			 	}
		        String CipherText=null;
		        try {
		            CipherText=cipertext.toString();
		            // 判断Key是否正确
		            if (Key == null) {
		                //System.out.print("Key为空null");
		                return null;
		            }
		            Key=getMD5(Key);
		            byte[] raw = Key.getBytes("utf-8");
		            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		            Cipher cipher=Cipher.getInstance("AES/"+EncryptMode+"/PKCS5Padding");
		            if(EncryptMode=="ECB") {
		                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		            }
		            else
		            {
		                IvParameterSpec iv = new IvParameterSpec(Key.getBytes("utf-8"));//使用CBC模式，需要一个向量iv，可增加加密算法的强度
		                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		            }
		            byte[] encrypted1 = Base64.decodeBase64(CipherText);//先用base64解密，@ECB-C 模式需要为16位，才行
		            //byte[] encrypted1 = CipherText.getBytes();
		            try {
		                byte[] original = cipher.doFinal(encrypted1);
		                String originalString = new String(original,"utf-8");
		                return originalString;
		            } catch (Exception e) {
		            	e.printStackTrace();
		                return null;
		            }
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		            return null;
		        }
		    }

		
		public static String getMD5(String s){
	        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	 
	        try {
	            byte[] btInput = s.getBytes();
	            // 获得MD5摘要算法的 MessageDigest 对象
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            // 使用指定的字节更新摘要
	            mdInst.update(btInput);
	            // 获得密文
	            byte[] md = mdInst.digest();
	            // 把密文转换成十六进制的字符串形式
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(str).substring(8,24);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
		
	}
	
	/**
	 * Desc:通过位置参数locals，替换这些位置的字符串为 sub
	 * @author jld.zhangzhou
	 * @datetime 2019-12-12 16:53:22
	 * @modify_record:
	 * @param val
	 * @param locals
	 * @param sub
	 * @return
	 */
	public static String replaceLocalStrBySub(String val, int [] locals, char sub) {
		if(StringUtils.isBlank(val)) {
			return "";
		}
		if(locals.length < 1) {
			return val;
		}
		char [] valChars = val.toCharArray();
		for(int local : locals) {
			if(valChars.length <= local) {
				continue;
			}
			valChars[local] = sub;
		}
		return String.valueOf(valChars);
	}
	
	
	
	/**
	 * Desc:clone一个 commonRequest，防止多线程先公用一个commonRequest 出现问题。
	 * 当然当commonRequest.getParams()的map中有其它对象引用，也会在commonRequestNew 中指向同一个引用
	 * @author jld.zhangzhou
	 * @datetime 2020-03-19 15:42:27
	 * @modify_record:
	 * @param commonRequest
	 * @return
	 */
	public static CommonRequest cloneCommonRequest(CommonRequest commonRequest) {
		CommonRequest commonRequestNew = new CommonRequest();
		commonRequestNew.setOrder(commonRequest.getOrder());
		commonRequestNew.setOrderBy(commonRequest.getOrderBy());
		commonRequestNew.setTraceId(commonRequest.getTraceId());
		
		/*Iterator<Entry<String, Object>> i = commonRequest.getParams().entrySet().iterator();//可以删除的map遍历
		while(i.hasNext()) {
			Entry<String, Object> en =i.next();
			en.getKey();en.getValue()
		}*/
		
		for(Entry<String, Object> entry : commonRequest.getParams().entrySet()) {
			commonRequestNew.addParameter(entry.getKey(), entry.getValue());
		}
		return commonRequestNew;
	}
	
	
	/**
	 * 
	 * <note>
	 * Desc： 反射操作相关的类
	 * @author jld.zhangzhou
	 * @email idiot_jillidan@163.com;
	 * @re be willing to communicate
	 * @refactor for jld
	 * @datetime 2020-04-09 09:49:13
	 * @location mobile base 3th,BeiJing 
	 * version  1.0
	 *  
	 * @REVISIONS: 
	 * Version 	        Date 		         Author             Location                   Description          
	 * ------------------------------------------------------------------------------------------------------  
	 * 1.0 		  2020-04-09 09:49:13    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
	 * </note>
	 */
	public static class ReflexRel<T>{
		
		/**
		 * Desc:通过传入参数执行对应方法
		 * @author jld.zhangzhou
		 * @datetime 2020-04-09 09:49:52
		 * @modify_record:
		 * @param methodName
		 * @param beanName
		 * @param commonRequest
		 * @return
		 * @throws InstantiationException
		 * @throws IllegalAccessException
		 * @throws ClassNotFoundException
		 * @throws NoSuchMethodException
		 * @throws SecurityException
		 * @throws IllegalArgumentException
		 * @throws InvocationTargetException
		 */
		public static Object reflexMethod(String methodName, String beanName, CommonRequest commonRequest) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
			//Object theClass=Class.forName(tableList.classMethod).newInstance();//获取class实例，即获取service层的对应class
			Object ob=SpringUtils.getBean(beanName);
			Method method=ob.getClass().getMethod(methodName,commonRequest.getClass());
			return method.invoke(ob, commonRequest);
		}
		
		/**
		 * Desc:通过传入参数执行对应方法
		 * @author jld.zhangzhou
		 * @datetime 2020-04-09 09:50:49
		 * @modify_record:
		 * @param methodName
		 * @param beanName
		 * @param map
		 * @return
		 * @throws InstantiationException
		 * @throws IllegalAccessException
		 * @throws ClassNotFoundException
		 * @throws NoSuchMethodException
		 * @throws SecurityException
		 * @throws IllegalArgumentException
		 * @throws InvocationTargetException
		 */
		public static Object reflexMethodMap(String methodName, String beanName, Map<String, Object> map) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
			//Object theClass=Class.forName(tableList.classMethod).newInstance();//获取class实例，即获取service层的对应class
			Object ob=SpringUtils.getBean(beanName);
			Method method=ob.getClass().getMethod(methodName,map.getClass());
			return method.invoke(ob, map);
		}
		
		/**
		 * Desc:给对象某个属性设置值
		 * @author jld.zhangzhou
		 * @datetime 2020-04-22 21:55:00
		 * @modify_record:
		 * @param o
		 * @param field
		 * @param val
		 * @throws NoSuchFieldException
		 * @throws SecurityException
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 */
		public static String reflexObjectSetFieldVal(Object o, String field, Object val) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
			//Field theField = o.getClass().getDeclaredField(field);
			//theField.setAccessible(true);//设置可见
			//theField.set(o, val);
			
			try {
				Field theField = o.getClass().getDeclaredField(field);
				theField.setAccessible(true);//设置可见
				
				if(theField.getType().isInstance(val)) {
					theField.set(o, val);
					return "1";
				}
				Object fieldObj = reflexTypeParseFromValToField((String)val, theField);
				if(fieldObj == null) {
					return String.format("字段[%s],类型必须是:%s", field,theField.getType().getName());
				}
				theField.set(o, reflexTypeParseFromValToField((String)val, theField));
				/*if(e.getValue() instanceof (theField.getGenericType())) {
					theField.getType();
				}*/
			} catch(Exception ee) {
				ee.printStackTrace();
				logger.error(String.format("@@reflexObjectSetFieldVal,error!"),ee);
				return String.format("@@reflexObjectSetFieldVal error");
			}
			
			return "1";
		}
		
		/**
		 * Desc:参数化类型判断，暂时没应用实例
		 * @author jld.zhangzhou
		 * @datetime 2020-04-23 15:05:34
		 * @modify_record:
		 * @param f
		 * @return
		 */
		public static Class<?>[] getParameterizedType(Field f) {
			// 获取f字段的通用类型
			Type fc = f.getGenericType(); // 关键的地方得到其Generic的类型


			// 如果不为空并且是泛型参数的类型
			if (fc != null && fc instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) fc;


			Type[] types = pt.getActualTypeArguments();


			if (types != null && types.length > 0) {
			Class<?>[] classes = new Class<?>[types.length];
			for (int i = 0; i < classes.length; i++) {
			classes[i] = (Class<?>) types[i];
			}
			return classes;
			}
			}
			return null;
			}
		@Test
		public void test1() throws NoSuchFieldException, SecurityException {
			PyProcess p = new PyProcess();
			Field f1 =p.getClass().getDeclaredField("disable");
			Object t=this.getParameterizedType(f1);
		}
		
		/**
		 * Desc:给对象某多个属性设置值
		 * @author jld.zhangzhou
		 * @datetime 2020-04-23 13:41:40
		 * @modify_record:
		 * @param o
		 * @param map
		 * @throws NoSuchFieldException
		 * @throws SecurityException
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 * @throws InvocationTargetException 
		 * @throws NoSuchMethodException 
		 * @throws ClassNotFoundException 
		 */
		public static String reflexObjectSetFieldVal(Object o, Map<String, Object> map) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
			
			for(Entry<String, Object> e : map.entrySet()) {
				String msg = reflexObjectSetFieldVal(o, e.getKey(), e.getValue());
				if("1".equals(msg)) {
					continue;
				}
				return msg;
				/*try {
					Field theField = o.getClass().getDeclaredField(e.getKey());
					theField.setAccessible(true);//设置可见
					
					if(theField.getType().isInstance(e.getValue())) {
						theField.set(o, e.getValue());
						continue;
					}
					Object fieldObj = reflexTypeParseFromValToField((String)e.getValue(), theField);
					if(fieldObj == null) {
						return String.format("字段[%s],类型必须是:%s", e.getKey(),theField.getType().getName());
					}
					theField.set(o, reflexTypeParseFromValToField((String)e.getValue(), theField));
					if(e.getValue() instanceof (theField.getGenericType())) {
						theField.getType();
					}
				} catch(Exception ee) {
					ee.printStackTrace();
					logger.error(String.format("@@reflexObjectSetFieldVal,error!"),ee);
					return String.format("@@reflexObjectSetFieldVal error");
				}*/
				
				
			}
			
			return "1";
			
		}
		
		/**
		 * Desc:给对象某多个属性设置值,并且提示fieldsHead对应的中文
		 * @author jld.zhangzhou
		 * @datetime 2020-04-23 16:49:56
		 * @modify_record:
		 * @param o
		 * @param map
		 * @param fieldsHead
		 * @return
		 * @throws IllegalAccessException 
		 * @throws IllegalArgumentException 
		 * @throws SecurityException 
		 * @throws NoSuchFieldException 
		 */
		public static String reflexObjectSetValByField2fieldsHead(Object o, Map<String, Object> map, final String[] fieldsHead) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
			String msg = reflexObjectSetFieldVal(o, map);
			String msgsub = (String) RegexpTool.getContent4LR(msg, "\\[", "\\]");
			for(String f : fieldsHead) {
				String c_ = (String) RegexpTool.getContent4LR(f, "_\\$c_", "_");
				if(c_.equals(msgsub)) {
					String name_ = (String) RegexpTool.getContent4LR(f, "^", "_");
					msg = msg.replace(String.format("[%s]", msgsub), "[" + name_ + "]");
				}
	
			}
			return msg;
		}
		
		/**
		 * Desc:把String val 值转换成field对应的类型。
		 * @author jld.zhangzhou
		 * @datetime 2020-04-23 15:29:06
		 * @modify_record:
		 * @param val
		 * @param field
		 * @return
		 * @throws ClassNotFoundException
		 * @throws NoSuchMethodException
		 * @throws SecurityException
		 * @throws IllegalAccessException
		 * @throws IllegalArgumentException
		 * @throws InvocationTargetException
		 */
		public static Object reflexTypeParseFromValToField(String val, Field field) {
			//field为目标，value为投进来的值
			String ftype = field.getType().getName(); //field为反射出来的字段类型
			String fstype = field.getType().getSimpleName();
			if (field.getType() == String.class)
				return val.toString();
			else if (ftype.indexOf("java.lang.") == 0) {
				
				try {
					// java.lang下面类型通用转换函数
					Class<?> class1 = Class.forName(ftype);
					Method method = class1.getMethod("parse" + fixparse(fstype),String.class);
					if (method != null) {
						Object ret = method.invoke(null, val.toString());
						if (ret != null)
							return ret;
					}
				} catch(Exception e) {
					//e.printStackTrace();
					logger.warn(String.format("@@reflexTypeParseFromValToField,val=%s,field=%s error!", val,field.toString()));
					return null;
				}
				
			}
			return null;

			
		}
		private static String fixparse(String fstype) {
			switch (fstype) {
			case "Integer":
				return "Int";
			default:
				return fstype;
			}
		}
		
		/**
		 * Desc:transform String to T
		 * @author jld.zhangzhou
		 * @datetime 2020-04-23 22:19:30
		 * @modify_record:
		 * @param val
		 * @param field
		 * @return
		 */
		public static<T> Object reflexTypeParseFromValToObject(String val, Object field) {
			//field为目标，value为投进来的值
			String ftype = field.getClass().getName(); //field为反射出来的字段类型
			String fstype = field.getClass().getSimpleName();
			if (field.getClass() == String.class)
				return val.toString();
			else if (ftype.indexOf("java.lang.") == 0) {
				
				try {
					// java.lang下面类型通用转换函数
					Class<?> class1 = Class.forName(ftype);
					Method method = class1.getMethod("parse" + fixparse(fstype),String.class);
					if (method != null) {
						Object ret = method.invoke(null, val.toString());
						if (ret != null)
							return ret;
					}
				} catch(Exception e) {
					//e.printStackTrace();
					logger.warn(String.format("@@reflexTypeParseFromValToField,val=%s,field=%s error!", val,field.toString()));
					return null;
				}
				
			}
			return null;

			
		}
		
		@Test
		public void test() {
			Integer a = 11;
			
			System.out.println(a+"");
		}

	}
	
	
	
	
	public void test() throws NoSuchAlgorithmException {
		List<String> yearRodMonthList = new ArrayList<>();
		yearRodMonthList =  ButiToolClassZz.queryYearRodMonthByNowTest(18, new Date(), 2, "yyyyMM");
		System.out.println(yearRodMonthList.toString());
		
		System.out.println("@@@@@@@@@@@@@@@@1-md5");
		MessageDigest digest1 = MessageDigest.getInstance("MD5");
	    digest1.update("1234".getBytes());
	    byte[] hash1 = digest1.digest();
	    System.out.println(hash1.toString());
	    
	    System.out.println("@@@@@@@@@@@@@@@@2-ase");
	    String sql ="select \r\n" + 
	    		"t.account_date accountDate,\r\n" + 
	    		"CONCAT('*',SUBSTR(t.agency_name,2)) agencyName,\r\n" + 
	    		"t.cust_cert_code custCertCode,\r\n" + 
	    		"case when d.value is not null then d.value else t.agency_cert_type end  agencyCertType,\r\n" + 
	    		"t.sum_type sumType,\r\n" + 
	    		"t.warn_num warnNum,\r\n" + 
	    		"count(1) dealCouont,\r\n" + 
	    		"DATE_FORMAT(t.warn_create_time, '%Y-%m-%d %T') warnCreateTime\r\n" + 
	    		"from busi_differreport_d2d_perbus_agency_adddiffer t\r\n" + 
	    		"LEFT JOIN saudit.sys_dict d ON d.key = t.agency_cert_type AND d.type='CERT_TYPE'\r\n" + 
	    		"where 1=1\r\n" + 
	    		"@$and t.account_date between #{accountStart} and #{accountEnd} \r\n" + 
	    		"@$and t.agency_cert_code = #{agencyCertCode}\r\n" + 
	    		"@$and t.sum_type = #{sumType}\r\n" + 
	    		"@$\r\n" + 
	    		"GROUP BY t.agency_cert_code\r\n" + 
	    		"order by t.account_date desc,dealCouont desc";
	  //定义变量
	  		String sqlLeft = sql.substring(0, sql.indexOf("@$"));//截取第一个@$前内容，即where 1=1 （含自己）前内容
	  		String sqlRight = sql.substring(sql.indexOf("@$")+2);//截取1=1后内容
	  	    String sqlRightLeft = sqlRight.substring(0,sqlRight.lastIndexOf("@$"));//获取条件and .* 的内容。
	  	    String sqlRightRight = sqlRight.substring(sqlRight.lastIndexOf("@$")+2);//获取条件and后的group等内容
	  	    sqlRightLeft = sqlRightLeft.replaceAll("[\r\n]", " ");//处理成一行
	  	    
	  	    //定义条件行
	  	    String sqlRightRrightCond = " ";
	  	    
	  	    //获取每个and语句进行判断。
	  	    String [] andParam = sqlRightLeft.split("@\\$");
	  	  Map<String, Object> reqParams = new HashMap<String, Object>(){
	  		  {
	  			  this.put("accountStart", "201909-201911");
	  			  this.put("accountEnd", "201909-201911");
	  			  this.put("sumType", "2");
	  		  }
	  	  };
	  	    for(String s : andParam) {
	  	    	Pattern p = Pattern.compile("(?<=\\#\\{)[^}]+");
	  	    	Matcher m = p.matcher(s);
	  	    	boolean nullFlag = false;//初始不空
	  	    	while(m.find()) {
	  	    		String str = m.group();
	  	    		String val = (String) reqParams.get(str);
	  	    		if(StringUtils.isNotBlank(val)){
	  	    			s = s.replace("#{"+str+"}", "'"+val+"' ");
	  	    		}else {
	  	    			nullFlag = true;
	  	    			break;
	  	    		}
	  	    	}
	  	    	if(!nullFlag) {
	  	    		sqlRightRrightCond += (s+" ");
	  	    	}
	  	    }
	  		String reStr = sqlLeft + sqlRightRrightCond +sqlRightRight;
	  		System.out.println("re:"+reStr);
	    
	   /* 
	    System.out.println(sqlRight);
		String regex = "(.*?)and(.*?)[\r\n]";//^(between.*and.*)
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(sqlRight);
		int i=0;
		while(match.find()) {
			System.out.println("gr:"+match.group(i));
			System.out.println(i);
			i++;
		}*/

		}
}
