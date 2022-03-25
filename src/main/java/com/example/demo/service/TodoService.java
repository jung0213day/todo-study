package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.TestMapper;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		// Validations
		validate(entity);
		
		repository.save(entity);
		return repository.findAll(Sort.by(Sort.Direction.DESC, "regDate"));
	}

	private void validate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}

		if(entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}

	public List<TodoEntity> update(TodoEntity entity) {
		// TODO Auto-generated method stub
		// (1) 저장 할 엔티티가 유효한지 확인한다. 이 메서드는 2.3.1 Create Todo에서 구현했다.
		validate(entity);
		
		// (2) 넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트 할 수 없기 때문이다.
		final Optional<TodoEntity> original = repository.findById(entity.getId());

		original.ifPresent(todo -> {
			// (3) 반환된 TodoEntity가 존재하면 값을 새 entity의 값으로 덮어 씌운다.
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.getDone());

			// (4) 데이터베이스에 새 값을 저장한다.
			repository.save(todo);
		});
				
		repository.save(entity);
		return repository.findAll(Sort.by(Sort.Direction.DESC, "regDate"));
	}


	public List<TodoEntity> getList() {
		// TODO Auto-generated method stub
		return repository.findAll(Sort.by(Sort.Direction.DESC, "regDate"));
	}


	public List<TodoEntity> retrieve(final String userId) {
		return repository.findByUserId(userId);
	}
}
