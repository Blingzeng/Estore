package cn.bling.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.bling.domain.Product;
import cn.bling.domain.User;
import cn.bling.utils.CustUtils;
import cn.bling.utils.MyThreadLocal;

public class ProDaoImpl implements ProDao {

	@Override
	public void addPro(Product pro) {
		// TODO Auto-generated method stub
		String sql = "insert into product values (?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(CustUtils.getSource());
		try {
			runner.update(sql,pro.getId(),pro.getProName(),pro.getProPrice(),pro.getCategory(),pro.getProNum(),pro.getImgUrl(),pro.getProDec());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> find(Product pro) {
		// TODO Auto-generated method stub
		String sql = "select * from product where 1=1 ";
		List list = new ArrayList();
		if(pro.getId()!=null){
			sql+="and id=? ";
			list.add(pro.getId());
		}
		if(pro.getProName()!=null){
			sql+="and proname=? ";
			list.add(pro.getProName());
		}
		
		if(pro.getCategory()!=null){
			sql+="and category=? ";
			list.add(pro.getCategory());
		}
		
		if(pro.getImgUrl()!=null){
			sql+="and imgurl=? ";
			list.add(pro.getImgUrl());
		}
		if(pro.getProDec()!=null){
			sql+="and prodec=? ";
			list.add(pro.getProDec());
		}
		
		QueryRunner runner = new QueryRunner(CustUtils.getSource());
		try {
			if(list.size()==0){
				return runner.query(sql, new BeanListHandler<Product>(Product.class));
			}else{
				return runner.query(sql, new BeanListHandler<Product>(Product.class),list.toArray());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void reduceNum(String product_id, int number) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update product set proNum=proNum-? where id=? and proNum>=?";
		QueryRunner runner = new QueryRunner();
		int count =runner.update(MyThreadLocal.getConnection(), sql, number,product_id,number);
		if(count==0){
			throw new RuntimeException("库存不足");
		}
	}

	@Override
	public void addProNum(String product_id, int number) throws SQLException {
		// TODO Auto-generated method stub
		//将商品的数量加回去
		String sql = "update product set pronum=pronum+? where id=?";
		QueryRunner runner = new QueryRunner();
		int count = runner.update(MyThreadLocal.getConnection(),sql,number,product_id);
		if(count==0){
			throw new RuntimeException("没有找到要添加的商品");
		}
	}

}
