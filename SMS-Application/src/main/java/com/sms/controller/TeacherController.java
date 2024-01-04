package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sms.entity.Course;
import com.sms.entity.Teacher;
import com.sms.service.CourseService;
import com.sms.service.TeacherService;
import com.sms.service.UserService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public String listTeachers(Model model) {
		Iterable<Teacher> teachers = teacherService.getAllTeachers();
		model.addAttribute("teachers", teachers);
		return "teacher-list";
	}
	
	@GetMapping("/addForm")
	public String showAddForm(Model model) {
		model.addAttribute("teachers", new Teacher());
		Iterable<Course> courses = courseService.getAllCourses();
		model.addAttribute("courses", courses);
		return "add-teacher-form";
	}
	
	@PostMapping("/saveTeacher")
	public String saveTeacher(@ModelAttribute("teacher") Teacher teacher,
								@RequestParam("userId") int userId,
								@RequestParam("username") String username,
								@RequestParam("password") String password) {
		
		userService.saveOrUpdateUser(userId, username, password, "teacher");
		teacherService.saveOrUpdateTeacher(teacher);
		return "redirect:/teachers/list";
	}
	
	@GetMapping("/updateTeacher")
	public String showUpdateForm(@RequestParam("userId") int userId, Model model) {
		Teacher teacher = teacherService.getTeacherById(userId);
		model.addAttribute("teacher", teacher);
		return "update-teacher-form";
	}
	
	@GetMapping("/deleteTeacher")
	public String deleteTeacher(@RequestParam("userId") int userId) {
		teacherService.deleteTeacher(userId);
		return "redirect:teachers/list";
	}
}