package com.example.demo.Model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "id cannot be empty")
    private int id;

    // Student's name
 
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;


    @NotEmpty(message = "Course cannot be empty")
    @Size(min = 3, max = 100, message = "Course must be between 3 and 100 characters")
    private String course;

    @NotNull(message = "Age cannot be null")
    @Min(value = 5, message = "Age must be at least 5")
    @Max(value = 100, message = "Age must not exceed 100")
    private int age;

}