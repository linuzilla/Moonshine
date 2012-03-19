package ncu.cc.moonshine.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ncu.cc.moonshine.domain.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("transaction")
public class UserService2ndImpl implements IUserService {
	@PersistenceContext
	private EntityManager	entityManager;

	@Override
	public List<User> findAll() {
		return entityManager.createNamedQuery("findAll", User.class).getResultList();
	}

	@Override
	public User getUserById(Integer userId) {
		return entityManager.createQuery("SELECT a FROM User a WHERE userId=:id", User.class).setParameter("id", userId).getSingleResult();
	}

	@Transactional
	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Transactional
	@Override
	public void deleteUser(User user) {
		entityManager.remove(entityManager.find(User.class, user.getUserId()));
	}


	@Transactional(rollbackFor={ RuntimeException.class, SQLException.class }, noRollbackFor=Exception.class)
	@Override
	public void modifyUser(User user)  {
		try {
			entityManager.merge(user);
			Integer.parseInt("abc");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		}

		entityManager.flush();
	}

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
