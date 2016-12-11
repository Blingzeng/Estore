package cn.bling.service;

import cn.bling.domain.User;
import cn.bling.exception.MyException;

public interface Service extends Service_father{

	User check(String userName);

	void add(User user) throws MyException;

	

}
