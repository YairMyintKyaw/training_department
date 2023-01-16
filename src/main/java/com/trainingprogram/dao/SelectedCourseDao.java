package com.trainingprogram.dao;

import java.sql.SQLException;
import java.util.List;

import com.trainingprogram.model.Course;
import com.trainingprogram.model.CourseRegister;
import com.trainingprogram.model.RegisteredAccount;

public interface SelectedCourseDao {
	
	void insertCourse(CourseRegister Course) throws SQLException;
	CourseRegister selectCourse(int CourseId);
	List<Course> getAllCourses();
	List<String> getAllTime();
	double getCoursePrice(String courseName );

	String registerAccount(RegisteredAccount ra) throws SQLException;
	Boolean checkPassword(String mail, String password) throws SQLException;
	RegisteredAccount getAccountInfo(String mail) throws SQLException;
	List<CourseRegister> getEnrolledCourses(String mail) throws SQLException;
}
