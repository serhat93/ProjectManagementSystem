package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Initializer {

	private static Initializer initializer;
	private static EntityManager entityManager;

	public static Initializer getInstance() {
		if (initializer == null) {
			initializer = new Initializer();

			// JPA bağlantısını sağlayan entity manager oluşuturulur.
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ProjectManagementSystem");
			entityManager = emfactory.createEntityManager();
		}

		return initializer;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
