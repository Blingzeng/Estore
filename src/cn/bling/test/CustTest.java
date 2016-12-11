package cn.bling.test;

import java.util.List;

import org.junit.Test;

import cn.bling.dao.Dao;
import cn.bling.domain.User;
import cn.bling.factory.BaseFactory;

public class CustTest {
	Dao dao = BaseFactory.getFactory().getDao(Dao.class);
	@Test
	public void checkTest(){
		
		User userE =new User();
		List<User> users =dao.check(userE);
		for(User user:users){
			System.out.println(user.getUserName());
		}
	}
	@Test
	public void addTest(){
		User userE =new User();
		dao.add(userE);
	}
}
