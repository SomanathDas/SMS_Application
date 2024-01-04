package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.sms.entity.User;
import com.sms.service.AttendanceService;
import com.sms.service.CourseService;
import com.sms.service.StudentService;
import com.sms.service.TeacherService;
import com.sms.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	
	@Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(String username, String password, String role, Model model, HttpSession session) {
        // Perform authentication using UserService
        User authenticatedUser = userService.authenticateUser(username, password);
        
        System.out.println("runned!!");
        
        if (authenticatedUser != null && authenticatedUser.getRole().equals(role)) {
            // Update counts
            int teacherCount = teacherService.getTeacherCount();
            int studentCount = studentService.getStudentCount();
            int courseCount = courseService.getCourseCount();
            double attendancePercentage = attendanceService.calculateAttendancePercentage();

            // Set counts in session or request scope
            model.addAttribute("teacherCount", teacherCount);
            model.addAttribute("studentCount", studentCount);
            model.addAttribute("courseCount", courseCount);
            model.addAttribute("attendancePercentage", attendancePercentage);

            model.addAttribute("username", username);
            
         // Store user role and username in session
            session.setAttribute("username", username);
            session.setAttribute("userRole", role);
            
            System.out.println("teacherCount:" +teacherCount);
            System.out.println("studentCount:" +studentCount);
            System.out.println("courseCount:" +courseCount);
            System.out.println("attendancePercentage:" +attendancePercentage);
            System.out.println("username:" +username);
            
            // Redirect based on the user's role
            switch (role) {
                case "admin":
                    return "admin-dashboard";
                case "student":
                    return "student-dashboard";
                case "teacher":
                    return "teacher-dashboard";
            }
        } else {
            // Handle invalid credentials
            model.addAttribute("loginError", "Invalid credentials");
            return "login";
        }
		return "login";
    }
}
