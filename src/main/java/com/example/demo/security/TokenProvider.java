package com.example.demo.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {

	private static final String SECRET_KEY = "ASFSDF3sdf3vflalvk34";
	
	
	public String create(UserEntity userEntity) {
		// 기한은 지금부터 1일로 설정
		Date expirtDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		
		// JWT 토큰 생성 
		return Jwts.builder()
				// header에 들어갈 내용 및 서명을 하기 윈한 SECRET_KEY	
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				// payload에 들어갈 내
				.setSubject(userEntity.getId())
					.setIssuer("demo app")
					.setIssuedAt(new Date())
					.setExpiration(expirtDate).compact();
		
	}
	
	public String validateAndGetUserId(String token) {
		// parseClainmJws 메서드가 Base64로 디코딩 및 파싱 헤더와 페이로드를 setSignKey로 넘어온
		// 시크릿을 이용해 서명한 후 token의 서명 비교 위조 되지 않았다면 메이로드(claims) 리턴, 위조라면 예외를 날림
		// 그중 우리는 userId가 필요하므로 getBody를 부른다.
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}
