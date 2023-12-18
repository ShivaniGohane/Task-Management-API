package com.Task_Manageent_API.Project1.Exceptionss;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(String msg){
        super(msg);
    }
}
