package com.project.HospitalManagementSystem.Exception;

public class ResourceexistException extends RuntimeException{

    private String message;

   public  ResourceexistException(String message){
        super(message);
        this.message=message;
    }
}
