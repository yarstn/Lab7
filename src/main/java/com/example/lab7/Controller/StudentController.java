package com.example.lab7.Controller;
import com.example.lab7.ApiResponse.ApiResponse;
import com.example.lab7.Model.Student;
import com.example.lab7.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
private final StudentService serviceSystem;
//get
@GetMapping("/get")
public ResponseEntity getAllStudents() {
    return ResponseEntity.status(200).body(serviceSystem.getStudents());
}
//add
@PostMapping("/add")
public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors errors) {
    if (errors.hasErrors()) {
        String message = errors.getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    serviceSystem.addStudent(student);
    return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
}
//update
@PutMapping("update/{id}")
    public ResponseEntity updateStudent(@PathVariable int id,@Valid @RequestBody Student student, Errors errors) {
    if (errors.hasErrors()) {
        String message = errors.getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }
    serviceSystem.updateStudent(id, student);
    return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
}
//delete
@DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id) {
    boolean success = serviceSystem.deleteStudent(id);
    if (success) {
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
    }
    return ResponseEntity.status(400).body(new ApiResponse("Student deleted successfully"));
}

    // Get all students by course
    @GetMapping("/students/{course}")
    public ResponseEntity getAllStudentsByCourse(@PathVariable String course) {
        ArrayList<Student> students = new ArrayList<>();
        if ( students.isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("No students found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("students found"+students));
    }


    // Get student name by id
    @GetMapping("/name/{id}")
    public ResponseEntity getStudentNameById(@PathVariable int id) {
ArrayList<Student> students = serviceSystem.getStudentNameById(id);
        if ( students.isEmpty()) {
            return ResponseEntity.status(404).body("Student not found");
         }
        return ResponseEntity.status(200).body(new ApiResponse("Student found: " + students));
        }
    // Change student course
    @PutMapping("/students/{id}/{course}")
    public ResponseEntity changeStudentCourse(@PathVariable int id,@PathVariable String course) {
        boolean success = serviceSystem.changeStudentCourse(id, course);
        if (success) {
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @GetMapping("/students/age")
    public ResponseEntity getStudentByAge(@PathVariable int age) {
        List<Student> matchingStudents = serviceSystem.getStudentByAge(age);
      if (matchingStudents.isEmpty()) {
          return ResponseEntity.status(404).body(new ApiResponse("No students found"));
      }
      return ResponseEntity.status(200).body(new ApiResponse("students found: " + matchingStudents));
    }

}
