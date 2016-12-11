package cn.bling.utils;

import java.sql.Connection;
import java.sql.SQLException;



public class MyThreadLocal {

	private MyThreadLocal(){
		
	}
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	public static Connection getConnection(){
		if(tl.get()==null){
			tl.set(CustUtils.getConnection());
		}
		 return tl.get();
	}
	
	public static void startTranaction(){
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void commit(){
		try {
			getConnection().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void rollBack(){
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void release(){
		tl.remove();
	}
	
	
}
