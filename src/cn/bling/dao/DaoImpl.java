package cn.bling.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.bling.domain.User;
import cn.bling.utils.CustUtils;

public class DaoImpl implements Dao {

	@Override
	public List<User> check(User user) {
		// TODO Auto-generated method stub
		//查询使用的是c3p0
		String sql = "select * from user where 1=1 ";
		List list = new ArrayList();
		if(user.getId()!=null){
			sql+="and id=? ";
			list.add(user.getId());
		}
		if(user.getUserName()!=null){
			sql+="and username=? ";
			list.add(user.getUserName());
		}
		if(user.getPassword()!=null){
			sql+="and password=? ";
			list.add(user.getPassword());
		}
		if(user.getNickName()!=null){
			sql+="and Nickname=? ";
			list.add(user.getNickName());
		}
		if(user.getEmail()!=null){
			sql+="and Email=? ";
			list.add(user.getEmail());
		}
		if(user.getActiveCode()!=null){
			sql+="and Activecode=? ";
			list.add(user.getActiveCode());
		}
		if(user.getRole()!=null){
			sql+="and getRole=? ";
			list.add(user.getRole());
		}
		
		QueryRunner runner = new QueryRunner(CustUtils.getSource());
		try {
			if(list.size()==0){
				return runner.query(sql, new BeanListHandler<User>(User.class));
			}else{
				return runner.query(sql, new BeanListHandler<User>(User.class),list.toArray());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into user values (null,?,?,?,?,?,?,?,null)";
		QueryRunner runner = new QueryRunner(CustUtils.getSource());
		try {
			runner.update(sql, user.getUserName(),user.getPassword(),user.getNickName(),user.getEmail(),user.getActiveCode(),user.getRole(),user.getState());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
