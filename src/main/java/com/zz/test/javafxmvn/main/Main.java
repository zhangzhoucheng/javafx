package com.zz.test.javafxmvn.main;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


/**
 * 
 * <note>
 * Desc： 桌面应用主程序入口
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-13 13:35:52
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-01-13 13:35:52    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
public class Main extends Application {
	/**
     * 程序初始化，这里可以初始化一些数据
     */
    @Override
    public void init() throws Exception {
        super.init();
    }

    /**
     * 程序关闭时，这里可以做关闭的确认等
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    /**
     * 开启窗口时，所有的 UI 程序写在这里
     * @param primaryStage 窗口对象
     */
	@Override
	public void start(Stage primaryStage) throws IOException {
		/*try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*///初始化原本代码
		
		
		 Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main/Main.fxml"));
	        Scene scene = new Scene(root, 600, 500);
	        scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
	        
	        // 设置程序在菜单栏 和左上角 显示的图标，以及标题
	        primaryStage.getIcons().add(new Image("/img/logo.jpg"));
	        primaryStage.setTitle("JLD ZZ WORLD!");
	        
	        // 设置窗体宽高
	        primaryStage.setWidth(1980);
	        primaryStage.setHeight(1060);
	        // 设置窗口模式
	        primaryStage.initStyle(StageStyle.DECORATED);
	        
	        primaryStage.setScene(scene);
	        
	        // 显示窗口
	        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
