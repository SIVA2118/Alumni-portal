package com.alumni.repository;

import com.alumni.model.Alumni;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface AlumniRepository extends MongoRepository<Alumni, String> {
    Optional<Alumni> findByEmail(String email);
    Optional<Alumni> findByName(String name);
    List<Alumni> findByDepartment(String department);
    List<Alumni> findByBatchYear(Integer batchYear);
    List<Alumni> findByIsApproved(boolean isApproved);
}
