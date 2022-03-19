package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO<T> {

	private String error;
	private List<T> data;

	/**
	 * 제너릭에 대해서 정리.
	 * 
	 */
}
