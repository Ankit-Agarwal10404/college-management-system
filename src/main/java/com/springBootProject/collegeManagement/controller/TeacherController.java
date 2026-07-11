package com.springBootProject.collegeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBootProject.collegeManagement.dto.TeacherRequestDTO;
import com.springBootProject.collegeManagement.dto.TeacherResponseDTO;
import com.springBootProject.collegeManagement.service.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teachers")
@Validated
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public TeacherResponseDTO saveTeacher(@Valid @RequestBody TeacherRequestDTO requestDTO) {
        return teacherService.saveTeacher(requestDTO);
    }

    @GetMapping("/{id}")
    public TeacherResponseDTO getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    @GetMapping
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PutMapping("/{id}")
    public TeacherResponseDTO updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody TeacherRequestDTO requestDTO) {

        return teacherService.updateTeacher(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Long id) {

        teacherService.deleteTeacher(id);
        return "Teacher deleted successfully.";
    }

}