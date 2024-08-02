package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Student;
import com.example.demo.Service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;




@RequestMapping("/api/v1/students")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    // Read (Get)
    @GetMapping("/get")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getStudents();
        return ResponseEntity.ok(students);
    }



    // add
    @PostMapping("/add")
    public ResponseEntity<Student> createStudent(@Valid@RequestBody Student student,Errors errors) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable int id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(msg);
        }
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body("updated successfully");
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(int id){
        boolean success = studentService.deleteStudent(id);
     if(success) {
         return ResponseEntity.status(200).body("deleted successfully");
     }
     return ResponseEntity.badRequest().body("deletion failed");
     }

          // Get all students by course
          @GetMapping("/students/{course}")
          public ResponseEntity<List<Student>> getAllStudentsByCourse(@PathVariable String course) {
              List<Student> students = studentService.getAllStudentsByCourse(course);
              return ResponseEntity.ok(students);
          }

    // Get student name by id
    @GetMapping("/student/{id}")
    public ResponseEntity getStudentNameById(@PathVariable int id) {
        String studentName = studentService.getStudentNameById(id);
        if (studentName == null) {
            return ResponseEntity.status(200).body("students not found ");
        }
        return ResponseEntity.ok(studentName);
    }
    // Change student course
    @PutMapping("/students/{id}/{course}")
    public ResponseEntity changeStudentCourse(@PathVariable int id,@PathVariable String newCourse) {
        Student updatedStudent = studentService.changeStudentCourse(id, newCourse);
        if (updatedStudent == null) {
            return ResponseEntity.status(200).body("students not found ");
        }
        return ResponseEntity.badRequest().body("Course Updated Successfully");
    }
    @GetMapping("/students/age")
    public ResponseEntity getStudentByAge(@RequestParam int age) {
        List<Student> matchingStudents = studentService.getStudentByAge(age);
        return ResponseEntity.ok(matchingStudents);
    }
    
     
}