package com.trainingprogram.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.trainingprogram.dao.SelectedCourseDao;
import com.trainingprogram.dao.SelectedCourseDaoService;
import com.trainingprogram.model.Course;
import com.trainingprogram.model.CourseRegister;

@WebServlet("/")
public class TrainingSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SelectedCourseDao courseDAO = new SelectedCourseDaoService();;
	private TrainingSessionController  sessionController =new TrainingSessionController(courseDAO);
 
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				sessionController.showNewForm(request, response);
				break;
			case "/insert":
				sessionController.insertCourse(request,response);;
				break;
			case "/register":
				sessionController.registerForm(request, response);
				break;
			case "/home":
				sessionController.goToHome(request, response);
				break;
			case "/enrolledCourse":
				sessionController.showEnrolledCourse(request,response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Course Auto-generated method stub
		doGet(request,response);
			}


	

}
