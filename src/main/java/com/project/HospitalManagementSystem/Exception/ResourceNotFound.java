package com.project.HospitalManagementSystem.Exception;

public class ResourceNotFound extends RuntimeException{

    private String message;

    ResourceNotFound(String message){
        super(message);
        this.message=message;
    }
}
