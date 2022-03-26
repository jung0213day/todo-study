package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MemoEntity;
import com.example.demo.persistence.MemoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoService {
	
	@Autowired
	private MemoRepository memoRepository;
	
	public List<MemoEntity> getList() {
		log.info("MemoService getList");
		return memoRepository.findAll();
	}

	public MemoEntity createMemo(MemoEntity memo) {
		// TODO Auto-generated method stub
		return memoRepository.save(memo);
	}
	

}
