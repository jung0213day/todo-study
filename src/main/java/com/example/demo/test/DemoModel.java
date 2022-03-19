package com.example.demo.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class DemoModel {

	@NonNull
	private String id;
	
	private String userId;
	
    private String userPwd;
    
    private String name;
    
    private String authType;
}
