package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.entity.Student;
import com.sms.repository.AttendanceRepository;
import com.sms.repository.StudentRepository;
import com.sms.repository.UserRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private AttendanceRepository attendanceRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	public Iterable<Student> getAllStudents(){
		return studentRepository.findAll();
	}

    public Student getStudentById(int userId) {
        return studentRepository.findById(userId).orElse(null);
    }	

    public void saveOrUpdateStudent(Student student) {
        // Business logic validation (e.g., check if the student name is not empty)
        if (student.getStudentName() == null || student.getStudentName().trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }

        studentRepository.save(student);
    }
    
    @Transactional
    public void deleteStudent(int userId) {
    	System.out.println("userId:"+userId);
    	attendanceRepository.deleteById(userId);
    	System.out.println("userId:");
    	studentRepository.deleteById(userId);
    	System.out.println("userId:");
//    	userRepository.deleteById(userId);
//    	System.out.println("userId:");
        
    }

    public int getStudentCount() {
        return (int) studentRepository.count();
    }
}
