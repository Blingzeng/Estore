package cn.bling.dao;

import java.sql.SQLException;
import java.util.List;

import cn.bling.domain.Order;
import cn.bling.domain.OrderItem;

public interface OrderDao extends Dao_father{

	/**
	 * 增加订单
	 * @param order
	 * @throws SQLException 
	 */
	void addOrder(Order order) throws SQLException;

	/**
	 * 增加订单项
	 * @param item
	 * @throws SQLException 
	 */
	void addItem(OrderItem item) throws SQLException;

	/**
	 * 根据用户id查找所有的订单列表
	 * @param id
	 * @return
	 */
	List<Order> findAllOrder(String id);

	/**
	 * 根据订单id来查询所有的订单项
	 * @param id
	 */
	List<OrderItem> findAllItems(String id);

	/**
	 * 根据订单id来删除订单
	 * @param orderId
	 * @throws SQLException 
	 */
	void deleteOrder(String orderId) throws SQLException;

	/**
	 * 根据订单id来删除订单项
	 * @param orderId
	 * @throws SQLException 
	 */
	void deleteItems(String orderId) throws SQLException;

}
