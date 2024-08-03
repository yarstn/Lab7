package com.example.lab7.Model;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Student {
    @NotNull(message = "ID cannot be empty")
    private int id;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;
    @NotEmpty(message = "Course cannot be empty")
    @Size(min = 3, max = 100, message = "Course must be between 3 and 100 characters")
    private String course;
    @NotNull(message = "Age cannot be null")
    @Min(value = 5, message = "Age must be at least 5")
    @Max(value = 40, message = "Age must not exceed 40")
    private int age;

}
