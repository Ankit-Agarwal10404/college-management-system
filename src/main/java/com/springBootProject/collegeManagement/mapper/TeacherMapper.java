package com.springBootProject.collegeManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.springBootProject.collegeManagement.dto.TeacherRequestDTO;
import com.springBootProject.collegeManagement.dto.TeacherResponseDTO;
import com.springBootProject.collegeManagement.entity.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

	Teacher toEntity(TeacherRequestDTO requestDTO);
	TeacherResponseDTO toDTO(Teacher teacher);
	
	List<TeacherResponseDTO> toDTOList(List<Teacher> teachers);
	
}
