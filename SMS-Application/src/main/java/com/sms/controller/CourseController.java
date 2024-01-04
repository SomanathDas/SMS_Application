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
import com.sms.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public String listCourses(Model model) {
        Iterable<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course-list";
    }

    @GetMapping("/addForm")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course-form";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveOrUpdateCourse(course);
        return "redirect:/courses/list";
    }

    @GetMapping("/updateCourse") 
    public String showUpdateForm(@RequestParam("courseId") int courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "update-course-form";
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") int courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/courses/list";
    }
}
