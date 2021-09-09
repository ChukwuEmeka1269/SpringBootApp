package com.example.simplespringbootapplication.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
       return studentService.getStudent();
    }

    @GetMapping(path = "{id}")
    public Optional<Student> getStudentById(@PathVariable("id") Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @DeleteMapping(path = "{id}")
    public int deleteStudent(@PathVariable("id") Long studentId){
        studentService.deleteStudentById(studentId);
        return 1;
    }

    @PutMapping(path = "{studentId}")
    public String updateStudent(
            @PathVariable Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam (required = false) String email){

        studentService.updateStudent(studentId, name, email);

        return "student updated successfully";
    }
}
