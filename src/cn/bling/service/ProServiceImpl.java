package cn.bling.service;

import java.util.List;

import cn.bling.dao.ProDao;
import cn.bling.domain.Product;
import cn.bling.factory.BaseFactory;

public class ProServiceImpl  implements ProService {
	ProDao proDao = BaseFactory.getFactory().getDao(ProDao.class);
	@Override
	public void addPro(Product pro) {
		// TODO Auto-generated method stub
		proDao.addPro(pro);
	}
	@Override
	public List<Product> findAllPro() {
		// TODO Auto-generated method stub
		Product pro =new Product();
		return proDao.find(pro);
	}
	@Override
	public Product findProById(String id) {
		// TODO Auto-generated method stub
		Product pro =new Product();
		pro.setId(id);
		List<Product> list = proDao.find(pro);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}
	

}
