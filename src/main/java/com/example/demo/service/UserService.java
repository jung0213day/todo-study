package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	//
	public UserEntity create(final UserEntity userEntity) {
		//
		log.info("user create");
		if(userEntity == null || userEntity.getEmail() == null) {
			throw new RuntimeException("Invaild arguments");
		}
		
		final String email = userEntity.getEmail();
		log.info("service create email : {}", email);
		
		//Boolean isExist2 = userRepository.existsById(email);
		//log.info("isExist2 : {}", isExist2);

		Boolean isExist = userRepository.existsByEmail(email);
		log.info("isExist : {}", isExist);

		
		if(isExist) {
			log.warn("Email already exists {}", email);
			throw new RuntimeException("Email already exists");
		}
		
		String userName = userEntity.getUserName();
		log.info("user name : {}", userName);		
		return userRepository.save(userEntity);
	}

	// 
	public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
		final UserEntity originalUser = userRepository.findByEmail(email);
		
		// matches 메서드를 이용해 패스워드가 같은지 확인
		if(originalUser != null && encoder.matches(password, originalUser.getPasswd())) {
			return originalUser;
		}
		
		return null;
	}
}
