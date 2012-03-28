package ncu.cc.moonshine.services;

import java.util.List;

import ncu.cc.moonshine.domain.User;

public interface IUserService {
	public List<User> findAll();
	public User getUserById(Integer userId);
	public void addUser(User user);
	public void deleteUser(User user);
	public void deleteUserById(Integer userId);
	public void modifyUser(User user);
	public User getUserByName(String username);
}
