package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.MemoEntity;

@Repository
public interface MemoRepository extends JpaRepository<MemoEntity, String> {
	
	List<MemoEntity> findByRegUser(String userId);
	
}
