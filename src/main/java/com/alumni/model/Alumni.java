package com.alumni.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "alumni")
public class Alumni {
    @Id
    private String id;
    private String name; // This will act as the unique username
    private String fullName;
    private String email;
    private String password; // Will store hashed password
    private String department;
    private Integer batchYear;
    private String currentCompany;
    private String jobRole;
    private String location;
    
    // Additional Profile Details
    private String phone;
    private String bio;
    private String skills;
    private String linkedIn;
    private String github;
    private String achievements;
    private String profilePicture; // Base64 string

    private boolean isApproved = false; // Default false until admin approves
}
