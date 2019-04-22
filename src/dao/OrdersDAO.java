package dao;

import java.util.List;
import model.Orders;

public class OrdersDAO extends AbstractDAO<Orders>{
	
	public void insert(Object a) {
		super.insert(a);
	}

	public List<Orders> findAll(){
		return super.findAll();
	}
	
	public List<Orders> findById(String setCol,String a) {
		return super.findById(setCol,a);
	}
}
