package cn.bling.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import cn.bling.domain.User;
import cn.bling.factory.BaseFactory;
import cn.bling.service.Service;
import cn.bling.utils.MD5Utils;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/servlet/loginServlet" })
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取账号和密码
		String username = request.getParameter("userName");
		String password = MD5Utils.md5(request.getParameter("password"));
		
		//调用service方法种的查询方法，结果不为空则返回主页
		//结果为空则返回登陆界面并且提示错误消息
		Service service = BaseFactory.getFactory().getService(Service.class);
		User user = service.check(username);
		if(user==null||!user.getPassword().equals(password)){
			request.setAttribute("msg", "账号和密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", user);
		//判断用户是否勾选记住用户名
		if(request.getParameter("remname").equals("true")){
			//发送一个cookies
			Cookie cookie = new Cookie("remname", URLEncoder.encode(user.getUserName(), "utf-8"));
			cookie.setPath("/");
			cookie.setMaxAge(3600*24*30);
			response.addCookie(cookie);
		}else{
			Cookie cookie = new Cookie("remname", "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
			
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
