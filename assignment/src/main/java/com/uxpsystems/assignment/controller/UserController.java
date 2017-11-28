package com.uxpsystems.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.uxpsystems.assignment.entity.UserInfo;
import com.uxpsystems.assignment.service.IUserService;

@Controller
public class UserController {
	@Autowired
	private IUserService userService;
	@GetMapping("user/{id}")
	public ResponseEntity<UserInfo> getUserById(@PathVariable("id") Long id) {
		UserInfo user = userService.getUserById(id);
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}
	@GetMapping("/list")
	public ResponseEntity<List<UserInfo>> getAllUsers() {
		List<UserInfo> list = userService.getAllUsers();
		return new ResponseEntity<List<UserInfo>>(list, HttpStatus.OK);
	}
	@PostMapping("post")
	public ResponseEntity<Void> post(@RequestBody UserInfo user, UriComponentsBuilder builder) {
        boolean flag = userService.postUser(user);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("put")
	public ResponseEntity<UserInfo> put(@RequestBody UserInfo user) {
		userService.putUser(user);
		
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}
	@DeleteMapping("user/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}
