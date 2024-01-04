package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Course;
import com.sms.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int course_id) {
        return courseRepository.findById(course_id).orElse(null);
    }

    public void saveOrUpdateCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(int course_id) {
        courseRepository.deleteById(course_id);
    }

    public int getCourseCount() {
        return (int) courseRepository.count();
    }
}
