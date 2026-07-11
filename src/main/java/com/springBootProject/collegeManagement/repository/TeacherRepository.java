package com.springBootProject.collegeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBootProject.collegeManagement.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
