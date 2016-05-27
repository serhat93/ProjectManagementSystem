package dao;

import java.util.List;

import model.Groups;

public interface IGroupDAO {
	
	public void addGroup(Groups group);	
	public void updateGroup(Groups group);
	public Groups selectGroup(int id);
	public void deleteGroup(int id);
	public int countGroup();
	public List<Groups> getGroups();

}
