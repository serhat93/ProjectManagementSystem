package service;

import java.util.List;

import dao.CourseDAO;
import model.Course;

public class CourseService {
	private static CourseDAO courseDAO = new CourseDAO();

	public static void addCourse(Course course) {
		courseDAO.addCourse(course);
	}

	public static Course selectCourse(int id) {
		return courseDAO.selectCourse(id);
	}
	
	public static Course selectCourse(String name) {
		return courseDAO.selectCourse(name);
	}

	public static void updateCourse(Course course) {
		courseDAO.updateCourse(course);
	}

	public static void deleteCourse(int id) {
		courseDAO.deleteCourse(id);
	}

	public static int countCourse() {
		return courseDAO.countCourse();
	}

	public static List<Course> getCourses() {
		return courseDAO.getCourses();
	}
}
