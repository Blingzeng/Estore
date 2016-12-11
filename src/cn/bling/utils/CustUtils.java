package cn.bling.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class CustUtils {

	private static ComboPooledDataSource source = new ComboPooledDataSource();
	private CustUtils(){
		
	}
	
	public static DataSource getSource(){
		return source;
	}
	
	public static Connection getConnection(){
		try {
			return source.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}
