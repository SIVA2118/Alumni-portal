package com.alumni.service;

import com.alumni.model.Alumni;
import com.alumni.repository.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AlumniRepository alumniRepository;

    public List<Alumni> getPendingApprovals() {
        return alumniRepository.findByIsApproved(false);
    }

    public Alumni approveAlumni(String id) {
        Alumni alumni = alumniRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alumni not found"));
        
        alumni.setApproved(true);
        return alumniRepository.save(alumni);
    }

    public void deleteAlumni(String id) {
        alumniRepository.deleteById(id);
    }
}
