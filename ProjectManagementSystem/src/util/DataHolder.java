package util;

import model.Groups;
import model.Project;

public class DataHolder {

	private static Project project;
	private static Groups group;

	public static Project getProject() {
		return project;
	}

	public static void setProject(Project project) {
		DataHolder.project = project;
	}

	public static Groups getGroup() {
		return group;
	}

	public static void setGroup(Groups group) {
		DataHolder.group = group;
	}

}
