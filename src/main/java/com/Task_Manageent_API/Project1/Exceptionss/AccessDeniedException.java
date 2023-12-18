package com.Task_Manageent_API.Project1.Exceptionss;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String str){
        super(str);
    }
}
