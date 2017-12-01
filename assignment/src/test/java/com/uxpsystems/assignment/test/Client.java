package com.uxpsystems.assignment.test;

import java.net.URI;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.uxpsystems.assignment.entity.UserInfo;

public class Client{
	String URL="http://localhost:8080/assignment/";
	 private HttpHeaders getHeaders() {
	    	String credential="admin:admin";
	    	//String credential="tarun:t123";
	    	String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	     	headers.add("Authorization", "Basic " + encodedCredential);
	    	return headers;
	    }
	 
	 public void postUser() {
	    	HttpHeaders headers = getHeaders();  
	        RestTemplate restTemplate = new RestTemplate();
		UserInfo user=new UserInfo();
		user.setUserName("vivek");
		user.setPassword("vishal1");
		user.setStatus("Activated");
		user.setRole("USER");
		
	        HttpEntity<UserInfo> requestEntity = new HttpEntity<UserInfo>(user, headers);
	        restTemplate.postForLocation(URL+"user/post", requestEntity);
	    	user.setStatus("DEActivated");
	        HttpEntity<UserInfo> requestEntity1 = new HttpEntity<UserInfo>(user, headers);
	        restTemplate.postForLocation(URL+"user/post", requestEntity1);
	    }
	public static void main(String[] args) {
		Client c=new Client();
		c.postUser();
	}
	
}