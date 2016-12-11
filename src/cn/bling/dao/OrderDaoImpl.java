package cn.bling.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.bling.domain.Order;
import cn.bling.domain.OrderItem;
import cn.bling.utils.CustUtils;
import cn.bling.utils.MyThreadLocal;


public class OrderDaoImpl implements OrderDao {

	@Override
	public void addOrder(Order order) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into orders values (?,?,?,null,?,?)";
		QueryRunner runner = new QueryRunner();
		runner.update(MyThreadLocal.getConnection(),sql,order.getId(),order.getMoney(),order.getPaystate(),order.getAdderss(),order.getUser_id());
	}

	@Override
	public void addItem(OrderItem item) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into orderitem values (?,?,?)";
		QueryRunner runner = new QueryRunner(CustUtils.getSource());
		runner.update(MyThreadLocal.getConnection(),sql,item.getOrders_id(),item.getProduct_id(),item.getNumber());
	}

	@Override
	public List<Order> findAllOrder(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT orders.*,user.nickname userName FROM orders,user WHERE user.id=orders.user_id and user.id=? ORDER BY orders.ordertime DESC";
		QueryRunner runner = new QueryRunner(CustUtils.getSource());
		try {
			List<Order> list = runner.query(sql,new BeanListHandler<Order>(Order.class),id);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public List<OrderItem> findAllItems(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT product.id product_id,product.proname pro_name,product.category pro_category,product.proprice pro_price, orderItem.number  FROM product,orderitem WHERE product.id=orderitem.product_id and orderitem.orders_id=? ";
		QueryRunner runner = new QueryRunner(CustUtils.getSource());
		try {
			List<OrderItem> list = runner.query(sql,new BeanListHandler<OrderItem>(OrderItem.class),id);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteOrder(String orderId) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from orders where id=?";
		QueryRunner runner = new QueryRunner();
		
			runner.update(MyThreadLocal.getConnection(),sql,orderId);
		
	}

	@Override
	public void deleteItems(String orderId) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from orderitem where orders_id=?";
		QueryRunner runner = new QueryRunner();
			runner.update(MyThreadLocal.getConnection(),sql,orderId);
	}

}
