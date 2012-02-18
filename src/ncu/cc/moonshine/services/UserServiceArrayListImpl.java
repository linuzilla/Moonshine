package ncu.cc.moonshine.services;

import java.util.ArrayList;
import java.util.List;

import ncu.cc.moonshine.domain.User;

public class UserServiceArrayListImpl implements IUserService {
	private List<User>	list = new ArrayList<User>();

	@Override
	public List<User> findAll() {
		return list;
	}

	@Override
	public User getUserById(Integer userId) {
		for (User u: list)
			if (u.getUserId() == userId)
				return u;

		return null;
	}

	@Override
	public void addUser(User user) {
		user.setUserId(list.size() + 1);
		list.add(user);
	}

	@Override
	public void deleteUser(User user) {
		list.remove(user);
	}

	@Override
	public void modifyUser(User user) {
	}
}
