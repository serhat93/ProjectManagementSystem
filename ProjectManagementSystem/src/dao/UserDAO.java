package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Initializer;
import model.User;

public class UserDAO implements IUserDAO {

	@Override
	public void addUser(User user) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(user);
		entitymanager.getTransaction().commit();
	}

	@Override
	public User selectUser(int id) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		User student = entitymanager.find(User.class, id);

		return student;
	}

	@Override
	public User selectUser(String username) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE u.username= '" + username + "'");

		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) query.getResultList();
		if (users.isEmpty())
			return null;

		return users.get(0);
	}

}
