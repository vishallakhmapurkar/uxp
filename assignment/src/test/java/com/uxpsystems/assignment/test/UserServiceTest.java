package com.uxpsystems.assignment.test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.uxpsystems.assignment.config.Application;
import com.uxpsystems.assignment.dao.IUserDAO;
import com.uxpsystems.assignment.entity.UserInfo;
import com.uxpsystems.assignment.service.UserServiceImpl;


@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class UserServiceTest  {

	@Mock
	private  IUserDAO IUserDAO;
	@InjectMocks
	private  UserServiceImpl userService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveUser() throws Exception {
		AuthenticationUtil.configureAuthentication("ROLE_ADMIN");


		
		List<UserInfo> result = userService.getAllUsers();
		//defualt user admin
		assertEquals(null, result);
		
	}		
		

}
