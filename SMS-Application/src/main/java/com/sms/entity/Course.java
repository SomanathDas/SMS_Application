package com.sms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "COURSES")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")  // Replace with your actual primary key column name
    private int course_id;

    @Column(name = "COURSE_NAME")  // Replace with your actual column name for courseName
    private String course_name;

    @Column(name = "DESCRIPTION")  // Replace with your actual column name for description
    private String description;
    
    public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Course(String course_name, String description) {
		super();
		this.course_name = course_name;
		this.description = description;
	}
	
	public Course(int course_id, String course_name, String description) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.description = description;
	}
	
	
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", course_name=" + course_name + ", description=" + description + "]";
	}

}
