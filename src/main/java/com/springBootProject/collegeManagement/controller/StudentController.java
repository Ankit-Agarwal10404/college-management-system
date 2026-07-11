package com.springBootProject.collegeManagement.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBootProject.collegeManagement.entity.Student;
import com.springBootProject.collegeManagement.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Student Controllers", description = "CURD operations for Student")
@RestController
@RequestMapping("/api/students")

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService service) {
        this.studentService = service;
    }

    @Operation(summary = "save student",
    		description = "creating a new Student")
    @PostMapping
    public Student saveStudent( @RequestBody Student student) {

        return studentService.saveStudent(student);

    }

    
    @Operation(summary = "Get all student",
    		description = "Geting the detail of all Students")
    @GetMapping
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();

    }

    @Operation(summary = "Get a student",
    		description = "Geting the detail of a Students using its id")

    @GetMapping("/{id}")
    public Student getStudentById(@Parameter(description = "Student ID") @PathVariable Long id) {

        return studentService.getStudentById(id);

    }

    @Operation(summary = "Update a student",
    		description = "Updating the detail of a Students using its id")
    @PutMapping("/{id}")
    public Student updateStudent(@Parameter(description = "Student id")
    							@PathVariable Long id,
                                 @RequestBody Student student) {

        return studentService.updateStudent(id, student);

    }

    @Operation(summary = "Delete a student",
    		description = "Delete a Student using its id")
    @DeleteMapping("/{id}")
    public String deleteStudent(@Parameter(description = "Student ID")
    							@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "Student Deleted Successfully";

    }
    
    @Operation(summary = "Course Assign",
    		description = "Assigning the required course to student")
    @PostMapping("/{studentId}/courses/{courseId}")
    public Student assignCourse(
    		@Parameter(description = "Student ID")
            @PathVariable Long studentId,
            @Parameter(description = "Course ID")
            @PathVariable Long courseId) {

        return studentService.assignCourse(studentId, courseId);
    }
    
    @Operation(summary = "Course Remove",
    		description = "Removing the unrequired course for the student")

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public Student removeCourse(
    		@Parameter(description = "Student ID")
            @PathVariable Long studentId,
            @Parameter(description = "Course ID")
            @PathVariable Long courseId) {

        return studentService.removeCourse(studentId, courseId);
    }

}