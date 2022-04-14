package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Person;
import com.example.demo.PersonRepository;
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
		
		log.info("aaaaaaaaa");
		
		double dValue = Math.random();

	    int iValue = (int)(dValue * 10);

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
	
}
