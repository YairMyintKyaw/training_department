package com.trainingprogram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trainingprogram.model.Course;
import com.trainingprogram.model.CourseRegister;
import com.trainingprogram.model.RegisteredAccount;
import com.trainingprogram.utils.EmailSendUtils;
import com.trainingprogram.utils.JDBCUtils;
import com.trainingprogram.utils.ReadSheetUtils;

public class SelectedCourseDaoService implements SelectedCourseDao {


	@Override
	public void insertCourse(CourseRegister Course) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `training_department`.`registered_student` "
						+ "(`s_name`, `s_email`, `registered_course`, `time`, `price`) VALUES " + "(?, ?, ?, ?, ?);")) {
			preparedStatement.setString(1, Course.getUserName());
			preparedStatement.setString(2, Course.getEmailId());
			preparedStatement.setString(3, Course.getCourseName());
			preparedStatement.setString(4, Course.getTime());
			preparedStatement.setDouble(5, Course.getPrice());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			new EmailSendUtils().SendEmail(Course); // after inserted send mail to
													// particular users
		} catch (SQLException exception) {
			System.out.print(exception);
		}
	}

	@Override
	public CourseRegister selectCourse(int CourseId) {
		CourseRegister Course = null;
		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from registered_student where id =?");) {
			preparedStatement.setLong(1, CourseId);

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.

			while (rs.next()) {
				int id = rs.getInt("s_ID");
				String username = rs.getString("s_name");
				String emailid = rs.getString("s_email");
				String coursename = rs.getString("registered_course");
				double price = rs.getDouble("price");
				String time = rs.getString("time");
				// LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				// int status = rs.getInt("status");
				Course = new CourseRegister(id, username, emailid, coursename, price, time);
			}
		} catch (SQLException exception) {
			System.out.println(exception);
		}
		return Course;
	}

	@Override
	public List<Course> getAllCourses() {

		return ReadSheetUtils.ReadCourse("D:\\CIA\\CIA.xlsx"); // calling excel sheet Path..
	}

	@Override
	public List<String> getAllTime() {

		return ReadSheetUtils.ReadTime("D:\\CIA\\CIA.xlsx");

	}

	@Override
	public double getCoursePrice(String courseName) // get the price from excel sheet.
	{
		List<Course> listerCourse = getAllCourses();

		for (Course currentCourse : listerCourse) {
			if (currentCourse.getCourseName().equalsIgnoreCase(courseName)) // check courseName
				return currentCourse.getPrice();
		}
		return 0;
	}

	@Override
	public String registerAccount(RegisteredAccount ra) throws SQLException {

		Connection connection = JDBCUtils.getConnection();
		//check if the mail is already exist
		String sql1 = "Select * from `training_department`.`registered_account` where email=?";
		PreparedStatement PsForMail = connection.prepareStatement(sql1);
		PsForMail.setString(1, ra.getEmail());
		ResultSet rs = PsForMail.executeQuery();
		while(rs.next()) {
			if(rs.getString("name")!=null) {
				return "Mail Already Exists";
			}
		}
		String sql2 = "INSERT INTO `training_department`.`registered_account` (`name`, `password`, `email`) VALUES (?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql2);
		ps.setString(1, ra.getName());
		ps.setString(2, ra.getPassword());
		ps.setString(3, ra.getEmail());
		ps.executeUpdate();
		return "Created account";
	}

	@Override
	public Boolean checkPassword(String mail, String password) throws SQLException {
		Boolean correctPassword = false;
		Connection connection = JDBCUtils.getConnection();
		String sql = "select password from registered_account where email=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, mail);
		ResultSet rs = ps.executeQuery();
		
		String pw=null;
		while(rs.next()) {
			pw = rs.getString("password");
		}
		if(pw!=null && pw.equals(password)) {
			correctPassword=true;
		}
		
		return correctPassword;
	}

	@Override
	public RegisteredAccount getAccountInfo(String mail) throws SQLException {

		Connection connection = JDBCUtils.getConnection();
		String sql = "select name from registered_account where email=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, mail);
		ResultSet rs = ps.executeQuery();
		String name=null;
		while(rs.next()) {
			name = rs.getString("name");
		}
		
		RegisteredAccount ra = new RegisteredAccount(name, mail);
		
		return ra;
		
		
	}

	@Override
	public List<CourseRegister> getEnrolledCourses(String mail) throws SQLException {
		List<CourseRegister> courseList = new ArrayList<>();
		
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from registered_student where s_email=?;";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, mail);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String courseName = rs.getString("registered_course");
			String time = rs.getString("time");
			CourseRegister course = new CourseRegister(courseName, time);
			courseList.add(course);
		}
		
		return courseList;
		
	}
	
	
	
	
	
	
	

	

}
