package com.Task_Manageent_API.Project1.Controller;

import com.Task_Manageent_API.Project1.DTO.request.NewTaskDTO;
import com.Task_Manageent_API.Project1.DTO.request.TaskDTO;
import com.Task_Manageent_API.Project1.DTO.response.GeneralMessageDTO;
import com.Task_Manageent_API.Project1.Enum.Status;
import com.Task_Manageent_API.Project1.Exceptionss.AccessDeniedException;
import com.Task_Manageent_API.Project1.Exceptionss.TaskNotExistException;
import com.Task_Manageent_API.Project1.Exceptionss.UserNotExistException;
import com.Task_Manageent_API.Project1.Model.Task;
import com.Task_Manageent_API.Project1.Repository.TaskRepository;
import com.Task_Manageent_API.Project1.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    // CreateTask API has feature to create a add new Task in Database for authenticate user.
    @PostMapping("/CreateTask")
    public ResponseEntity createTask(@RequestBody NewTaskDTO newTaskDTO, @RequestParam("Email")String email){
        try {
            Task task = taskService.createNewTask(newTaskDTO, email);
            return new ResponseEntity(task, HttpStatus.CREATED);
        }
        catch (UserNotExistException userNotExistException){
            return new ResponseEntity(new GeneralMessageDTO(userNotExistException.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    // TaskList API provided TaskList to authenticate and Admin user.
    @GetMapping("/TaskList")
    public ResponseEntity getTaskList(@RequestParam("Email")String email){
        try {
            List<Task> taskList = taskService.getAllTaskList(email);
            return new ResponseEntity(taskList, HttpStatus.OK);
        }
        catch (UserNotExistException userNotExistException){
            return new ResponseEntity(new GeneralMessageDTO(userNotExistException.getMessage()), HttpStatus.NOT_FOUND);
        }catch (AccessDeniedException accessDeniedException){
            return new ResponseEntity(new GeneralMessageDTO(accessDeniedException.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    // UpdateTask API has feature to update a task details and change a status of a task(e.g., pending, In Progress, completed).
    @PostMapping("/updateTask")
    public ResponseEntity updateTask(@RequestBody TaskDTO taskDTO, @RequestParam("Status") Status status){
        try{
            Task updatedTask = taskService.updateTaskDetails(taskDTO, status);
            return new ResponseEntity(updatedTask, HttpStatus.OK);
        }
        catch (TaskNotExistException taskNotExistException){
            return new ResponseEntity(new GeneralMessageDTO(taskNotExistException.getMessage()), HttpStatus.NOT_FOUND);
        }catch (UserNotExistException userNotExistException){
            return new ResponseEntity(new GeneralMessageDTO(userNotExistException.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    //deleteTask API provide a access to delete the task to authenticate and ADMIN user.
    @DeleteMapping("/deleteTask")
    public ResponseEntity deleteTask(@RequestParam("TaskId")UUID taskId, @RequestParam("Email")String email){
        try {
            String result = taskService.deleteTask(taskId, email);
            return new ResponseEntity(result, HttpStatus.FOUND);
        }
        catch (TaskNotExistException taskNotExistException){
            return new ResponseEntity(new GeneralMessageDTO(taskNotExistException.getMessage()), HttpStatus.NOT_FOUND);
        }catch (AccessDeniedException accessDeniedException){
            return new ResponseEntity(new GeneralMessageDTO(accessDeniedException.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    //Pages with Filtering and Sorting
    @GetMapping("/FilteringAndSorting")
    public Page<Task> getTaskListByFilteringAndSorting(@RequestParam(defaultValue = "") String title,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "25") int size,
                                                       @RequestParam(defaultValue = "") List<String> sortList,
                                                       @RequestParam(defaultValue = "DESC")Sort.Direction sortOrder){
        return taskService.getTaskListByFilteringAndSorting(title, page, size, sortList, String.valueOf(sortOrder));
    }
}
