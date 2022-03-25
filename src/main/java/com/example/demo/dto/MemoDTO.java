package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.MemoEntity;
import com.example.demo.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDTO {

	private int idx;
	private String title;
	private String contents;
	private String img1;
	private String img2;
	private String img3;
	private Date regDate;
	
	public MemoDTO(final MemoEntity entity) {
		this.idx = entity.getIdx();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.regDate = entity.getRegDate();
	}
	
	public static MemoEntity toEntity(final MemoDTO dto) {
		return MemoEntity.builder().idx(dto.idx).title(dto.title).contents(dto.contents)
				.img1(dto.img1)
				.img2(dto.img2)
				.img3(dto.img3)
				.regDate(dto.regDate).build();
	}
	
	
}
