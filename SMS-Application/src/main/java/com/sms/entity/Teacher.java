package com.sms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "teachers")
public class Teacher {
	
	@Id
    @Column(name = "USERID")  // Replace with your actual primary key column name
    private int userId;

    @Column(name = "NAME")  // Replace with your actual column name for name
    private String name;

    @Column(name = "EMAIL")  // Replace with your actual column name for email
    private String email;

    @Column(name = "COURSES")  // Replace with your actual column name for courses
    private String courses;
    
    public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Teacher(String name, String email, String courses) {
		super();
		this.name = name;
		this.email = email;
		this.courses = courses;
	}


	public Teacher(int userId, String name, String email, String courses) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.courses = courses;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCourses() {
		return courses;
	}


	public void setCourses(String courses) {
		this.courses = courses;
	}


	@Override
	public String toString() {
		return "Teacher [userId=" + userId + ", name=" + name + ", email=" + email + ", courses=" + courses + "]";
	}
}
