package bll.validators;

import java.util.List;

import bll.ExceptieStock;
import dao.ProductDAO;
import model.Product;
import model.Orders;

public class OrderStockValidator implements Validator<Orders> {

	@Override
	public void validate(Orders t) throws ExceptieStock {
		List<Product> st = (new ProductDAO()).findById("id",((Integer)(t.getIdProduct())).toString());
		if(st.get(0).getStock()<t.getQunatity()) {
				throw new ExceptieStock("Product under stock!!");
		}
		
		
	}

}
