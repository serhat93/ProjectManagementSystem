package service;

import java.util.List;

import dao.GroupDAO;
import model.Groups;

public class GroupService {
	
	private static GroupDAO groupDAO = new GroupDAO();

	public static void addGroup(Groups group) {
		groupDAO.addGroup(group);
	}

	public static Groups selectGroup(int id) {
		return groupDAO.selectGroup(id);
	}

	public static void updateGroup(Groups group) {
		groupDAO.updateGroup(group);
	}

	public static void deleteGroup(int id) {
		groupDAO.deleteGroup(id);
	}

	public static int countGroup() {
		return groupDAO.countGroup();
	}

	public static List<Groups> getGroups() {
		return groupDAO.getGroups();
	}
	
}
