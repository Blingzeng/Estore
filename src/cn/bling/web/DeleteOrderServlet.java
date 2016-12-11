package cn.bling.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bling.factory.BaseFactory;
import cn.bling.service.OrderService;

/**
 * Servlet implementation class DeleteOrder
 */
@WebServlet("/servlet/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取订单的id
		String id = request.getParameter("id");
		//调用orderService中的方法来删除订单
		OrderService os = BaseFactory.getFactory().getService(OrderService.class);
		os.deleteOrder(id);
		//重定向到订单页面
		response.sendRedirect(request.getContextPath()+"/servlet/FindOrderServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
