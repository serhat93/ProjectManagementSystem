package service;

import java.util.List;

import dao.ProjectDAO;
import model.Project;

public class ProjectService {

	private static ProjectDAO projectDAO = new ProjectDAO();

	public static void addProject(Project project) {
		projectDAO.addProject(project);
	}

	public static Project selectProject(int id) {
		return projectDAO.selectProject(id);
	}

	public static Project selectProject(String name) {
		return projectDAO.selectProject(name);
	}

	public static void updateProject(Project project) {
		projectDAO.updateProject(project);
	}

	public static void deleteProject(int id) {
		projectDAO.deleteProject(id);
	}

	public static int countProject() {
		return projectDAO.countProject();
	}

	public static List<Project> getProjects() {
		return projectDAO.getProjects();
	}

	public static List<Project> getProjectsByCourse(int courseId) {
		return projectDAO.getProjectsByCourse(courseId);
	}
}
