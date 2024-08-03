package com.example.lab7.Service;
import com.example.lab7.Model.Student;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    ArrayList<Student> student = new ArrayList<>();

  public  ArrayList<Student> getStudents(){

      return student;
  }
    public void addStudent(Student student) {
      this.student.add(student);
    }
  public boolean updateStudent(int id,Student student){
      for(int i=0;i<this.student.size();i++){
          if(this.student.get(i).getId()==id){
              this.student.set(i,student);
              return true;
          }
      }
      return false;
  }
  public boolean deleteStudent(int id){
      for(int i=0;i<this.student.size();i++){
          if(this.student.get(i).getId()==id){
              this.student.remove(i);
              return true;
          }
      }
      return false;
  }
    public ArrayList<Student> getStudentByCourse(String course) {
        ArrayList<Student> students = new ArrayList<>();
        for (Student student : students) {
            if (student.getCourse().equals(course)) {
                students.add(student);
            }
        }
        return students;
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
    public ArrayList<Student> getStudentNameById(int id) {
        List<Student> students = new ArrayList<>();
        for (Student student : students) {
            if (student.getId()==id) {
                students.add(student);
            }
        }
        return student;
    }

    public boolean changeStudentCourse(int id, String course) {
      for(int i=0;i<this.student.size();i++){
          if(this.student.get(i).getId()==id){
              this.student.get(i).setCourse(course);
              return true;
          }
      }
      return false;

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
