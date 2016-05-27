package service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import dao.StudentDAO;
import model.Student;

@ManagedBean(name = "studentService")
@ApplicationScoped
public class StudentService {

	private static StudentDAO studentDAO = new StudentDAO();

	public static void addStudent(Student student) {
		studentDAO.addStudent(student);
	}

	public static Student selectStudent(int id) {
		return studentDAO.selectStudent(id);
	}

	public static void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}

	public static void deleteStudent(int id) {
		studentDAO.deleteStudent(id);
	}

	public static int countStudent() {
		return studentDAO.countStudent();
	}

	public static List<Student> getStudents() {
		return studentDAO.getStudents();
	}
}
