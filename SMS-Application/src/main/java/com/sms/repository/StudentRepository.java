package com.sms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sms.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>{

	Student findByStudentName(String studentName);
	
}
	