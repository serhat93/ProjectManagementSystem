package dao;

import java.util.List;

import model.Student;

public interface IStudentDAO {
	
	public void addStudent(Student student);	
	public void updateStudent(Student student);
	public Student selectStudent(int id);
	public void deleteStudent(int id);
	public int countStudent();
	public List<Student> getStudents();

}
