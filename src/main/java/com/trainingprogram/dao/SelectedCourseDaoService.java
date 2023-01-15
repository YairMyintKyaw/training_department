package com.trainingprogram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trainingprogram.model.Course;
import com.trainingprogram.model.CourseRegister;
import com.trainingprogram.utils.EmailSendUtils;
import com.trainingprogram.utils.JDBCUtils;
import com.trainingprogram.utils.ReadSheetUtils;

public class SelectedCourseDaoService implements SelectedCourseDao {
	private static final String INSERT_CourseS_SQL = "INSERT INTO `training_department`.`registered_student` "
			+ "(`s_name`, `s_email`, `registered_course`, `time`, `price`) VALUES "
			+ "(?, ?, ?, ?, ?);";

	private static final String SELECT_Course_BY_ID = "select * from registered_student where id =?";

	@Override
	public void insertCourse(CourseRegister Course) throws SQLException {
		System.out.println(INSERT_CourseS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CourseS_SQL)) {
			preparedStatement.setString(1, Course.getUserName());
			preparedStatement.setString(2, Course.getEmailId());
			preparedStatement.setString(3, Course.getCourseName());
			preparedStatement.setString(4, Course.getTime());
			preparedStatement.setDouble(5, Course.getPrice());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			new EmailSendUtils().SendEmail(Course.getEmailId(), Course.getCourseName()); // after inserted send mail to
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
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Course_BY_ID);) {
			preparedStatement.setLong(1, CourseId);
			System.out.println(preparedStatement);
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

}
