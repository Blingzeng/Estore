package cn.bling.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.bling.annotation.trans;
import cn.bling.dao.Dao_father;
import cn.bling.service.Service_father;
import cn.bling.utils.ConfigUtils;
import cn.bling.utils.MyThreadLocal;

public class BaseFactory {

	private static BaseFactory fac;
	private BaseFactory(){
		
	}
	static {
		fac=new BaseFactory();
	}
	public static BaseFactory getFactory(){
		return fac;
	}
	
	public static <T extends Dao_father> T getDao(Class<T> clazz){
		String simpleName = clazz.getSimpleName();
		String fullName = ConfigUtils.getFullName(simpleName);
		try {
			return (T) Class.forName(fullName).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T extends Service_father> T getService(Class<T> clazz){
		String simpleName = clazz.getSimpleName();
		String fullName = ConfigUtils.getFullName(simpleName);
			try {
				T t = (T) Class.forName(fullName).newInstance();
				return (T) Proxy.newProxyInstance(clazz.getClassLoader(), t.getClass().getInterfaces(), new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						if(method.isAnnotationPresent(trans.class)){
							try{
								MyThreadLocal.startTranaction();
								Object ob = method.invoke(t,args);
								MyThreadLocal.commit();
								return ob;
							}catch(InvocationTargetException e){
								MyThreadLocal.rollBack();
								throw new RuntimeException(e.getCause());
							}finally{
								MyThreadLocal.release();
							}
						}else{
							return method.invoke(t,args);
						}
					}
				});
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new RuntimeException();
			}	
	
	}
}
