package cn.bling.service;


import java.util.List;

import cn.bling.annotation.trans;
import cn.bling.domain.Order;
import cn.bling.domain.OrderItem;

public interface OrderService extends Service_father {

	/**
	 * 增加订单
	 * @param order
	 */
	@trans
	void addOrder(Order order);

	/**
	 * 根据用户的id查找所有的订单
	 * @param id
	 * @return
	 */
	List<Order> findAllOrder(String id);

	/**
	 * 根据订单id删除订单
	 * @param id
	 */
	@trans
	void deleteOrder(String orderId);

	

}
