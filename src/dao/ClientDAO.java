package dao;

import java.util.List;

import model.Client;

public class ClientDAO extends AbstractDAO<Client> {
	
	public List<Client> findById(String setCol,String a) {
		return super.findById(setCol,a);
	}
	
	public void insert(Object a) {
		super.insert(a);
	}
	
	public List<Client> findAll(){
		return super.findAll();
	}
	
	public void update (String setCol, String setVal, String setCol1, String setVal1) {
		super.update(setCol, setVal, setCol1, setVal1);
	}
	
	public void delete (String setCol, String setVal) {
		super.delete(setCol, setVal);
	}

	public static void main(String[] args) {
		/*Client cl1;
		cl1=(new ClientDAO()).findById(1);
		System.out.println(cl1.getId()+" "+cl1.getName()+" "+cl1.getAddress()+" "+cl1.getAge());
		
		List<Client> lista=(new ClientDAO()).findAll();
		for(Client cl:lista)
			System.out.println(cl.getId()+" "+cl.getName()+" "+cl.getAddress()+" "+cl.getAge());
		
		Client client=new Client(3,"Mihai Cucui","Observatorului","mihai@yahoo.com",21);
		(new ClientDAO()).insert(client);*/
		
		//(new ClientDAO()).update("age", "20", "name", "Remus");
	}
}
