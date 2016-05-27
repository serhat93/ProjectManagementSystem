package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Initializer;
import model.Course;

public class CourseDAO implements ICourseDAO {

	@Override
	public void addCourse(Course course) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(course);
		entitymanager.getTransaction().commit();
	}

	@Override
	public void updateCourse(Course course) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		Course courseToUpdate = entitymanager.find(Course.class, course.getId());
		courseToUpdate.setName(course.getName());

		entitymanager.getTransaction().commit();
	}

	@Override
	public Course selectCourse(int id) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		Course course = entitymanager.find(Course.class, id);

		return course;
	}

	@Override
	public Course selectCourse(String name) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		// Veritabanından kayıt çekmek için sorgu oluşturulur.
		Query query = entitymanager.createQuery("SELECT c FROM Course c WHERE c.name= '" + name + "'");

		@SuppressWarnings("unchecked")
		List<Course> courses = (List<Course>) query.getResultList();
		if (courses.isEmpty())
			return null;

		return courses.get(0);
	}

	@Override
	public void deleteCourse(int id) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		Course course = entitymanager.find(Course.class, id);

		if (course != null) {
			entitymanager.remove(course);
		}
		entitymanager.getTransaction().commit();
	}

	@Override
	public int countCourse() {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT COUNT(e) FROM Course c");
		int count = (int) (long) query.getSingleResult();

		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCourses() {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT c FROM Course c");
		List<Course> studentList = query.getResultList();

		return studentList;
	}

}
