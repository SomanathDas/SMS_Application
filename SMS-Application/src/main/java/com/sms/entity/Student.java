package com.sms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {
	
	@Id
    @Column(name = "USERID")
    private int userId;

    @Column(name = "STUDENT_NAME")
    private String studentName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int userId, String studentName, String email, String address) {
		super();
		this.userId = userId;
		this.studentName = studentName;
		this.email = email;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [userId=" + userId + ", studentName=" + studentName + ", email=" + email + ", address="
				+ address + "]";
	}
    
    
}
