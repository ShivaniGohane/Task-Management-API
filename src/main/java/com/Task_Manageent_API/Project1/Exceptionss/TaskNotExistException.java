package com.Task_Manageent_API.Project1.Exceptionss;

public class TaskNotExistException extends RuntimeException{
    public TaskNotExistException(String msg){
        super(msg);
    }
}
