package com.sms.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ATTENDANCE")
public class Attendance {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTENDANCE_ID")
	private int attendanceId;
	
	@Column(name = "USERID")
	private int userId;
	
	@Column(name = "ATTENDANCE_DATE")
	private LocalDate date;
	
	@Column(name = "STATUS")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "USERID", insertable = false, updatable = false)
	private Student student;
	
	
	public Attendance() {
    }

	public Attendance(int userId, LocalDate date, String status) {
		super();
		this.userId = userId;
		this.date = date;
		this.status = status;
	}

	public Attendance(int attendanceId, int userId, LocalDate date, String status) {
		super();
		this.attendanceId = attendanceId;
		this.userId = userId;
		this.date = date;
		this.status = status;
	}

	public int getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", userId=" + userId + ", date=" + date + ", status="
				+ status + "]";
	}
}
