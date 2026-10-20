package com.exam.sec;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.exam.sec.model.User;
import com.exam.sec.repo.UserRepo;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setUsername("arbaz");
		user1.setPassword("123");
		user1.setRoles(Set.of("USER"));

		userRepo.save(user1);

		User user2 = new User();
		user2.setUsername("anushka");
		user2.setPassword("123");
		user2.setRoles(Set.of("ADMIN"));

		userRepo.save(user2);
	}

}
