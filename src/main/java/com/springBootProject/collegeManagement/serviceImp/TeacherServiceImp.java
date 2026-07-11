package com.springBootProject.collegeManagement.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBootProject.collegeManagement.dto.TeacherRequestDTO;
import com.springBootProject.collegeManagement.dto.TeacherResponseDTO;
import com.springBootProject.collegeManagement.entity.Teacher;
import com.springBootProject.collegeManagement.mapper.TeacherMapper;
import com.springBootProject.collegeManagement.repository.TeacherRepository;
import com.springBootProject.collegeManagement.service.TeacherService;

@Service
public class TeacherServiceImp implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public TeacherResponseDTO saveTeacher(TeacherRequestDTO dto) {

        Teacher teacher = teacherMapper.toEntity(dto);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return teacherMapper.toDTO(savedTeacher);
    }

    @Override
    public TeacherResponseDTO getTeacher(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        return teacherMapper.toDTO(teacher);
    }

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {

        List<Teacher> teachers = teacherRepository.findAll();

        return teacherMapper.toDTOList(teachers);
    }

    @Override
    public TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO dto) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setPhone(dto.getPhone());
        teacher.setDepartment(dto.getDepartment());
        teacher.setSalary(dto.getSalary());
        teacher.setExperience(dto.getExperience());

        Teacher updatedTeacher = teacherRepository.save(teacher);

        return teacherMapper.toDTO(updatedTeacher);
    }

    @Override
    public void deleteTeacher(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        teacherRepository.delete(teacher);
    }

}