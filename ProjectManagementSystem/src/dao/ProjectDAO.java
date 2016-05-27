package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Initializer;
import model.Project;

public class ProjectDAO implements IProjectDAO {

	@Override
	public void addProject(Project project) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(project);
		entitymanager.getTransaction().commit();
	}

	@Override
	public void updateProject(Project project) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		Project projectToUpdate = entitymanager.find(Project.class, project.getId());
		projectToUpdate.setName(project.getName());
		projectToUpdate.setCourse(project.getCourse());

		entitymanager.getTransaction().commit();
	}

	@Override
	public Project selectProject(int id) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		Project project = entitymanager.find(Project.class, id);

		return project;
	}

	@Override
	public Project selectProject(String name) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT p FROM Project p WHERE p.name= '" + name + "'");

		List<Project> projects = (List<Project>) query.getResultList();
		if (projects.isEmpty())
			return null;

		return projects.get(0);
	}

	@Override
	public void deleteProject(int id) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		Project project = entitymanager.find(Project.class, id);

		if (project != null) {
			entitymanager.remove(project);
		}
		entitymanager.getTransaction().commit();
	}

	@Override
	public int countProject() {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT COUNT(e) FROM Project s");
		int count = (int) (long) query.getSingleResult();

		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjects() {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT c FROM Project c");
		List<Project> projectList = query.getResultList();

		return projectList;
	}

	@Override
	public List<Project> getProjectsByCourse(int courseId) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT p FROM Project p WHERE p.COURSE_id = '" + courseId + "'");
		List<Project> projectList = query.getResultList();

		return projectList;
	}

}
