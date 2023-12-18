package com.Task_Manageent_API.Project1.Service;

import com.Task_Manageent_API.Project1.DTO.request.NewTaskDTO;
import com.Task_Manageent_API.Project1.DTO.request.TaskDTO;
import com.Task_Manageent_API.Project1.Enum.Role;
import com.Task_Manageent_API.Project1.Enum.Status;
import com.Task_Manageent_API.Project1.Exceptionss.AccessDeniedException;
import com.Task_Manageent_API.Project1.Exceptionss.TaskNotExistException;
import com.Task_Manageent_API.Project1.Exceptionss.UserNotExistException;
import com.Task_Manageent_API.Project1.Model.Task;
import com.Task_Manageent_API.Project1.Model.User1;
import com.Task_Manageent_API.Project1.Repository.TaskRepository;
import com.Task_Manageent_API.Project1.Repository.UserRepository;
import org.apache.tomcat.util.http.ConcurrentDateFormat;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository1;
    @Autowired
    UserService userService;

    //Method to create new Task where user need to provide new task details
    public Task createNewTask(NewTaskDTO newTaskDTO, String email){
        User1 user1 = userService.getUserByMail(email);
        if(user1==null){
            throw new UserNotExistException("User Not Exist.");
        }
        Task newtask = new Task();
        newtask.setTitle(newTaskDTO.getTitle());
        newtask.setDescription(newTaskDTO.getDescription());
        newtask.setDate(newTaskDTO.getDate());
        newtask.setStatus(newTaskDTO.getStatus());
        user1.getTaskList().add(newtask);
        taskRepository1.save(newtask);
        if(userService.isPresent(newtask.getId())==null){
            userService.updateUser_Task_List(user1.getUId(), newtask.getId());
        }
        return newtask;
    }

    //Method to get Task List of all task only authenticate user can access it otherwise it throw exception.
    public List<Task> getAllTaskList(String Email){
        User1 user1 = userService.getUserByMail(Email);
        if(user1==null){
            throw new UserNotExistException("User Not Exist.");
        }
        if(!user1.getRole().equals(Role.AdminRole)){
            throw new AccessDeniedException("Access Denied.");
        }
        return taskRepository1.findAll();
    }

    //Method to update the particular task details user need to provide task details which they want to update
    public Task updateTaskDetails(TaskDTO taskDTO, Status status){
        Task prevTask = taskRepository1.getTaskById(taskDTO.getPrevId());
        if(prevTask==null){
            throw new TaskNotExistException("Task Not Exist");
        }
        User1 user1 = userService.getUserByMail(taskDTO.getEmail());
        if(user1==null){
            throw new UserNotExistException("User Not Exist");
        }
        prevTask.setTitle(taskDTO.getTitle());
        prevTask.setDescription(taskDTO.getDescription());
        prevTask.setStatus(status);
        prevTask.setDate(taskDTO.getDate());
        userService.updateUser_Task_List(user1.getUId(), taskDTO.getPrevId());
        taskRepository1.save(prevTask);
        return prevTask;
    }

    //Method to delete the Task only authenticate user can delete it otherwise it throw exception.
    public String deleteTask(UUID taskId, String Email){
        Task task = taskRepository1.getTaskById(taskId);
        User1 user1 = userService.getUserByMail(Email);
        if(task==null){
            throw new TaskNotExistException("Task Not Exist.");
        }
        UUID user2Id = userService.getUserByTaskId(taskId);
        User1 user2 = userService.getUserByUserId(user2Id);
        if(user2.equals(user1) || user1.getRole().equals(Role.AdminRole)){
            userService.delteFromUser_Task_List(taskId);
            taskRepository1.delete(task);
            return "Task Deleted.";
        }
        else {
            throw new AccessDeniedException("Access Denied.");
        }
    }

    //Implementing Page with Filtering and Sorting
    public Page<Task> getTaskListByFilteringAndSorting(String title, int page, int size, List<String> sortList, String sortOrder){
        //create pageable object sing page size and sort details
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        // fetch the page object by additionally passing pageable with the filters
        return taskRepository1.getTaskListFilteringByTitle(title, pageable);
    }

    public List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }
}
