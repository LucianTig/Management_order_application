package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

public class ProductBll {


	private List<Validator<Product>> validators;

	public ProductBll() {
		validators = new ArrayList<Validator<Product>>();
	}

	public List<Product> findProduct(String setCol, String a) {
		List<Product> st = (new ProductDAO()).findById(setCol,a);
		if (st == null) {
			throw new NoSuchElementException("The student with "+ setCol+"=" + a + " was not found!");
		}
		return st;
	}

	public void insertProduct(Product product) throws Exception {
		for (Validator<Product> v : validators) {
			v.validate(product);
		}
		(new ProductDAO()).insert(product);
	}

	public List<Product> findAll(){
		List<Product> l;
		l=(new ProductDAO()).findAll();
		return l;
	}
	
	public void updateProduct(String setCol, String setVal, String setCol1, String setVal1) {
		(new ProductDAO()).update(setCol, setVal, setCol1, setVal1);
	}
	
	public void deleteProduct(String setCol, String setVal) {
		(new ProductDAO()).delete(setCol, setVal);
	}

}
