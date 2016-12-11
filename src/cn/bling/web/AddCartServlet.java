package cn.bling.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bling.domain.Product;
import cn.bling.factory.BaseFactory;
import cn.bling.service.ProService;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/servlet/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProService proS = BaseFactory.getFactory().getService(ProService.class);
		String id = request.getParameter("id");
		Product pro =proS.findProById(id);
		if(pro==null){
			response.getWriter().write("没有找到对应的商品");
			return;
		}
		//获取购物车
		Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		cart.put(pro, cart.containsKey(pro)? cart.get(pro)+1:1 );
		//重定向到购物车页面
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
