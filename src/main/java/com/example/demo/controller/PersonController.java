package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Person;
import com.example.demo.PersonRepository;
import com.example.demo.config.GemfireConfiguration;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.TodoEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("person")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/test")
	public ResponseEntity<?> test(@RequestParam String name){
		
		//log.info("aaaaaaaaa");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(GemfireConfiguration.class);
		ctx.refresh();
		
		//Properties gemfireProperties = new Properties();
		//gemfireProperties.setProperty("test", "test");
		
		
		CacheFactoryBean gemfireCache = (CacheFactoryBean) ctx.getBean(CacheFactoryBean.class);
		
		Properties gemfireProperties  = gemfireCache.getProperties();
		gemfireCache.setProperties(gemfireProperties);
		gemfireProperties.setProperty("test", "test");
		
		String name1 = (String) gemfireCache.getProperties().get("name");
		String name2 = (String) gemfireCache.getProperties().get("name2");
		String test = (String) gemfireCache.getProperties().get("test");
		
		log.info("CacheFactoryBean getProperties name : {} / {}", name1 ,name2);
		log.info("test : {}", test);
		
		double dValue = Math.random();
	    int iValue = (int)(dValue * 10);
	    Person aaaa = new Person(name, iValue);
	    personRepository.save(aaaa);

		Person alice = new Person("Adult Alice"+iValue, 40);
		Person bob = new Person("Baby Bob"+iValue, 1);
		Person carol = new Person("Teen Carol"+iValue, 13);
		
		personRepository.save(alice);
		personRepository.save(bob);
		personRepository.save(carol);
		
		
		List<Person> list2 = (List<Person>) personRepository.findAll();
		for(Person t : list2) {
			log.info("person >>>"+t.toString());
		}
		
		ResponseDTO<Person> response = ResponseDTO.<Person>builder().data(list2).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/test2")
	public ResponseEntity<?> test2(){
		
		List<Person> list2 = (List<Person>) personRepository.findAll();
		for(Person t : list2) {
			log.info("test2 person >>>"+t.toString());
		}
		
		ResponseDTO<Person> response = ResponseDTO.<Person>builder().data(list2).build();
		
		return ResponseEntity.ok().body(response);
	}
	
}
