package cn.bling.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



public class ConfigUtils {
	private static Properties pro;
	private ConfigUtils(){
		
	}
	static {
		pro=new Properties();
		try {
			pro.load(new FileReader(ConfigUtils.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Properties getPro(){
		return pro;
	}
	public static String getFullName(String name){
		return pro.getProperty(name);
	}
	
}
