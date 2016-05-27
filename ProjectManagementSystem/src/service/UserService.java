package service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import dao.UserDAO;
import model.User;

@ManagedBean(name = "userService")
@ApplicationScoped
public class UserService {

	private static UserDAO userDAO = new UserDAO();

	public static void addUser(User user) {
		userDAO.addUser(user);
	}

	public static User selectUser(int id) {
		return userDAO.selectUser(id);
	}

	public static User selectUser(String username) {
		return userDAO.selectUser(username);
	}

}
