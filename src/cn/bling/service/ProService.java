package cn.bling.service;

import java.util.List;

import cn.bling.domain.Product;

public interface ProService extends Service_father{

	/**
	 * 增加商品的方法
	 * @param pro
	 */
	void addPro(Product pro);
	
	/**
	 * 查找所有商品的方法
	 * @return
	 */
	List<Product> findAllPro();

	Product findProById(String id);

	

}
