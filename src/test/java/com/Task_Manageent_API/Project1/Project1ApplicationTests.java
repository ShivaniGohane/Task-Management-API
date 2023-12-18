package com.Task_Manageent_API.Project1;

import com.Task_Manageent_API.Project1.DTO.request.NewTaskDTO;
import com.Task_Manageent_API.Project1.DTO.request.TaskDTO;
import com.Task_Manageent_API.Project1.Enum.Role;
import com.Task_Manageent_API.Project1.Enum.Status;
import com.Task_Manageent_API.Project1.Model.Task;
import com.Task_Manageent_API.Project1.Model.User1;
import com.Task_Manageent_API.Project1.Service.TaskService;
import com.Task_Manageent_API.Project1.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest
class Project1ApplicationTests {
	@Autowired
	UserService userService;

	@Autowired
	TaskService taskService;

	@Test
	void contextLoads() {


	}
	// Created the dummy object as User2 and Test2 to copy value of expected user result and actual task result as we can predict the UUId id value because its auto-generated.

	//Unit Test for SignUp
	@Test
	void SignUpTest(){
		User2 expectedUser = new User2("test@gmail.com", "Test@123", Role.UserRole);

		User1 user1 = userService.SignUp("test@gmail.com", "Test@123", "ACCESS");

		User2 actualUser = new User2(user1.getEmail(), user1.getPassword(), user1.getRole());

		String expected = expectedUser.toString();
		String actual = actualUser.toString();

		assertThat(actual).isEqualTo(expected);
	}

	//Unit Test Login
	@Test
	void loginTest(){

		User2 expectedUser = new User2("test@gmail.com", "Test@123", Role.AdminRole);

		User1 user1 = userService.Login("test@gmail.com", "Test@123");

		User2 actualUser = new User2(user1.getEmail(), user1.getPassword(), user1.getRole());

		assertThat(actualUser.getEmail()).isEqualTo(expectedUser.getEmail());
		assertThat(actualUser.getPassword()).isEqualTo(expectedUser.getPassword());
		assertThat(actualUser.getRole()).isEqualTo(expectedUser.getRole());
	}

	//Unit Test for Adding New Task
	@Test
	void AddTaskTest(){
		Task2 expectedTask = new Task2("Test_Task", "Test_Task Added", null, Status.Progress);

		NewTaskDTO newTaskDTO = new NewTaskDTO("Test_Task", "Test_Task Added", null, Status.Progress);
		Task task = taskService.createNewTask(newTaskDTO, "test@gmail.com");
		User1 user1 = userService.getUserByMail("test@gmail.com");
		userService.inserUser_Task_Table(user1.getUId(), task.getId());

		Task2 actualTask = new Task2(task.getTitle(), task.getDescription(), task.getDate(), task.getStatus());

		assertThat(actualTask.getTitle()).isEqualTo(expectedTask.getTitle());
		assertThat(actualTask.getDescription()).isEqualTo(expectedTask.getDescription());
		assertThat(actualTask.getDate()).isEqualTo(expectedTask.getDate());
		assertThat(actualTask.getStatus()).isEqualTo(expectedTask.getStatus());
	}

	//Unit Test for Updating Task.
	@Test
	void upDateTaskTest(){
		Task2 expectedTask = new Task2("Test_Updated_Task", "Test_Updated_Task Updated", null, Status.Completed);

		TaskDTO taskDTO = new TaskDTO( UUID.fromString("e9425d5c-68ef-4f5d-a40a-3863a4bf4b29") , "Test_Updated_Task", "Test_Updated_Task Updated", null, "test@gmail.com");
		Task task = taskService.updateTaskDetails(taskDTO, Status.Completed);

		Task2 actualTask = new Task2(task.getTitle(), task.getDescription(), task.getDate(), task.getStatus());

		assertThat(actualTask.getTitle()).isEqualTo(expectedTask.getTitle());
		assertThat(actualTask.getDescription()).isEqualTo(expectedTask.getDescription());
		assertThat(actualTask.getDate()).isEqualTo(expectedTask.getDate());
		assertThat(actualTask.getStatus()).isEqualTo(expectedTask.getStatus());
	}

	//Unit Test for Deleting Task
	@Test
	void DeleteTaskTest(){
		String expected = "Task Deleted.";

		String actual = taskService.deleteTask(UUID.fromString("e9425d5c-68ef-4f5d-a40a-3863a4bf4b29") , "test@gmail.com");

		assertThat(actual).isEqualTo(expected);
	}

}
