package com.sms.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sms.entity.Attendance;
import com.sms.entity.Student;
import com.sms.service.AttendanceService;
import com.sms.service.StudentService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
		
	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/attendance-ByDate")
	public String getStudentAttendanceByDate(@RequestParam("date") String dateStr, Model model) {
	    try {
	    	if (dateStr == null || dateStr.isEmpty()) {
	            LocalDate currentDate = LocalDate.now();
	            LocalDate previousDate = currentDate.minusDays(1);
	            dateStr = previousDate.toString();
	        }
	        LocalDate date = LocalDate.parse(dateStr);
	        List<Attendance> studentAttendance = attendanceService.getAttendanceForDate(date);
	        model.addAttribute("studentAttendance", studentAttendance);
	        return "attendance-by-date";
	    } catch (DateTimeParseException e) {
	        // Handle invalid date format
	        model.addAttribute("error", "Invalid date format. Please use yyyy-MM-dd");
	        return "error-page";
	    }
	}
	
	@GetMapping("/attendance-list")
	public String getStudentList(Model model) {
		LocalDate currentDate = LocalDate.now();
        LocalDate previousDate = currentDate.minusDays(1);
        String dateStr = previousDate.toString();
        
        LocalDate date = LocalDate.parse(dateStr);
        List<Attendance> studentAttendance = attendanceService.getAttendanceForDate(date);
        model.addAttribute("studentAttendance", studentAttendance);
        return "attendance-by-date";
	}
	
	@GetMapping("/mark-attendance-form")
	public String showMarkAttendance(Model model) {
		Iterable<Student> students = studentService.getAllStudents();
		model.addAttribute("students", students);
		model.addAttribute("currentDate", LocalDate.now());
		return "mark-attendance-form";
	}
	
	@PostMapping("/mark-attendance")
	public String markAttendance(@RequestParam String attendanceDate, @RequestParam Map<String, String> selectedStudents) {
	    // Your controller logic here
		System.out.println("attendanceDate:" + attendanceDate);
		System.out.println("selectedStudents:" + selectedStudents);
		
		LocalDate date = LocalDate.parse(attendanceDate);
		System.out.println("userId:");
		
	    for (Map.Entry<String, String> entry : selectedStudents.entrySet()) {
	        String[] parts = entry.getKey().split("_");
	        System.out.println("parts:" + Arrays.toString(parts));
	        if (parts.length == 2) {
	            // Extract userId from the parts array
	            int userId = Integer.parseInt(parts[1]);
	            
	            // Extract attendanceStatus from the value of the entry
	            String attendanceStatus = entry.getValue();
	            
	            // Print userId and attendanceStatus for debugging or logging
	            System.out.println("userId:" + userId);
	            System.out.println("attendanceStatus:" + attendanceStatus);
	            
	            // Your logic to mark attendance
	            attendanceService.markAttendance(userId, date, attendanceStatus);
	        }
	    }

	    return "redirect:/attendance/attendance-ByDate?date=" + date; // Redirect to the attendance view page
	}
	
	@GetMapping("/update-attendance-form")
	public String showAttendanceForm(@RequestParam("attendanceId") int attendanceId,
									@RequestParam("attendanceDate") LocalDate date,
																	Model model) {
		System.out.println("attendanceId_1:" + attendanceId);
		Attendance attendance = attendanceService.getAttendanceById(attendanceId);
		model.addAttribute("date", date);
		model.addAttribute("attendance", attendance);
		return "update-attendance-form";
	}
	
	@PostMapping("/update-attendance")
	public String updateAttendance(@RequestParam String attendanceId, 
									@RequestParam String status,
									@RequestParam LocalDate date,
									Model model) {
		
		int id = Integer.parseInt(attendanceId);
        attendanceService.updateAttendanceStatus(id, status);
        
        // Redirect to the attendance list page
        return "redirect:/attendance/attendance-ByDate?date=" + date;
    }
	
	@GetMapping("/delete-attendance")
	public String deleteAttendance(@RequestParam("attendanceId") int attendanceId,
									@RequestParam("attendanceDate") LocalDate date) {
		attendanceService.deleteAttendance(attendanceId);
		
		return "redirect:/attendance/attendance-ByDate?date=" + date;
	}
}
