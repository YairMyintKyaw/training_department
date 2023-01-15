package com.trainingprogram.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.trainingprogram.dao.SelectedCourseDao;
import com.trainingprogram.model.Course;
import com.trainingprogram.model.CourseRegister;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TrainingSessionController {
	
	private SelectedCourseDao courseDAO;
	
	TrainingSessionController(SelectedCourseDao _courseDAO){
		courseDAO=_courseDAO;
	}
	

	public void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Course> listCourse = courseDAO.getAllCourses();// get all courses from excel sheet from Dao assign to array list
		List<String> timeList = courseDAO.getAllTime();
		System.out.println(timeList.size());
		request.setAttribute("listCourse", listCourse);  //setAttribute 
		request.setAttribute("time", timeList);  //setAttribute 
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");//calling course-register.jsp
		dispatcher.forward(request, response);
	}


	public void insertCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String username = request.getParameter("name");
		String emailid = request.getParameter("email");
		String coursename = request.getParameter("course");
		String time= request.getParameter("time");
		
		double price = courseDAO.getCoursePrice(coursename); // get price from excel sheet 
	    CourseRegister newCourse = new CourseRegister( username, emailid, coursename, price,time);
		courseDAO.insertCourse(newCourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("thanks.jsp");
		request.setAttribute("Course", newCourse);
		dispatcher.forward(request, response);
	}



}
