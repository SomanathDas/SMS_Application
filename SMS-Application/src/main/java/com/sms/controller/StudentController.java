package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sms.entity.Student;
import com.sms.service.StudentService;
import com.sms.service.UserService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public String listStudents(Model model) {
		Iterable<Student> students = studentService.getAllStudents();
		model.addAttribute("students", students);
		return "student-list";
	}

	@GetMapping("/addForm")
	public String showAddForm(Model model) {
		model.addAttribute("student", new Student());
		return "add-student-form";
	}

	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student, 
								@RequestParam("userId") int userId,
								@RequestParam("username") String username,
								@RequestParam("password") String password) {
		
		userService.saveOrUpdateUser(userId, username, password, "student");
		
		studentService.saveOrUpdateStudent(student);
		return "redirect:/students/list";
	}

	@GetMapping("/updateStudent")
	public String showUpdateForm(@RequestParam("userId") int userId, Model model) {
		Student student = studentService.getStudentById(userId);
		model.addAttribute("student", student);
		return "update-student-form";
	}

	@GetMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("userId") int userId) {
		studentService.deleteStudent(userId);
		return "redirect:/students/list";
	}
}
