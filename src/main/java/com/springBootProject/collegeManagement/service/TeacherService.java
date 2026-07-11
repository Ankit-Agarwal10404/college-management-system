package com.springBootProject.collegeManagement.service;

import java.util.List;

import com.springBootProject.collegeManagement.dto.TeacherRequestDTO;
import com.springBootProject.collegeManagement.dto.TeacherResponseDTO;

public interface TeacherService {

	TeacherResponseDTO saveTeacher(TeacherRequestDTO dto);

    TeacherResponseDTO getTeacher(Long id);

    List<TeacherResponseDTO> getAllTeachers();

    TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO dto);

    void deleteTeacher(Long id);
}
