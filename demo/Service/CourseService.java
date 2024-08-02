package com.example.demo.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.Model.Course;

@Service
public class CourseService {
    private ArrayList<Course> courses = new ArrayList<>();



    // Read (Get)
    public ArrayList<Course> getAllCourses() {
        return courses;
    }
      // add
      public Course createCourse(Course newCourse) {
        courses.add(newCourse);
        return newCourse;
    }


 // Update
public Course updateCourse(int id, Course updatedCourse) {
    for (int i = 0; i < courses.size(); i++) {
        Course course = courses.get(i);
        if (course.getId() == id) {
            course.setCourseName(updatedCourse.getCourseName());
            course.setCapacity(updatedCourse.getCapacity());
            return course;
        }
    }
    return null;
}
   // Delete
public boolean deleteCourse(int id) {
    for (int i = 0; i < courses.size(); i++) {
        Course course = courses.get(i);
        if (course.getId() == id) {
            courses.remove(i);
            return true;
        }
    }
    return false;
}
//get course by id
public Course getCourseById(int id) {
    return courses.get(id);
}
//enroll student
public boolean enrollStudent(int studentId, int courseId) {
    Course course = courses.get(courseId);
    if (course == null) {
        return false;
    }

    List<Integer> enrolledStudents = studentEnrollments(courseId, new ArrayList<>());
    if (!enrolledStudents.contains(studentId)) {
        enrolledStudents.add(studentId);
        course.addStudent(studentId);
        studentEnrollments.put(courseId, enrolledStudents);
        return true;
    }
    return false;

}
}