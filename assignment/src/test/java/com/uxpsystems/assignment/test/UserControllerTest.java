package com.uxpsystems.assignment.test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.uxpsystems.assignment.config.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
	private MockMvc mockMvc;

	private HttpHeaders getHeaders() {
		String credential = "admin:admin";
		String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + encodedCredential);
		return headers;
	}

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyAllUsersList() throws Exception {
		AuthenticationUtil.configureAuthentication("ROLE_ADMIN");
		mockMvc.perform(MockMvcRequestBuilders.get("/users/list").headers(getHeaders()))
				.andExpect(jsonPath("$", hasSize(1))).andDo(print());
		AuthenticationUtil.clearAuthentication();
	}

	@Test
	public void verifySaveUser() throws Exception {
		AuthenticationUtil.configureAuthentication("ROLE_ADMIN");
		mockMvc.perform(MockMvcRequestBuilders.post("/user/post").contentType(MediaType.APPLICATION_JSON)
				.headers(getHeaders())
				.content("{\"userName\":\"user\",\"password\":\"user\",\"role\":\"USER\",\"status\":\"ACTIVATED\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").exists()).andDo(print());
		AuthenticationUtil.clearAuthentication();
	}

	@Test
	public void verifyUpdateUser() throws Exception {
		AuthenticationUtil.configureAuthentication("ROLE_ADMIN");
		mockMvc.perform(MockMvcRequestBuilders.put("/user/put").contentType(MediaType.APPLICATION_JSON)
				.headers(getHeaders())
				.content(
						"{\"id\":2,\"userName\":\"user\",\"password\":\"passwordupdate\",\"role\":\"USER\",\"status\":\"ACTIVATED\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.password").value("passwordupdate"))
				.andDo(print());
		AuthenticationUtil.clearAuthentication();
	}

	@Test
	public void verifyDeleteUser() throws Exception {
		AuthenticationUtil.configureAuthentication("ROLE_ADMIN");
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/user/2").contentType(MediaType.APPLICATION_JSON).headers(getHeaders()))
				.andDo(print());
		AuthenticationUtil.clearAuthentication();
	}
	@Test
	public void verifyGetDeletedUser() throws Exception {
		AuthenticationUtil.configureAuthentication("ROLE_ADMIN");
		mockMvc.perform(MockMvcRequestBuilders.get("/user/2").headers(getHeaders()))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("user doesn´t exist"))
	     .andDo(print());
		AuthenticationUtil.clearAuthentication();
	}
	@Test
	public void verifyDeletedUnknownUser() throws Exception {
		AuthenticationUtil.configureAuthentication("ROLE_ADMIN");
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/3").headers(getHeaders()))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("user doesn´t exist"))
	     .andDo(print());
		AuthenticationUtil.clearAuthentication();
	}
}
