package ncu.cc.moonshine.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ncu.cc.moonshine.domain.User;

@Service
public class UserServiceArrayListImpl implements IUserService {
	private List<User>	list = new ArrayList<User>();
	private int			lastUserid = list.size();

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
		user.setUserId(++lastUserid);
		list.add(user);
	}

	@Override
	public void deleteUser(User user) {
		list.remove(user);
	}

	@Override
	public void modifyUser(User user) {
	}

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
