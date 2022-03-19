package com.example.demo.dto;

import java.util.Date;
import com.example.demo.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {

	private String id;
	private String title;
	private Boolean done;
	private Date regDate;
	
	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.getDone();
		this.regDate = entity.getRegDate();
	}
	
	public static TodoEntity toEntity(final TodoDTO dto) {
		return TodoEntity.builder().id(dto.id).title(dto.title).done(dto.done).regDate(dto.regDate).build();
	}
	
	
}
