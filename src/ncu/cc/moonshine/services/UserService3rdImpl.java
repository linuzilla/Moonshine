package ncu.cc.moonshine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ncu.cc.moonshine.dao.IUserDao;
import ncu.cc.moonshine.domain.User;
import ncu.cc.moonshine.security.UserContextService;

@Service
@Qualifier("dao")
public class UserService3rdImpl implements IUserService {
	@Autowired
	private IUserDao		userDao;
	
	@Autowired
	private UserContextService userContextService;

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
		user.setCreatedBy(userContextService.getCurrentUser());
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

	@Override
	public User getUserByName(String username) {
		List<User> list = userDao.findByProperty("name", username);
		if (list != null && list.size() > 0) return list.get(0);
		return null;
	}
}
