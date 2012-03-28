package ncu.cc.moonshine.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ncu.cc.moonshine.domain.User;

@Qualifier("database")
@Service
public class UserServiceImpl implements IUserService {
	// private transient EntityManager entityManager;
	@Autowired
	@PersistenceUnit
	private EntityManagerFactory	entityManagerFactory;

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
		EntityManager entityManager = null;
		EntityTransaction tx = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			tx = entityManager.getTransaction();
			tx.begin();
			entityManager.remove(entityManager.find(User.class, user.getUserId()));
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) tx.rollback();
			throw e;
		} finally {
			if (entityManager != null) entityManager.close();
		}
	}
	

	@Override
	public void deleteUserById(Integer userId) {
		EntityManager entityManager = null;
		EntityTransaction tx = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			tx = entityManager.getTransaction();
			tx.begin();
			entityManager.remove(entityManager.find(User.class, userId));
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) tx.rollback();
			throw e;
		} finally {
			if (entityManager != null) entityManager.close();
		}
	}

	@Override
	public void modifyUser(User user) {
		EntityManager entityManager = null;
		EntityTransaction tx = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			tx = entityManager.getTransaction();
			tx.begin();
			entityManager.merge(user);
			entityManager.flush();
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) tx.rollback();
			throw e;
		} finally {
			if (entityManager != null) entityManager.close();
		}
	}

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
