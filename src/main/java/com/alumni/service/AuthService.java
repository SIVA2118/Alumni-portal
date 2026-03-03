package com.alumni.service;

import com.alumni.model.Admin;
import com.alumni.model.Alumni;
import com.alumni.repository.AdminRepository;
import com.alumni.repository.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Alumni registerAlumni(Alumni alumni) {
        if (alumniRepository.findByEmail(alumni.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }
        if (alumniRepository.findByName(alumni.getName()).isPresent()) {
            throw new RuntimeException("User Name already taken! Please choose a unique name.");
        }
        alumni.setPassword(passwordEncoder.encode(alumni.getPassword()));
        alumni.setApproved(false);
        return alumniRepository.save(alumni);
    }

    public Alumni loginAlumni(String identifier, String password) {
        Optional<Alumni> alumniOpt = alumniRepository.findByEmail(identifier);
        if (alumniOpt.isEmpty()) {
            alumniOpt = alumniRepository.findByName(identifier);
        }

        if (alumniOpt.isPresent()) {
            Alumni alumni = alumniOpt.get();
            if (passwordEncoder.matches(password, alumni.getPassword())) {
                if (!alumni.isApproved()) {
                    throw new RuntimeException("Account not approved. Please wait for admin approval.");
                }
                return alumni;
            }
        }
        throw new RuntimeException("Invalid credentials.");
    }

    public Admin loginAdmin(String username, String password) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (passwordEncoder.matches(password, admin.getPassword())) {
                return admin;
            }
        }
        throw new RuntimeException("Invalid admin credentials.");
    }
    
    // Seed an initial admin if none exists
    public void seedInitialAdmin() {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            adminRepository.save(admin);
        }
    }
}
