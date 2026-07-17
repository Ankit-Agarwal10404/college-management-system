package com.springBootProject.collegeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBootProject.collegeManagement.dto.course.CourseResponseDTO;
import com.springBootProject.collegeManagement.dto.teacher.TeacherRequestDTO;
import com.springBootProject.collegeManagement.dto.teacher.TeacherResponseDTO;
import com.springBootProject.collegeManagement.entity.Course;
import com.springBootProject.collegeManagement.service.CourseService;
import com.springBootProject.collegeManagement.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Teacher Controllers", description = "CURD operations for Teacher")
@RestController
@RequestMapping("/teachers")
@Validated
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private CourseService courseService;

    
    @Operation(summary = "save teacher",
    		description = "creating a new teacher")
    @PostMapping
    public TeacherResponseDTO saveTeacher(@Valid @RequestBody TeacherRequestDTO requestDTO) {
        return teacherService.saveTeacher(requestDTO);
    }

    @Operation(summary = "Get teacher by id",
    		description = "Getting Teacher buy using Teacher id")
    @GetMapping("/{id}")
    public TeacherResponseDTO getTeacherById(@Parameter(description = "Teacher ID")
    														@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    
    @Operation(summary = "Get All Teachers",
    		description = "Getting all Teachers details")
    @GetMapping
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    
    @Operation(summary = "Update Teacher",
    		description = "Updating Teacher using Teacher id")
    @PutMapping("/{id}")
    public TeacherResponseDTO updateTeacher(
    		@Parameter(description = "Teacher ID")
            @PathVariable Long id,
            @Valid @RequestBody TeacherRequestDTO requestDTO) {

        return teacherService.updateTeacher(id, requestDTO);
    }
    
    
    @Operation(summary = "Delete Teacher",
    		description = "Deleting Teacher details using Teacher id")

    @DeleteMapping("/{id}")
    public String deleteTeacher(@Parameter(description = "Teacher ID")
    											@PathVariable Long id) {

        teacherService.deleteTeacher(id);
        return "Teacher deleted successfully.";
    }
    
    
    @Operation(summary = "Get Courses for Teacher",
    		description = "Used to get all the courses assigned to the teacher")
    @GetMapping("/teachers/{teacherId}/courses")
	public ResponseEntity<List<CourseResponseDTO>> getTeacherCourses(
															@Parameter(description = "Teacher ID")
	        												@PathVariable Long teacherId) {

	    return ResponseEntity.ok(courseService.getTeacherCourses(teacherId));
	}

}