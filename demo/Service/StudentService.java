package com.example.demo.Service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.Model.Student;

@Service
public class StudentService {
    private ArrayList<Student> students = new ArrayList<>();

    // Read (Get)
    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Student> getStudentById(int id) {
        ArrayList<Student> matchingStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getId() == id) {
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }

    // Create
    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    // Update
    public Student updateStudent(int id, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getId() == id) {
                students.set(i, updatedStudent);
                return updatedStudent;
            }
        }
        return null;
    }

    // Delete
    public boolean deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getId() == id) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }
     // Get all students by course
    public List<Student> getAllStudentsByCourse(String course) {
        List<Student> students = new ArrayList<>();
        for (Student student : students) {
            if (student.getCourse().equals(course)) {
                students.add(student);
            }
        }
        return students;
    }

    // Get student name by id
public String getStudentNameById(int id) {
    List<Student> students = new ArrayList<>();
    for (Student student : students) {
        if (student.getId()==id) {
            return student.getName();
        }
    }
    return null;
}
// Change student course
public Student changeStudentCourse(int id, String newCourse) {
    List<Student> students = new ArrayList<>();
    for (Student student : students) {
        if (student.getId()== id) {
            student.setCourse(newCourse);
            return student;
        }
    }
    return null;
}

// Get student by age
public ArrayList<Student> getStudentByAge(int age) {
    List<Student> students = new ArrayList<>();
    ArrayList<Student> matchingStudents = new ArrayList<>();
    for (Student student : students) {
        if (student.getAge() == age) {
            matchingStudents.add(student);
        }
    }
    return matchingStudents;
}
}