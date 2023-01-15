package com.trainingprogram.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.trainingprogram.model.Course;

import java.io.File;

public class ReadSheetUtils {

	public static List<Course> ReadCourse(String path) {
		List<Course> courseList = new ArrayList<Course>(); // Array list
		try {
			File file = new File(path);
			// creating a new file instance
			FileInputStream fis = new FileInputStream(file);
			// obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			// creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator();
			int count = 0;
			// iterating over excel file
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				String coursename = "";
				double price = 0;
				count = count + 1;
				if (count == 1) {
					continue;
				}
				// iterating over each column
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: // string value in coursename
						coursename = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC: // numberic value in price
						price = cell.getNumericCellValue();

						break;
					default:
					}
				}
				// System.out.println(coursename+" "+price);
				courseList.add(new Course(coursename, price));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;

	}

	public static List<String> ReadTime(String path) {
		System.out.println("time is read");
		List<String> timeList = new ArrayList<String>(); // Array list
		try {
			File file = new File(path);
			// creating a new file instance
			FileInputStream fis = new FileInputStream(file);
			// obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(1);
			// creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator();
			int count = 0;
			// iterating over excel file
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				String time = "";
				count = count + 1;
				if (count == 1) {
					System.out.println("this is continue");
					continue;
				}
				// iterating over each column
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					time = cell.getStringCellValue();

				}
				timeList.add(time);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeList;

	}

}
