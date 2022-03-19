package com.example.demo.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.TestMapper;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

	@Autowired
	private TodoRepository repository;

	@Autowired
	private TestMapper testMapper;
	

	public String testService() {
		return "Test Service";
	}
	
	
	public List<TodoEntity> testMapper() {
		return testMapper.getAllDataList();
	}


	public List<TodoEntity> cereate(TodoEntity entity) {
		// TODO Auto-generated method stub
		repository.save(entity);
		return repository.findAll(Sort.by(Sort.Direction.DESC, "regDate"));
	}


	public List<TodoEntity> update(TodoEntity entity) {
		// TODO Auto-generated method stub
		repository.save(entity);
		return repository.findAll(Sort.by(Sort.Direction.DESC, "regDate"));
	}


	public List<TodoEntity> getList() {
		// TODO Auto-generated method stub
		return repository.findAll(Sort.by(Sort.Direction.DESC, "regDate"));
	}
}
