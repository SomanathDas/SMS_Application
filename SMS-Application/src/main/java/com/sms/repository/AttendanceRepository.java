package com.sms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sms.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	
	List<Attendance> findByDate(LocalDate date);

    List<Attendance> findByUserId(int userId);

    // Custom query method to check the existence of attendance
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Attendance a " +
 	       "WHERE a.attendanceId = :attendanceId AND a.date = :date")
	boolean existsByAttendanceIdAndDate(int attendanceId, LocalDate date);
     
}
