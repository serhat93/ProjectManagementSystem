package dao;

import java.util.List;

import model.Project;

public interface IProjectDAO {

	public void addProject(Project project);

	public void updateProject(Project project);

	public Project selectProject(int id);

	public Project selectProject(String name);

	public void deleteProject(int id);

	public int countProject();

	public List<Project> getProjects();

	public List<Project> getProjectsByCourse(int courseId);
}
