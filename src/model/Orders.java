package model;

public class Orders {
	private int idOrder;
	private int idClient;
	private String nameClient;
	private int idProduct;
	private String nameProduct;
	private int quantity;
	
	public Orders(int idO,int idC, String namec, int idp, String namep, int qua) {
		idOrder=idO;
		idClient=idC;
		nameClient=namec;
		idProduct=idp;
		nameProduct=namep;
		quantity=qua;
	}
	
	public int getQunatity() {
		return this.quantity;
	}
	
	public void setQunatity(int a) {
		quantity=a;
	}
	
	public int getIdProduct() {
		return this.idProduct;
	}
	
	public void setIdProduct(int a) {
		idProduct=a;
	}
	
	public int getIdClient() {
		return this.idClient;
	}
	
	public void setIdClient(int a) {
		idClient=a;
	}
	
	public int getIdOrder() {
		return this.idOrder;
	}
	
	public void setIdOrder(int a) {
		idOrder=a;
	}
	
	public String getNameClient() {
		return this.nameClient;
	}
	
	public void setNameClient(String a) {
		this.nameClient=a;
	}
	
	public String getNameProduct() {
		return this.nameProduct;
	}
	
	public void setNameProduct(String a) {
		this.nameProduct=a;
	}
	

}
