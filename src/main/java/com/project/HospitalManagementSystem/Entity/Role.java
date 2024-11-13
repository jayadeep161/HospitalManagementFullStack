package com.project.HospitalManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public enum Role {

    ROLE_Master("Master"),
    ROLE_Admin("Admin"),
    ROLE_Doctor("Doctor"),
    ROLE_Patient("Patient");

    private final String roledescription;

    private static Logger logger= LoggerFactory.getLogger(Role.class);

    Role(String roledescription){
        this.roledescription=roledescription;
    }

    public String getRoledescription(){
        return this.roledescription;
    }

    @JsonCreator
    public static Role fromDescription(String description) {
        for (Role role : Role.values()) {
            if (role.getRoledescription().equalsIgnoreCase(description)) {
                logger.info(role.getRoledescription()+" equals - "+description);
                return role;
            }
            else{
               logger.info(role.getRoledescription()+"-"+description);
            }
        }
        throw new IllegalArgumentException("No matching role for description: " + description);
    }


}
