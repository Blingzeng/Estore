package cn.bling.service;

import java.util.List;
import java.util.UUID;

import cn.bling.dao.Dao;
import cn.bling.domain.User;
import cn.bling.exception.MyException;
import cn.bling.factory.BaseFactory;

public class ServiceImpl  implements Service{
	Dao dao = BaseFactory.getFactory().getDao(Dao.class);
	@Override
	public User check(String userName)  {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName(userName);
		List<User> users = dao.check(user);
		if(users.size()==0){
			return null;
		}else{
			return users.get(0);
		}
		
	}
	@Override
	public void add(User user) throws MyException {
		// TODO Auto-generated method stub
		//判断用户的唯一性
		User tempUser = check(user.getUserName());
		if(tempUser==null){
			user.setActiveCode(UUID.randomUUID().toString());
			user.setRole("user");
			user.setState("0");
			dao.add(user);
		}else{
			throw new MyException();
		}
		
	}

}
