package com.zz.test.javafxmvn;

import javafx.application.Application;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zz.test.javafaxmvn.commoninterceptor.LogInterceptor;
import com.zz.test.javafxmvn.commondb.CommonDb;
import com.zz.test.javafxmvn.commondb.SqlMapper;
import com.zz.test.javafxmvn.main.Main;

/**
 * 
 * <note> Desc：boot入口
 * 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-13 14:31:17
 * @location mobile base 3th,BeiJing version 1.0
 * 
 * @REVISIONS: Version Date Author Location Description
 *             ------------------------------------------------------------------------------------------------------
 *             1.0 2020-01-13 14:31:17 jld.zhangzhou mobile base 3th,BeiJing
 *             1.create the class </note>
 */
//@SpringBootApplication
//@ComponentScan(basePackages = { "com.zz.test.web","com.zz.test.javafxmvn","com.zz.test.javafxmvn.main.service" })
@EnableTransactionManagement
public class AppDesktop implements CommandLineRunner {
	/**
	 * SpringBoot 的入口，这里关闭了启动时会出现的 banner 图形
	 * 
	 * @param args
	 *            启动时输入的参数
	 */
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AppDesktop.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		//SpringApplication.run(AppDesktop.class, args);//启动boot项目
	}

	/**
	 * SpringBoot 初始化完成后立即执行该方法，在这里制定窗体程序的入口类
	 * 
	 * @param args
	 *            启动时输入的参数
	 */
	@Override
	public void run(String... args) throws Exception {
		System.out.println("SpringBoot 桌面应用");
		Application.launch(Main.class, args);

	}

	@Bean
	public CommonDb commonDb() {
		return new CommonDb();

	}

	@Bean
	public SqlMapper sqlMapper() {
		return new SqlMapper();

	}

	/*@Configuration //2.x放弃这个方式
	public class WebMvcConfig extends WebMvcConfigurerAdapter {

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			// registry.addInterceptor(new
			// UserInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login");
			registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
			super.addInterceptors(registry);
		}
	}*/
	
	/**
	 * Spring 5 (或者Spring Boot 2.x)版本配置Web应用程序示例
	 * @author ramostear
	 * @create-time 2019/4/18 0018-1:40
	 */
	@Configuration
	public class MvcConfigure implements WebMvcConfigurer{

	    @Override
	    public void configurePathMatch(PathMatchConfigurer configurer) {
	        configurer.setUseSuffixPatternMatch(false);
	    }

	    /*@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/**")//  /** 格式会解析到如下路径，classpath（项目绝对路径），
	                .addResourceLocations("classpath:/static/")
	                .addResourceLocations("classpath:/public/")
	                .addResourceLocations("classpath:/resources/");
	       
	    }*/
	    @Override
		public void addInterceptors(InterceptorRegistry registry) {
			// registry.addInterceptor(new
			// UserInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login");
			registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
		}
	}
}
