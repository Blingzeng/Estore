package cn.bling.test;

import java.util.List;

import org.junit.Test;

import cn.bling.domain.Order;
import cn.bling.factory.BaseFactory;
import cn.bling.service.OrderService;

public class BaseFactoryTest {

	@Test
	public void ServiceTest(){
		OrderService service = BaseFactory.getFactory().getService(OrderService.class);
		List<Order> list = service.findAllOrder("24");
		
	}
}
