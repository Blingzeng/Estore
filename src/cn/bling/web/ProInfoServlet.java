package cn.bling.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bling.domain.Product;
import cn.bling.factory.BaseFactory;
import cn.bling.service.ProService;

/**
 * Servlet implementation class ProInfoServlet
 */
@WebServlet("/servlet/ProInfoServlet")
public class ProInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		ProService proSv = BaseFactory.getFactory().getService(ProService.class);
		Product pro = proSv.findProById(id);
		if(pro==null){
			response.getWriter().write("该货物的资源已经消失了......");
		}else{
			request.setAttribute("product", pro);
			request.getRequestDispatcher("/ProInfo.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
