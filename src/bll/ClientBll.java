package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;


/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientBll {

	private List<Validator<Client>> validators;

	public ClientBll() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new ClientAgeValidator());
	}

	public List<Client> findClient(String setCol,String a) {
		List<Client> st = (new ClientDAO()).findById(setCol,a);
		if (st == null) {
			throw new NoSuchElementException("The student with"+ setCol+" =" + a + " was not found!");
		}
		return st;
	}

	public void insertClient(Client client) throws Exception {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		(new ClientDAO()).insert(client);
	}
	
	public List<Client> findAll(){
		List<Client> l;
		l=(new ClientDAO()).findAll();
		return l;
	}
	
	public void updateClient(String setCol, String setVal, String setCol1, String setVal1) {
		(new ClientDAO()).update(setCol, setVal, setCol1, setVal1);
	}
	
	public void deleteClient(String setCol, String setVal) {
		(new ClientDAO()).delete(setCol, setVal);
	}
	
	
}
