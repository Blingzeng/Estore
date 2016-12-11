package cn.bling.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bling.domain.Order;
import cn.bling.domain.OrderItem;
import cn.bling.domain.Product;
import cn.bling.domain.User;
import cn.bling.factory.BaseFactory;
import cn.bling.service.OrderService;
import cn.bling.service.ProService;



/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/servlet/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//存一个订单
			//生成一个Bean
		OrderService os = BaseFactory.getFactory().getService(OrderService.class);
		ProService pros = BaseFactory.getFactory().getService(ProService.class);
		User user = (User) request.getSession().getAttribute("user");
		Order order = new Order();
		String id =UUID.randomUUID().toString();
		order.setId(id);
		order.setPaystate(0);
		order.setAdderss(request.getParameter("adderss"));
		order.setUser_id(Integer.parseInt(user.getId()));
		
		//获取购物车，
			Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
				
				//遍历获取每一个商品
				double sum=0;
				List<OrderItem> list = new ArrayList<OrderItem>();
				for(Map.Entry<Product, Integer> entry  : cart.entrySet()){
					sum+=entry.getKey().getProPrice()*entry.getValue();
					OrderItem item = new OrderItem();
					item.setNumber(entry.getValue());
					item.setOrders_id(id);
					item.setProduct_id(entry.getKey().getId());
					list.add(item);
					
				}
				order.setItems(list);
				order.setMoney(sum);
				//调用增加的方法
				os.addOrder(order);
				cart.clear();
				//返回到购物车
				response.sendRedirect(request.getContextPath()+"/CartMap.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
