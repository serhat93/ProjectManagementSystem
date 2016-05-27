package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.Initializer;
import model.Groups;

public class GroupDAO implements IGroupDAO {

	@Override
	public void addGroup(Groups group) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(group);
		entitymanager.getTransaction().commit();
	}

	@Override
	public void updateGroup(Groups group) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		Groups groupToUpdate = entitymanager.find(Groups.class, group.getId());

		// TODO list update

		entitymanager.getTransaction().commit();
	}

	@Override
	public Groups selectGroup(int id) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		Groups group = entitymanager.find(Groups.class, id);

		return group;
	}

	@Override
	public void deleteGroup(int id) {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();
		entitymanager.getTransaction().begin();

		Groups group = entitymanager.find(Groups.class, id);

		if (group != null) {
			entitymanager.remove(group);
		}
		entitymanager.getTransaction().commit();
	}

	@Override
	public int countGroup() {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT COUNT(e) FROM Groups g");
		int count = (int) (long) query.getSingleResult();

		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Groups> getGroups() {
		EntityManager entitymanager = Initializer.getInstance().getEntityManager();

		Query query = entitymanager.createQuery("SELECT g FROM Groups g");
		List<Groups> groupList = query.getResultList();

		return groupList;
	}

}
