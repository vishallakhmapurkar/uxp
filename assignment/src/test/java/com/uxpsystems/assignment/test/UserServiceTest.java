/*package com.uxpsystems.assignment.test;

import static org.junit.Assert.assertEquals;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.uxpsystems.assignment.config.Application;
import com.uxpsystems.assignment.controller.UserController;
import com.uxpsystems.assignment.entity.UserInfo;
import com.uxpsystems.assignment.service.IUserService;
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = true)
@ContextConfiguration(classes={Application.class})

public class UserServiceTest  {
	String URL = "http://localhost:8080";
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IUserService userService;

	private static HttpHeaders getHeaders() {
		String credential = "admin:admin";
		// String credential="tarun:t123";
		String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + encodedCredential);
		return headers;
	}
	@Test
	public void createStudentCourse() throws Exception {
		UserInfo mockUser = new UserInfo();
		mockUser.setUserName("Vishal");
		mockUser.setPassword("Vishal");
		mockUser.setRole("USER");
		mockUser.setStatus("ACTIVATED");
		// studentService.addCourse to respond back with mockCourse
		Mockito.when(
				userService.postUser(mockUser)).thenReturn(true);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/assignment/user/post")
				.headers(getHeaders());

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		

	}
}
*/