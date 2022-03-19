package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/* 

@RunWith(SpringRunner.class)

JUnit에 내장된 실행자 외 다른 실행자를 실행
여기서는 SpringRunner 실행자
즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest

Web(Spring MVC)에 집중하게 해줌 (?)
@Controller, @ControllerAdvice 사용 가능
@Service, @Component, @Repository 사용 불가
@Autowired

스프링이 관리하는 Bean 을 주입 받음
Bean : Spring IoC 컨테이너가 관리하는 자바 객체
private MockMvc mvc

스프링 MVC 테스트의 시작점
GET, POST 등 테스트하게 해줌
사용자 역할?
mvc.perform(get("/hello"))

MockMvc 를 통해 GET 요청
체이닝 지원
.andExpect()

mvc.perform 의 결과 검증
status().isOk()

HTTP Header 의 Status 가 200 인지
content().string("hello")

본문의 내용이 "hello" 인지

*/

@RunWith(SpringRunner.class)	// (1)
@WebMvcTest(controllers = TestController.class)	// (2)
public class TestControllerTest {
	
	@Autowired	// (3)
	private MockMvc mvc;	// (4)

    @Test
    public void hello_리턴() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello"))	// (5)
                .andExpect(status().isOk())	// (6)(7)
                .andExpect(content().string(hello));	// (8)
    }


}







