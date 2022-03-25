package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.dto.MemoDTO;
import com.example.demo.model.MemoEntity;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.MemoService;
import com.example.demo.service.TodoService;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("memo")
@ApiIgnore
public class MemoController {

	@Autowired
	private TodoService todoService;
	
	@Autowired
	private MemoService memoService;
	
	@GetMapping
	public ResponseEntity<?> getTodoListAll() {
		
		try {
			
			List<TodoEntity> entities = todoService.getList();
			// 4 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			// TODO: handle exception
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
			
		}
	}
	
	@GetMapping("/memo")
	public ResponseEntity<?> getMemoList() {
		
		try {
			
			log.info("getMemoList start");
			
			List<MemoEntity> entities = memoService.getList();
			
			log.info("list size : {}", entities.size());
			
			// 4 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
			List<MemoDTO> dtos = entities.stream().map(MemoDTO::new).collect(Collectors.toList());
			
			ResponseDTO<MemoDTO> response = ResponseDTO.<MemoDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			// TODO: handle exception
			String error = e.getMessage();
			ResponseDTO<MemoDTO> response = ResponseDTO.<MemoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
			
		}
	}
			
}
