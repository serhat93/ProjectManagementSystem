package dao;

import java.util.List;

import model.Course;

public interface ICourseDAO {

	public void addCourse(Course course);

	public void updateCourse(Course course);

	public Course selectCourse(int id);

	public Course selectCourse(String name);

	public void deleteCourse(int id);

	public int countCourse();

	public List<Course> getCourses();

}
