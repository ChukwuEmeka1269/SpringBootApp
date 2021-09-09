package com.example.simplespringbootapplication.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentByEmailAndStudentName = studentRepository
                .findStudentByEmailAndStudentName(student.getEmail(), student.getStudentName());

        if(studentByEmailAndStudentName.isPresent())
            throw new IllegalStateException("Student " + student.getStudentName() + " with email " + student.getEmail() + "already saved");
        studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long studentId) {
        boolean existsById = studentRepository.existsById(studentId);
        if(!existsById)
            throw new IllegalStateException("Student with id No. " + studentId + " does not exist.");
        return studentRepository.findById(studentId);
    }


    public void deleteStudentById(Long studentId) {
        boolean existsById = studentRepository.existsById(studentId);
        if(!existsById)
            throw new IllegalStateException("Student with id no. " + studentId + " not found.");
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student not found."));
        if(name!=null && name.length()>0 && !Objects.equals(student.getStudentName(), name))
            student.setStudentName(name);
        if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
            if(optionalStudent.isPresent())
                throw new IllegalStateException("Email already taken.");
            student.setEmail(email);
        }


        studentRepository.save(student);
    }
}
