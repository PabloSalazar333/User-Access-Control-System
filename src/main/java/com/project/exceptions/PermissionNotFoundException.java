package com.project.exceptions;

public class PermissionNotFoundException extends RuntimeException{
    public PermissionNotFoundException(String message){
        super(message);
    }
}
