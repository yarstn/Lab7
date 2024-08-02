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
public class Course {
  
    @NotNull(message = "Course ID cannot be null")
    private int id;

  
    @NotEmpty(message = "Course name cannot be empty")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String courseName;

    @NotNull(message = "Course capacity cannot be null")
    @Min(value = 1, message = "Course capacity must be at least 1")
    @Max(value = 500, message = "Course capacity must not exceed 500")
    private int capacity;

}