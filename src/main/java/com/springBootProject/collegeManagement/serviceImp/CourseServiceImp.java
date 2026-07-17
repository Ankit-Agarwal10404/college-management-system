package com.springBootProject.collegeManagement.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springBootProject.collegeManagement.dto.course.CourseRequestDTO;
import com.springBootProject.collegeManagement.dto.course.CourseResponseDTO;
import com.springBootProject.collegeManagement.entity.Course;
import com.springBootProject.collegeManagement.entity.Teacher;
import com.springBootProject.collegeManagement.exception.ResourceNotFoundException;
import com.springBootProject.collegeManagement.mapper.CourseMapper;
import com.springBootProject.collegeManagement.repository.CourseRepository;
import com.springBootProject.collegeManagement.repository.TeacherRepository;
import com.springBootProject.collegeManagement.service.CourseService;

@Service
public class CourseServiceImp implements CourseService{

	private final CourseRepository courseRepository;
	private final TeacherRepository teacherRepository;
	private final CourseMapper courseMapper;
	
	public CourseServiceImp(CourseRepository courseRepository, TeacherRepository teacherRepository, CourseMapper courseMapper) {
		// TODO Auto-generated constructor stub
		this.courseMapper = courseMapper;
		this.courseRepository=courseRepository;
		this.teacherRepository=teacherRepository;
	}
	public CourseResponseDTO saveCourse(CourseRequestDTO courseRequestDTO) {
		Course course = courseMapper.toEntity(courseRequestDTO);
		courseRepository.save(course);
		return courseMapper.toDTO(course);
	}

	@Override
	public List<CourseResponseDTO> getAllCourses() {
		List<Course> courses= courseRepository.findAll();
		return courseMapper.toDTOList(courses);
	}

	@Override
	public CourseResponseDTO getCourseById(Long id) {
		Course course= courseRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("course not found"));
		return courseMapper.toDTO(course);
	}

	@Override
	public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO) {
		Course c = courseRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("course not found"));
	
			c.setCourseName(courseRequestDTO.getCourseName());
			c.setDuration(courseRequestDTO.getDuration());
			c.setCredits(courseRequestDTO.getCredits());
			c.setFees(courseRequestDTO.getFees());
			courseRepository.save(c);
			
			return courseMapper.toDTO(c);
	}

	@Override
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
		
	}
	
	@Override
	public CourseResponseDTO assignTeacher(Long courseId, Long teacherId) {

	    Course course = courseRepository.findById(courseId)
	    		.orElseThrow(()-> new ResourceNotFoundException("course not found"));
	            
	    Teacher teacher = teacherRepository.findById(teacherId)
	    		.orElseThrow(()-> new ResourceNotFoundException("course not found"));

	    course.setTeacher(teacher);

	    Course savedCourse= courseRepository.save(course);
	    
	    return courseMapper.toDTO(savedCourse);
	}

	@Override
	public CourseResponseDTO removeTeacher(Long courseId) {

	    Course course = courseRepository.findById(courseId)
	    		.orElseThrow(()-> new ResourceNotFoundException("course not found"));

	    course.setTeacher(null);

	    Course savedCourse=courseRepository.save(course);
	    
	    return courseMapper.toDTO(savedCourse);
	}
	
	@Override
	public List<CourseResponseDTO> getTeacherCourses(Long teacherId) {

	    Teacher teacher = teacherRepository.findById(teacherId)
	    		.orElseThrow(()-> new ResourceNotFoundException("course not found"));
	    return courseMapper.toDTOList(teacher.getCourses());
	}
}
