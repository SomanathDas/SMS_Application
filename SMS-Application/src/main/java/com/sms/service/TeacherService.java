package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Teacher;
import com.sms.repository.TeacherRepository;
import com.sms.repository.UserRepository;

@Service
public class TeacherService {

	@Autowired
    private TeacherRepository teacherRepository;
	
	@Autowired
	private UserRepository userRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(int userId) {
        return teacherRepository.findById(userId).orElse(null);
    }

    public void saveOrUpdateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(int userId) {
        teacherRepository.deleteById(userId);
        userRepository.deleteById(userId);
    }

    public int getTeacherCount() {
        return (int) teacherRepository.count();
    }
}