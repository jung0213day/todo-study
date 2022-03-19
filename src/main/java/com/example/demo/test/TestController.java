package com.example.demo.test;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {
	
	@GetMapping("/test")
	public ModelAndView name() {
		
		ModelAndView mav = new ModelAndView();
		log.trace("Trace Level 테스트");
		log.debug("DEBUG Level 테스트");
		log.info("INFO Level 테스트"); 
		log.warn("Warn Level 테스트");
		log.error("ERROR Level 테스트");
		
		return mav;
	}
	
	@GetMapping("/hello")
    public Map<String, Object> hello(){
		
		int[][] arrScore = {
				{1, 2, 3},
				{1, 2, 3},
				{1, 2, 3},
				{1, 2, 3},
				{1, 2, 3}
		};
		
		int sum = 0;
		int sum2 = 0;
		int sum3 = 0;
		
		for(int i = 0; i<arrScore.length; i++) {
			
			int a = 0;
					
			for(int j = 0; j<arrScore[i].length; j++) {
				int s = arrScore[i][j];
				
				a += s;
				if(j == 0) {
					sum += s;
				}else if(j == 1) {
					sum2 += s;
				}else {
					sum3 += s;
					
				}
			}
			// 학기 평균
			System.out.println(i+" 학 평균 : "+a/arrScore[i].length);
		}
		
		System.out.println("국어  총점 : "+sum);
		System.out.println("영어  총점 : "+sum2);
		System.out.println("수학  총점 : "+sum3);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("sum", sum);
		map.put("sum2", sum2);
		map.put("sum3", sum3);
		
		student st4 = new student("이재명", 1, 2);
		st4.setSc(new int[]{1, 4, 7, 8, 10});
		st4.avgS();
		
		student st5 = new student();
		st5.setName("윤석");
		
		
        return map;
    }
	
}
