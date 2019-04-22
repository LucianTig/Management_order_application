package bll;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import bll.validators.OrderStockValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import dao.OrdersDAO;
import dao.ProductDAO;
import bll.ProductBll;
import model.Client;
import model.Orders;
import model.Product;

public class OrdersBll {
	private List<Validator<Orders>> validators;

	public OrdersBll() {
		validators = new ArrayList<Validator<Orders>>();
		validators.add(new OrderStockValidator());
	}
	
	public void insertOrder(Orders order) throws Exception {
		for (Validator<Orders> v : validators) {
			v.validate(order);
		}
		List<Product> st = (new ProductDAO()).findById("id",((Integer)(order.getIdProduct())).toString());
		List<Client> cl1 = (new ClientDAO()).findById("id",((Integer)(order.getIdClient())).toString());
		(new ProductBll()).updateProduct("stock",((Integer)(st.get(0).getStock()-order.getQunatity())).toString() , "id",((Integer)(order.getIdProduct())).toString());
		(new OrdersDAO()).insert(order);
		
		try {
		PrintWriter writer = new PrintWriter("factura.txt", "UTF-8");
		writer.println("                                  Factura                             ");
		writer.println("Detalii de facturare client ");
		writer.println(cl1.get(0).toString());
		writer.println("Produs comandat: \n");
		writer.println(st.get(0).toString()+"\n");
		writer.println("Numar de produse comandate "+order.getQunatity());
		writer.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Orders> findAll(){
		List<Orders> l;
		l=(new OrdersDAO()).findAll();
		return l;
	}

}
