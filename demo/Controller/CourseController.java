package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Course;
import com.example.demo.Service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
@RestController

public class CourseController{
    private final CourseService courseService;

  @GetMapping("/get")
public ResponseEntity getMethodName() {
    List<Course> courseList = courseService.getAllCourses();
    return ResponseEntity.ok(courseList);
}
//add
@PostMapping("/add")
    public ResponseEntity createCourse(@Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getField();
            return ResponseEntity.badRequest().body(msg);
        }

        Course savedCourse = courseService.createCourse(course);
        return ResponseEntity.status(200).body("saved successfully"+savedCourse);
    }
    //update

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@PathVariable int id, @Valid @RequestBody Course updatedCourse, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getField();
            return ResponseEntity.badRequest().body(msg);
                }

        Course course = courseService.updateCourse(id, updatedCourse);
        if (course != null) {
            return ResponseEntity.status(200).body("updated  successfully");
        } else {
            return ResponseEntity.status(400).body("id not found");
        }
    }
//delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable int id) {
        if (courseService.deleteCourse(id)) {
            return ResponseEntity.status(200).body("deleted  successfully");
        } else {
            return ResponseEntity.status(400).body("id not found");
        }
    }

    @GetMapping("/course/{id}")
    public ResponseEntity getCourseById(@PathVariable int id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.status(400).body("id not found");
        }
        return ResponseEntity.ok(course);
    }
    
    
    
}