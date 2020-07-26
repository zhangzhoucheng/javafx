package com.zz.test.javafxmvn.commontool;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * json对象映射工具类之jackson封装
 */

/**
 * 
 * <note> Desc：
 * 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-15 12:26:55
 * @location mobile base 3th,BeiJing version 1.0
 * 
 * @REVISIONS: Version Date Author Location Description
 *             ------------------------------------------------------------------------------------------------------
 *             1.0 2020-01-15 12:26:55 jld.zhangzhou mobile base 3th,BeiJing
 *             1.create the class </note>
 */
public class JsonUtils {

	private static Logger log = LoggerFactory.getLogger(JsonUtils.class);
	public static ObjectMapper myObjectMapper = null;

	static {
		myObjectMapper = new ObjectMapper();
		
		/**
		 * 默认日期格式
		 */
		myObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		
		/**
		 * 默认设置
		 */
		myObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		myObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		myObjectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		

	}

	/**
	 * Desc:对象转换成json字符串格式（默认将转换所有的属性）
	 * @author jld.zhangzhou
	 * @datetime 2020-05-24 16:07:21
	 * @modify_record:
	 * @param value
	 * @return
	 */
	public static String toJsonStr(Object value) {

		try {
			if (value == null) {
				return "";
			}
			return myObjectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Json transfrom fail", e);
		}
	}

	/**
	 * 将对象转换成json字符串格式
	 * 
	 * @param value
	 *            需要转换的对象
	 * @param properties
	 *            需要转换的属性
	 */
	public static String toJsonStr(Object value, String[] properties) {

		try {
			SimpleBeanPropertyFilter sbp = SimpleBeanPropertyFilter.filterOutAllExcept(properties);
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("propertyFilterMixIn", sbp);
			return myObjectMapper.writer(filterProvider).writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException("Json transfrom fail", e);
		}

	}

	/**
	 * 将对象转换成json字符串格式
	 * 
	 * @param value
	 *            需要转换的对象
	 * @param properties2Exclude
	 *            需要排除的属性
	 */
	public static String toJsonStrWithExcludeProperties(Object value, String[] properties2Exclude) {

		try {
			SimpleBeanPropertyFilter sbp = SimpleBeanPropertyFilter.serializeAllExcept(properties2Exclude);
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("propertyFilterMixIn", sbp);
			return myObjectMapper.writer(filterProvider).writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException("Json transfrom fail", e);
		}

	}

	/**
	 * <pre>
	 * 将对象json格式直接写出到流对象中（默认将转换所有的属性）
	 * </pre>
	 * 
	 * @param out
	 * @param value
	 */
	public static void writeJsonStr(OutputStream out, Object value) {

		try {
			myObjectMapper.writeValue(out, value);
		} catch (Exception e) {
			throw new RuntimeException("Json transfrom fail", e);
		}
	}

	/**
	 * <pre>
	 * 将对象json格式直接写出到流对象中
	 * </pre>
	 * 
	 * @param out
	 * @param value
	 *            需要转换的对象(注意，需要在要转换的对象中定义JsonFilter注解)
	 * @param properties
	 *            需要转换的属性
	 */
	public static void writeJsonStr(OutputStream out, Object value, String[] properties) {

		try {
			myObjectMapper.writer(new SimpleFilterProvider().addFilter(AnnotationUtils
					.getValue(AnnotationUtils.findAnnotation(value.getClass(), JsonFilter.class)).toString(),
					SimpleBeanPropertyFilter.filterOutAllExcept(properties))).writeValue(out, value);
		} catch (Exception e) {
			throw new RuntimeException("Json transfrom fail", e);
		}

	}

	/**
	 * <pre>
	 * 将对象转换成json字符串格式
	 * </pre>
	 * 
	 * @param out
	 * @param value
	 *            需要转换的对象
	 * @param properties2Exclude
	 *            需要排除的属性(注意，需要在要转换的对象中定义JsonFilter注解)
	 */
	public static void writeJsonStrWithExcludeProperties(OutputStream out, Object value, String[] properties2Exclude) {
		try {
			myObjectMapper
					.writer(new SimpleFilterProvider()
							.addFilter(
									AnnotationUtils
											.getValue(
													AnnotationUtils.findAnnotation(value.getClass(), JsonFilter.class))
											.toString(),
									SimpleBeanPropertyFilter.serializeAllExcept(properties2Exclude)))
					.writeValue(out, value);
		} catch (Exception e) {
			throw new RuntimeException("Json transfrom fail", e);
		}

	}

	/**
	 * <pre>
	 * 反序列化POJO或简单Collection如List<String>.
	 * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
	 * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String, JavaType)
	 * </pre>
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {

		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return myObjectMapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			log.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * <pre>
	 * 单独解析某一个json的key值
	 * </pre>
	 * 
	 * @param jsonText
	 * @param key
	 * @return
	 */
	public static JsonNode getjsonvalue(String jsonText, String key) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(jsonText); // 读取Json

			return rootNode.path(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * 解析json属性，放到实体里面去 @Title: readJsonList @param jsondata @param
	 * collectionClass @return 设定文件 List<SpecVO> 返回类型 @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> readJsonList(String jsondata, Class<T> collectionClass) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JavaType javaType = getCollectionType(ArrayList.class, collectionClass);
			List<T> lst = (List<T>) mapper.readValue(jsondata, javaType);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <pre>
	 * json 转map
	 * </pre>
	 * 
	 * @param jsondata
	 * @return Map<String, String>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> readJsonToMap(String jsondata) {
		try {
			Map<String, String> maps = myObjectMapper.readValue(jsondata, Map.class);
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> readJsonToMap1(String jsondata) {
		try {
			Map<String, Object> maps = myObjectMapper.readValue(jsondata, Map.class);
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
