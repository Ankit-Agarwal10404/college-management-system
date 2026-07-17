package com.springBootProject.collegeManagement.service;
import java.util.List;

import com.springBootProject.collegeManagement.dto.course.CourseRequestDTO;
import com.springBootProject.collegeManagement.dto.course.CourseResponseDTO;
import com.springBootProject.collegeManagement.entity.Course;

public interface CourseService {

    CourseResponseDTO saveCourse(CourseRequestDTO courseRequestDTO);

    List<CourseResponseDTO> getAllCourses();

    CourseResponseDTO getCourseById(Long id);

    CourseResponseDTO updateCourse(Long id, CourseRequestDTO course);

    void deleteCourse(Long id);
    
    public CourseResponseDTO assignTeacher(Long courseId, Long teacherId);
    public CourseResponseDTO removeTeacher(Long courseId) ;
    
    public List<CourseResponseDTO> getTeacherCourses(Long teacherId);

}