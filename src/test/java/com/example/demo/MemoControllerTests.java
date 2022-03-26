package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.MemoEntity;
import com.example.demo.persistence.MemoRepository;

@SpringBootTest
public class MemoControllerTests {

	@Autowired
	private MemoRepository memoRepository;
	
	@Test
	void save() {
		MemoEntity memo = MemoEntity.builder().title("spring test 1")
				.contents("sring test 1 content")
				.delYn("N")
				.regUser("admin").build();
		memoRepository.save(memo);
	}
}
