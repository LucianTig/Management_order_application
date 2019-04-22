package dao;

import java.util.List;

import model.Product;

public class ProductDAO extends AbstractDAO<Product> {
	
	public List<Product> findById(String setCol, String a) {
		return super.findById(setCol,a);
	}
	
	public void insert(Object a) {
		super.insert(a);
	}
	
	public List<Product> findAll(){
		return super.findAll();
	}
	
	public void update(String setCol, String setVal, String setCol1, String setVal1) {
		super.update(setCol, setVal, setCol1, setVal1);
	}
	
	public void delete(String setCol, String setVal) {
		super.delete(setCol, setVal);
	}

	public static void main(String[] args) {
		
	}
}
