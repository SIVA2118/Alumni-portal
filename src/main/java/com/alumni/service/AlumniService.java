package com.alumni.service;

import com.alumni.model.Alumni;
import com.alumni.repository.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumniService {

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Alumni> getAllApprovedAlumni() {
        return alumniRepository.findByIsApproved(true);
    }

    public List<Alumni> searchByDepartment(String department) {
        return alumniRepository.findByDepartment(department);
    }

    public List<Alumni> searchByBatchYear(Integer batchYear) {
        return alumniRepository.findByBatchYear(batchYear);
    }

    public Alumni updateProfile(String id, Alumni updatedData) {
        Alumni existing = alumniRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alumni not found"));
        
        // Uniqueness checks for sensitive fields if changed
        if (updatedData.getName() != null && !updatedData.getName().equals(existing.getName())) {
            if (alumniRepository.findByName(updatedData.getName()).isPresent()) {
                throw new RuntimeException("Username already taken!");
            }
            existing.setName(updatedData.getName());
        }

        if (updatedData.getEmail() != null && !updatedData.getEmail().equals(existing.getEmail())) {
            if (alumniRepository.findByEmail(updatedData.getEmail()).isPresent()) {
                throw new RuntimeException("Email already registered!");
            }
            existing.setEmail(updatedData.getEmail());
        }

        if (updatedData.getPassword() != null && !updatedData.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(updatedData.getPassword()));
        }

        if (updatedData.getFullName() != null) {
            existing.setFullName(updatedData.getFullName());
        }
        if (updatedData.getDepartment() != null) {
            existing.setDepartment(updatedData.getDepartment());
        }
        if (updatedData.getBatchYear() != null) {
            existing.setBatchYear(updatedData.getBatchYear());
        }
        if (updatedData.getCurrentCompany() != null) {
            existing.setCurrentCompany(updatedData.getCurrentCompany());
        }
        if (updatedData.getJobRole() != null) {
            existing.setJobRole(updatedData.getJobRole());
        }
        if (updatedData.getLocation() != null) {
            existing.setLocation(updatedData.getLocation());
        }
        if (updatedData.getPhone() != null) {
            existing.setPhone(updatedData.getPhone());
        }
        if (updatedData.getBio() != null) {
            existing.setBio(updatedData.getBio());
        }
        if (updatedData.getSkills() != null) {
            existing.setSkills(updatedData.getSkills());
        }
        if (updatedData.getLinkedIn() != null) {
            existing.setLinkedIn(updatedData.getLinkedIn());
        }
        if (updatedData.getGithub() != null) {
            existing.setGithub(updatedData.getGithub());
        }
        if (updatedData.getAchievements() != null) {
            existing.setAchievements(updatedData.getAchievements());
        }
        if (updatedData.getProfilePicture() != null) {
            existing.setProfilePicture(updatedData.getProfilePicture());
        }
        
        return alumniRepository.save(existing);
    }
}
