package cn.bling.dao;

import java.util.List;

import cn.bling.domain.User;

public interface Dao extends Dao_father{

	List<User> check(User user);

	void add(User user);

}
