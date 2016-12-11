package cn.bling.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bling.domain.Order;
import cn.bling.domain.User;
import cn.bling.factory.BaseFactory;
import cn.bling.service.OrderService;

/**
 * Servlet implementation class FindOrderServlet
 */
@WebServlet("/servlet/FindOrderServlet")
public class FindOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取当前用户的id
		User user = (User) request.getSession().getAttribute("user");
		//利用用户的id查询所用订单 返回一个list
		OrderService os =BaseFactory.getFactory().getService(OrderService.class);
		List<Order> list = os.findAllOrder(user.getId());
		//将list存入request中，然后请求转发到订单列表页面。
		request.setAttribute("list", list);
		request.getRequestDispatcher("/OrderList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
