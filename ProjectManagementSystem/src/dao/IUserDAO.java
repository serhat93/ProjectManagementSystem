package dao;

import model.User;

public interface IUserDAO {

	public void addUser(User user);

	public User selectUser(int id);

	public User selectUser(String username);
}
