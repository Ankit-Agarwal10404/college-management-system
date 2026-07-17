package com.springBootProject.collegeManagement.serviceImp;
import org.springframework.stereotype.Service;

import com.springBootProject.collegeManagement.dto.student.StudentRequestDTO;
import com.springBootProject.collegeManagement.dto.student.StudentResponseDTO;
import com.springBootProject.collegeManagement.entity.Course;
import com.springBootProject.collegeManagement.entity.Student;
import com.springBootProject.collegeManagement.exception.ResourceNotFoundException;
import com.springBootProject.collegeManagement.mapper.StudentMapper;
import com.springBootProject.collegeManagement.repository.CourseRepository;
import com.springBootProject.collegeManagement.repository.StudentRepository;
import com.springBootProject.collegeManagement.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;

    public StudentServiceImp(StudentRepository repository, CourseRepository courseRepository, StudentMapper studentMapper) {
        this.studentRepository = repository;
        this.courseRepository=courseRepository;
        this.studentMapper=studentMapper;
    }

    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO) {
    	Student student = studentMapper.toEntity(studentRequestDTO);
         studentRepository.save(student);
         return studentMapper.toDTO(student);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students= studentRepository.findAll();
        return studentMapper.toDTOList(students);
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student= studentRepository.findById(id)
        		.orElseThrow(()->new ResourceNotFoundException("student not found"));
        
        return studentMapper.toDTO(student);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO student) {

        Student existing = studentRepository.findById(id)
        		.orElseThrow(()->new ResourceNotFoundException("student not found"));
        	
        	//updating existing student
            existing.setFirstName(student.getFirstName());
            existing.setLastName(student.getLastName());
            existing.setEmail(student.getEmail());
            existing.setPercentage(student.getPercentage());

            Student existingSaved= studentRepository.save(existing);
            return studentMapper.toDTO(existingSaved);

        
    }

    @Override
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);

    }

	@Override
	public StudentResponseDTO assignCourse(Long studentId, Long courseId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(()-> new ResourceNotFoundException("student not found"));
		
		Course course = courseRepository.findById(courseId)
				.orElseThrow(()-> new ResourceNotFoundException("course not found"));
		
		student.getCourses().add(course);
		course.getStudents().add(student);	
		
		 Student savedStudent=studentRepository.save(student);
		 return studentMapper.toDTO(savedStudent);
	}


	@Override
	public StudentResponseDTO removeCourse(Long studentId, Long courseId) {

	    Student student = studentRepository.findById(studentId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Student not found"));

	    Course course = courseRepository.findById(courseId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Course not found"));

	    student.getCourses().remove(course);
	    course.getStudents().remove(student);

	    Student savedStudent = studentRepository.save(student);
	    
	    return studentMapper.toDTO(savedStudent);
	}

}