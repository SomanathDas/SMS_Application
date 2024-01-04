package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sms.service.AttendanceService;
import com.sms.service.CourseService;
import com.sms.service.StudentService;
import com.sms.service.TeacherService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private AttendanceService attendanceService;

	@GetMapping("/admin-dashboard")
	public String adminDashboard(Model model, HttpSession session) {
		// Load counts and other data
		int teacherCount = teacherService.getTeacherCount();
		int studentCount = studentService.getStudentCount();
		int courseCount = courseService.getCourseCount();
		double attendancePercentage = attendanceService.calculateAttendancePercentage();

		String username = (String) session.getAttribute("username");
		
		System.out.println("username::" + username);
		
		// Set counts in the model
		model.addAttribute("teacherCount", teacherCount);
		model.addAttribute("studentCount", studentCount);
		model.addAttribute("courseCount", courseCount);
		model.addAttribute("attendancePercentage", attendancePercentage);
		model.addAttribute("username", username);
		// Return the admin dashboard view
		return "admin-dashboard";
	}
}
