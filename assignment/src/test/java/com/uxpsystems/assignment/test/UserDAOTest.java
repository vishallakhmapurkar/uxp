package com.uxpsystems.assignment.test;

import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uxpsystems.assignment.dao.IUserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDAOTest {
    @Mock
	private IUserDAO IUserDAO;
	
	@Test
	public void checkUsers() {
		when(IUserDAO.findAll()).thenReturn(Collections.emptyList());
	}
}
