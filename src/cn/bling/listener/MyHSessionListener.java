package cn.bling.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.bling.domain.Product;

public class MyHSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		se.getSession().setAttribute("cartmap", new HashMap<Product, Integer>());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
