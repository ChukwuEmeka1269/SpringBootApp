package com.example.simplespringbootapplication.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentByEmailAndStudentName(String email, String studentName);
}
