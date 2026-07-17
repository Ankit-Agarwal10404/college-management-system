package com.springBootProject.collegeManagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.springBootProject.collegeManagement.dto.course.CourseRequestDTO;
import com.springBootProject.collegeManagement.dto.course.CourseResponseDTO;
import com.springBootProject.collegeManagement.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	CourseResponseDTO toDTO(Course course);
	Course toEntity(CourseRequestDTO courseRequestDTO);
	List<CourseResponseDTO> toDTOList(List<Course> courses);
}
