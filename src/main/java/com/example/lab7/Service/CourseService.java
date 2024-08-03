package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    //reserve seat
    public boolean reserveSeat(Course course, String wantedCourse, int capacity) {
        if (course.getCourseName().equals(wantedCourse)) {
            int currentCapacity = course.getCapacity();
            if (currentCapacity >= capacity) {
                course.setCapacity(currentCapacity - capacity);
                courses.add(course);
                return true;
            }
        }
        return false;
    }
    public int getCapacityByCourse(Course course) {
        for (int i = 0; i < courses.size(); i++) {
            Course course1 = courses.get(i);
            if (course1.getCourseName().equals(course.getCourseName())) {
                return course1.getCapacity();
            }
        }
        return 0;
    }
    public boolean isAvailable(int id) {
        for (Course course : courses) {
            if (course.getId() == id && course.getCapacity() > 0) {
                return true;
            }
        }
        return false;
    }

}
