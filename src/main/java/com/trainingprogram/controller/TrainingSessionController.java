package com.trainingprogram.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.trainingprogram.dao.SelectedCourseDao;
import com.trainingprogram.model.Course;
import com.trainingprogram.model.CourseRegister;
import com.trainingprogram.model.RegisteredAccount;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TrainingSessionController {

	private SelectedCourseDao courseDAO;

	TrainingSessionController(SelectedCourseDao _courseDAO) {
		courseDAO = _courseDAO;
	}

	public void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		List<Course> listCourse = courseDAO.getAllCourses();// get all courses from excel sheet from Dao assign to array
															// list
		List<String> timeList = courseDAO.getAllTime();

		request.setAttribute("listCourse", listCourse); // setAttribute
		request.setAttribute("time", timeList); // setAttribute
		request.setAttribute("name", name);
		request.setAttribute("email", email);

		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");// calling course-register.jsp
		dispatcher.forward(request, response);
	}

	public void insertCourse(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String username = request.getParameter("name");
		String emailid = request.getParameter("email");
		String coursename = request.getParameter("course");
		String time = request.getParameter("time");

		double price = courseDAO.getCoursePrice(coursename); // get price from excel sheet
		CourseRegister newCourse = new CourseRegister(username, emailid, coursename, price, time);
		courseDAO.insertCourse(newCourse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("thanks.jsp");
		request.setAttribute("Course", newCourse);
		dispatcher.forward(request, response);
	}

	public void registerForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String username = request.getParameter("name");
		String emailid = request.getParameter("email");
		String password = request.getParameter("password");

		RegisteredAccount ra = new RegisteredAccount(username, emailid, password);
		if (courseDAO.registerAccount(ra).equals("Mail Already Exists")) {
			request.setAttribute("Message", "Mail Already Exists");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Signup.jsp");// calling course-register.jsp
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("login.jsp");
		}
	}

	public void goToHome(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String mail = request.getParameter("email");
		String password = request.getParameter("password");

		Boolean isCorrectPassword = courseDAO.checkPassword(mail, password);
		System.out.println(isCorrectPassword);
		if (isCorrectPassword) {
			RegisteredAccount ra = courseDAO.getAccountInfo(mail);
			request.setAttribute("AccountInfo", ra);
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void showEnrolledCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String mail = request.getParameter("email");
		List<CourseRegister> courseRegisters = courseDAO.getEnrolledCourses(mail);
		request.setAttribute("registeredCourses", courseRegisters);
		RequestDispatcher dispatcher = request.getRequestDispatcher("enrolledCourse.jsp");
		dispatcher.forward(request, response);
	}

}
