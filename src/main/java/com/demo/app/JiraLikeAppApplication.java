package com.demo.app;

import com.demo.app.Entity.Task;
import com.demo.app.Entity.User;
import com.demo.app.Repository.TaskRepository;
import com.demo.app.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class JiraLikeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiraLikeAppApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, TaskRepository taskRepository){
		return args ->{
			long fetch = userRepository.count();
			System.out.println("users fetch: " + fetch);
			User test = new User("Matej", "Kučan", "mkucan", "12345","mkucan@test.com","123456");
			userRepository.save(test);
			fetch = userRepository.count();
			System.out.println("users fetch: " + fetch);


			long fetchTasks = taskRepository.count();
			System.out.println("Task fetch: " + fetchTasks);
			Task testTask = new Task("Test Task", "Task for testing", "HIGH", "mkucan", "otheruser", new Date());

			Task testTask2 = new Task("Test Task 2", "Task for testing 2", "LOW", "otheruser", "mkucan", new Date());
			taskRepository.save(testTask);
			taskRepository.save(testTask2);
			fetchTasks = taskRepository.count();
			System.out.println("Task fetch: " + fetchTasks);
		};
	}

}
