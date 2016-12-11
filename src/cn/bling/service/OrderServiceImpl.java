package cn.bling.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bling.dao.OrderDao;
import cn.bling.dao.ProDao;
import cn.bling.domain.Order;
import cn.bling.domain.OrderItem;
import cn.bling.factory.BaseFactory;
import cn.bling.utils.MyThreadLocal;

public class OrderServiceImpl  implements OrderService {
	OrderDao od = BaseFactory.getFactory().getDao(OrderDao.class);
	ProDao prod = BaseFactory.getFactory().getDao(ProDao.class);
	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		
		//增加订单
		try {
			od.addOrder(order);
			//增加订单项
			for(OrderItem item:order.getItems()){
				od.addItem(item);
				//减少商品数量
				prod.reduceNum(item.getProduct_id(),item.getNumber());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<Order> findAllOrder(String id) {
		// TODO Auto-generated method stub
		//根据用户的id查找所有的订单
		List<Order> list =od.findAllOrder(id);
		//为每个order增加一个items属性
		for(Order order : list){
			List<OrderItem> list1 =od.findAllItems(order.getId());
			order.setItems(list1);
		}
		return list;
	}
	@Override
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		//将商品的数量加回去
		List<OrderItem> list = 	od.findAllItems(orderId);
		try {
			for(OrderItem item : list){
				prod.addProNum(item.getProduct_id(),item.getNumber());
			}
			od.deleteItems(orderId);
			od.deleteOrder(orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
