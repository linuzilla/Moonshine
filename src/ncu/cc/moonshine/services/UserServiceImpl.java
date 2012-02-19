package ncu.cc.moonshine.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import ncu.cc.moonshine.domain.User;

public class UserServiceImpl implements IUserService {
	// private transient EntityManager entityManager;
	private EntityManagerFactory	entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public List<User> findAll() {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			return entityManager.createNamedQuery("User.AllUsers", User.class).getResultList();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	@Override
	public User getUserById(Integer userId) {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			transaction.commit();
			return entityManager.createNamedQuery("User.User4Id", User.class).setParameter("id", userId).getSingleResult();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	@Override
	public void addUser(User user) {
		EntityManager entityManager = null;
		EntityTransaction tx = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) tx.rollback();
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
