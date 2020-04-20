package com.zz.test.javafxmvn.commontool.threadtool;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;




@Configuration
@EnableScheduling
@Component
public class MyScheduleTool {
	/*@Autowired
	private GrywjhService grywjhService;
	
	
	@Scheduled(cron = "0 0 3 * * * ")
	public void task_one() throws ClientProtocolException, IOException{
		grywjhService.queryOrderDetaiListNewDictSchedule();
	}*/
}
