package com.example.lab7.Controller;

import com.example.lab7.Model.Course;
import com.example.lab7.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
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

    @PutMapping("/update/{id}")
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
    @PutMapping("/reserve/{course}/{capacity}")
    public ResponseEntity reserveCourse(@PathVariable String wantedCourse, @PathVariable int capacity,Course course) {
        boolean reservation = courseService.reserveSeat(course,wantedCourse, capacity);
        if (reservation) {
            return ResponseEntity.status(200).body("reserved seat successfully");
        }
        return ResponseEntity.status(400).body("reservation failed");

    }
    @GetMapping("/course/{courseName}")
    public ResponseEntity getCapacity(@PathVariable String courseName, Course course) {
        course.setCourseName(courseName);
        int capacity = courseService.getCapacityByCourse(course);
        return ResponseEntity.status(200).body(capacity);
    }
    @GetMapping("/available/{id}")
    public ResponseEntity isAvailable(@RequestParam int id) {
        boolean isAvailable = courseService.isAvailable(id);

        if (isAvailable ) {
            return ResponseEntity.status(400).body("course not found");
        }

        return ResponseEntity.status(200).body("course availible");
    }

    }
