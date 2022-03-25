package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.security.TokenProvider;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("auth")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	// Bean으로 작성해도 됨.
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
		log.info("signup");
		
		try {
			
			log.info("registerUser : {}", userDTO.getEmail());
			// 요청을 이용해 저장할 사용자를 만들기
			UserEntity user = UserEntity.builder()
						.email(userDTO.getEmail())
						.userName(userDTO.getUserName())
						.passwd(passwordEncoder.encode(userDTO.getPassword()))
						.build();
			
			// 서비스를 이용해 리포지터리에 사용자 지정
			UserEntity registeredUser = userService.create(user);
			
			UserDTO responseUserDTO = UserDTO.builder()
							.email(registeredUser.getEmail())
							.id(registeredUser.getId())
							.userName(registeredUser.getUserName()).build();
			
			return ResponseEntity.ok().body(responseUserDTO);
			
		} catch (Exception e) {
			// TODO: handle exception
			// 에러
			ResponseDTO errorResponseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			log.warn("error signup : {}", e.getMessage());
			return ResponseEntity.badRequest().body(errorResponseDTO);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
		UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(), passwordEncoder);
		
		if(user != null) {
			// 토큰 생성
			final String token = tokenProvider.create(user);
			final UserDTO responseUserDTO = UserDTO.builder().email(user.getEmail()).id(user.getId())
					.token(token)
					.build();
			return ResponseEntity.ok().body(responseUserDTO);
		}else {
			
			ResponseDTO errorResponseDTO = ResponseDTO.builder().error("Login Failed.").build();
			return ResponseEntity.badRequest().body(errorResponseDTO);
		}
	}
	

}