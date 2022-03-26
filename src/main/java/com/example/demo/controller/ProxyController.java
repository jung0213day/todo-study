package com.example.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("proxy")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProxyController {
	
	
	@GetMapping("/**")
    public ResponseEntity<?> proxy(
    		HttpServletRequest request, HttpServletResponse response, 
    		@RequestBody(required = false) byte[] body) throws IOException, URISyntaxException {
    	
    
    	//log.info("body : {}", body.toString());
     
    	HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(360000); //6m
        httpRequestFactory.setConnectTimeout(360000); //6m
        httpRequestFactory.setReadTimeout(360000); //6m
     
        // restTempate tobe bean
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
     
        // url
        String originReqURL = request.getRequestURI().replaceAll("^/proxy/", "");
        log.info("1. url : "+originReqURL);
        
        String originQueryString = request.getQueryString();
        log.info("2. url : {}", originQueryString);
        
        String urlStr = originReqURL + (StringUtils.isEmpty(originQueryString) ? "" : "?"+originQueryString);
        log.info("3. url : {}", urlStr);
     
        URI url = new URI(urlStr);
     
        // method
        String originMethod = request.getHeader("x-origin-method");
        if(StringUtils.isEmpty(originMethod)) {
        	originMethod = "GET";
        }
        HttpMethod method = HttpMethod.valueOf(originMethod.toUpperCase());
     
     
        // header
        Enumeration<String> headerNames = request.getHeaderNames();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        while(headerNames.hasMoreElements()) {
        	String headerName = headerNames.nextElement();
        	String headerValue = request.getHeader(headerName);
   
        	headers.add(headerName, headerValue);
        }
     
     
        // http entity (body, header)
        HttpEntity<byte[]> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> result = restTemplate.exchange(url, method, httpEntity, String.class);
        log.info("result >>> : {}", result.getBody().toString());
        
        return ResponseEntity.ok().body(result.getBody());
    }

}
