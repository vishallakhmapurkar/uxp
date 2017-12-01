package com.uxpsystems.assignment.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.uxpsystems.assignment.dao.IUserDAO;
import com.uxpsystems.assignment.entity.UserInfo;
import com.uxpsystems.assignment.service.IUserService;

@SpringBootApplication
@EnableJpaRepositories("com.uxpsystems.assignment.dao")
@EntityScan("com.uxpsystems.assignment.entity")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
	public CommandLineRunner setup(IUserService userService) {
		return (args) -> {
			UserInfo u=new UserInfo();
			u.setUserName("admin");
			u.setPassword("admin");
			u.setRole("ADMIN");
			u.setStatus("ACTIVATED");
			userService.postDummyUser(u);
			
		};
	}
}