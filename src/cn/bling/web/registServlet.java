package cn.bling.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.bling.domain.User;
import cn.bling.exception.MyException;
import cn.bling.factory.BaseFactory;
import cn.bling.service.Service;
import cn.bling.utils.MD5Utils;

/**
 * Servlet implementation class registServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/servlet/registServlet" })
public class registServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//校验验证码
		String valification = request.getParameter("valification");
		String valification_session = (String) request.getSession().getAttribute("valification");
		if(!valification.equals(valification_session)){
			request.setAttribute("userName", request.getParameter("userName"));
			request.setAttribute("nickName", request.getParameter("nickName"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}

		Service service = BaseFactory.getFactory().getService(Service.class);
	
		//将用户封装成bean，使用beanUtils的populate方法
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			user.setPassword(MD5Utils.md5(request.getParameter("password")));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//用service的增加方法
		
		try {
			service.add(user);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			request.setAttribute("userName", request.getParameter("userName"));
			request.setAttribute("nickName", request.getParameter("nickName"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("msg", "账户已经存在");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		
		//3秒后重定向到主页
//		response.getWriter().write("注册成功，请到邮箱点击认证。3秒后重定向到主页");
//		response.setHeader("Refresh", "3;url="+request.getContextPath()+"/index.jsp");
		User user1 = service.check(user.getUserName());
		request.getSession().setAttribute("user", user1);
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
