package ncu.cc.moonshine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ncu.cc.moonshine.dao.IUserDao;
import ncu.cc.moonshine.domain.User;

@Service
@Qualifier("dao")
public class UserService3rdImpl implements IUserService {
	@Autowired
	private IUserDao		userDao;

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User getUserById(Integer userId) {
		return userDao.findById(userId);
	}

	@Transactional
	@Override
	public void addUser(User user) {
		// userDao.persist(user);
		userDao.saveOrUpdate(user);
	}

	@Transactional
	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Transactional
	@Override
	public void modifyUser(User user) {
		userDao.merge(user);
	}
}
