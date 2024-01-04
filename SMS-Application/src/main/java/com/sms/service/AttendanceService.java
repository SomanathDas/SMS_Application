package com.sms.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Attendance;
import com.sms.entity.Student;
import com.sms.repository.AttendanceRepository;
import com.sms.repository.StudentRepository;

@Service
public class AttendanceService {

	@Autowired
    private AttendanceRepository attendanceRepository;
	
	@Autowired
    private StudentRepository studentRepository;

    public List<Attendance> getAttendanceForDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    public void markAttendance(int userId, LocalDate date, String status) {
        Attendance attendance = new Attendance();
        attendance.setUserId(userId);
        attendance.setDate(date);
        attendance.setStatus(status);
        attendanceRepository.save(attendance);
    }

    public void updateAttendanceStatus(int attendanceId, String newStatus) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + attendanceId));
        attendance.setStatus(newStatus);
        attendanceRepository.save(attendance);
    }

    public Attendance getAttendanceById(int attendanceId) {
        return attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + attendanceId));
    }

    public void deleteAttendance(int attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }

    public List<Attendance> getAttendanceForStudent(int userId) {
        return attendanceRepository.findByUserId(userId);
    }

    public double calculateAttendancePercentage() {
        List<Attendance> allAttendances = attendanceRepository.findAll();

        if (allAttendances.isEmpty()) {
            return 0.0; // Default to 0% if there are no attendance records
        }

        long totalPresent = allAttendances.stream().filter(attendance -> "Present".equals(attendance.getStatus())).count();
        System.out.println("totalPresent:" + totalPresent);
        long totalAttendances = allAttendances.size();
        System.out.println("totalAttendances:" + totalAttendances);

        double attendancePercentage = ((double) totalPresent / totalAttendances) * 100.0;
        
     // Format the percentage value with two digits after the decimal point
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(attendancePercentage));
    }
    

    public List<Attendance> getAttendanceByStudentName(String studentName) {
        // Assuming there's a field like userId in your Attendance entity
        List<Attendance> attendances = attendanceRepository.findByUserId(getUserIdByStudentName(studentName));
        return attendances;
    }

    private int getUserIdByStudentName(String studentName) {
        // Fetch the student by name and return the user ID
        Student student = studentRepository.findByStudentName(studentName);
        return student != null ? student.getUserId() : -1; // Handle the case when student is not found
    }
    
    public boolean isAttendanceMarked(int attendanceId, LocalDate date) {
    	return attendanceRepository.existsByAttendanceIdAndDate(attendanceId, date);
    }
}
