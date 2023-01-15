package com.trainingprogram.dao;

import java.sql.SQLException;
import java.util.List;

import com.trainingprogram.model.Course;
import com.trainingprogram.model.CourseRegister;

public interface SelectedCourseDao {
	
	void insertCourse(CourseRegister Course) throws SQLException;
	CourseRegister selectCourse(int CourseId);
	List<Course> getAllCourses();
	List<String> getAllTime();
	double getCoursePrice(String courseName );



}
