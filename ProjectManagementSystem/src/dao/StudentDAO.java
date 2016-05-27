package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Initializer;
import model.Student;

public class StudentDAO implements IStudentDAO {

	@Override
	public void addStudent(Student student) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(student);
		entitymanager.getTransaction().commit();
	}

	@Override
	public void updateStudent(Student student) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		Student studentToUpdate = entitymanager.find(Student.class, student.getId());
		studentToUpdate.setNo(student.getNo());
		studentToUpdate.setName(student.getName());
		studentToUpdate.setSurname(student.getSurname());
		studentToUpdate.setGrades(student.getGrades());

		entitymanager.getTransaction().commit();

	}

	@Override
	public Student selectStudent(int id) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		Student student = entitymanager.find(Student.class, id);

		return student;
	}

	@Override
	public void deleteStudent(int id) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		Student student = entitymanager.find(Student.class, id);

		if (student != null) {
			entitymanager.remove(student);
		}
		entitymanager.getTransaction().commit();
	}

	@Override
	public int countStudent() {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT COUNT(e) FROM Student s");
		int count = (int) (long) query.getSingleResult();

		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudents() {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT c FROM Student c");
		List<Student> studentList = query.getResultList();

		return studentList;
	}

}
