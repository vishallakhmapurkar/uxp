package com.uxpsystems.assignment.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.uxpsystems.assignment.entity.UserInfo;
import com.uxpsystems.assignment.service.IUserService;

@RestController
public class UserController {
	@Autowired
	private IUserService userService;
	@GetMapping("user/{id}")
	public ResponseEntity<UserInfo> getUserById(@PathVariable("id") Long id) {
		UserInfo user = userService.getUserById(id);
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}
	@GetMapping("users/list")
	public ResponseEntity<List<UserInfo>> getAllUsers() {
		List<UserInfo> list = userService.getAllUsers();
		return new ResponseEntity<List<UserInfo>>(list, HttpStatus.OK);
	}
	@PostMapping("/user/post")
	public ResponseEntity<Void> post(@RequestBody UserInfo user) {
		UserInfo userinfo = userService.postUser(user);
        if (userinfo == null) {
        	 return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"user/{id}").buildAndExpand(userinfo.getId()).toUri();
        return  ResponseEntity.created(location).build();
	
	}
	@PutMapping("user/put")
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
