package cn.bling.dao;

import java.sql.SQLException;
import java.util.List;

import cn.bling.domain.Product;

public interface ProDao extends Dao_father{

	/**
	 * 增加商品的方法
	 * @param pro
	 */
	void addPro(Product pro);

	/**
	 * 寻找商品的方法
	 * @param pro
	 * @return
	 */
	List<Product> find(Product pro);

	/**
	 * 减少商品的数目
	 * @param product_id
	 * @param number
	 * @throws SQLException 
	 */
	void reduceNum(String product_id, int number) throws SQLException;

	void addProNum(String product_id, int number) throws SQLException;

}
