package com.trainingprogram.model;

public class Course {
	String courseName;
	double price;
	

	public Course(String courseName, double price) {
		super();
		this.courseName = courseName;
		this.price = price;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	


}
