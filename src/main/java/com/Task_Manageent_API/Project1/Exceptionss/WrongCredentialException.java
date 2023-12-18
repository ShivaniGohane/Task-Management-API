package com.Task_Manageent_API.Project1.Exceptionss;

public class WrongCredentialException extends RuntimeException{
    public WrongCredentialException(String msg){
        super(msg);
    }
}
