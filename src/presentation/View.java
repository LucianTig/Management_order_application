package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bll.ClientBll;
import bll.ExceptieStock;
import bll.OrdersBll;
import bll.ProductBll;
import model.Client;
import model.Orders;
import model.Product;

public class View extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pane3 = new JPanel(new GridBagLayout());
	private JPanel pane1 = new JPanel(new GridBagLayout());
	private JPanel pane2 = new JPanel(new GridBagLayout());
	private JPanel mainPane = new JPanel(new GridBagLayout());

	GridBagConstraints c = new GridBagConstraints();

	private JButton buttonC1 = new JButton("Add new object");
	private JButton buttonC2 = new JButton("Edit object");
	private JButton buttonC3 = new JButton("Delete object");
	private JButton buttonC4 = new JButton("View all object");
	private JButton buttonC5 = new JButton("View object");
	private JButton buttonP = new JButton("Place an order");

	private JLabel label1 = new JLabel("Select a table");

	String optionTable[]= { "Client     ","Product    "}; 

	JList opt=new JList(optionTable); 

	public View(String name) {
		super(name);

		c.gridx = 0;
		c.gridy = 0;
		pane2.add(label1, c);

		c.gridx = 0;
		c.gridy = 1;
		opt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pane2.add(opt, c);


		c.gridx = 0;
		c.gridy = 0;
		buttonC1.addActionListener(this);
		pane1.add(buttonC1, c);

		c.gridx = 1;
		c.gridy = 0;
		buttonC2.addActionListener(this);
		pane1.add(buttonC2, c);

		c.gridx = 2;
		c.gridy = 0;
		buttonC3.addActionListener(this);
		pane1.add(buttonC3, c);

		c.gridx = 0;
		c.gridy = 1;
		buttonC4.addActionListener(this);
		pane1.add(buttonC4, c);

		c.gridx = 1;
		c.gridy = 1;
		buttonC5.addActionListener(this);
		pane1.add(buttonC5, c);





		c.gridx = 0;
		c.gridy = 0;
		pane3.add(pane2, c);

		c.gridx = 1;
		c.gridy = 0;
		pane3.add(pane1, c);

		c.gridx = 0;
		c.gridy = 0;
		mainPane.add(pane3, c);

		c.gridx = 0;
		c.gridy = 1;
		buttonP.addActionListener(this);
		pane3.add(buttonP, c);




		/*pane1.setAlignmentX(Component.LEFT_ALIGNMENT);
		pane2.setAlignmentX(Component.LEFT_ALIGNMENT);
		mainPane.setAlignmentX(Component.LEFT_ALIGNMENT);*/


		this.add(mainPane);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();

		if(source == buttonC1){

			//Client clTest=new Client();
			//Product prTest=new Product();
			//String tabela=(String) opt.getSelectedValue();
			int optiune=opt.getSelectedIndex();
			System.out.println(optiune);

			JPanel paneCl1 = new JPanel(new FlowLayout());
			JPanel paneCl2 = new JPanel(new FlowLayout());
			JLabel labelCl1 = new JLabel("ID                ");
			JLabel labelCl2 = new JLabel("Name              ");
			JLabel labelCl3 = new JLabel("Address           ");
			JLabel labelCl4 = new JLabel("Email             ");
			JLabel labelCl5 = new JLabel("Age               ");

			JLabel labelCl6 = new JLabel("Stock               ");

			JTextField text1 = new JTextField(3);  //id
			JTextField text2 = new JTextField(8);  //name
			JTextField text3 = new JTextField(8);  //address 
			JTextField text4 = new JTextField(8);  //email
			JTextField text5 = new JTextField(8);  //age

			JTextField text6 = new JTextField(4);  //stock
			JButton insertClient = new JButton("Insert Client");
			JButton insertProduct = new JButton("Insert Product");

			if(optiune==0) {

				paneCl1.add(labelCl1);
				paneCl1.add(labelCl2);
				paneCl1.add(labelCl3);
				paneCl1.add(labelCl4);
				paneCl1.add(labelCl5);

				paneCl2.add(text1);
				paneCl2.add(text2);
				paneCl2.add(text3);
				paneCl2.add(text4);
				paneCl2.add(text5);

			}
			if(optiune==1) {
				paneCl1.add(labelCl1);
				paneCl1.add(labelCl2);
				paneCl1.add(labelCl6);

				paneCl2.add(text1);
				paneCl2.add(text2);
				paneCl2.add(text6);
			}

			JDialog d = new JDialog(this, "Result query");
			d.setLayout(new GridBagLayout());

			c.gridx = 0;
			c.gridy = 0;
			d.add(paneCl1, c);

			c.gridx = 0;
			c.gridy = 1;
			d.add(paneCl2, c);

			c.gridx = 0;
			c.gridy = 2;
			if(optiune==0) {
				d.add(insertClient, c);
			}
			if(optiune==1) {
				d.add(insertProduct, c);
			}

			insertClient.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					try {
						(new ClientBll()).insertClient(new Client(Integer.parseInt(text1.getText()),text2.getText(),text3.getText(),text4.getText(),Integer.parseInt(text5.getText())));
					} catch (NumberFormatException e1) {
						System.out.println(e1.getMessage());
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				}  
			}); 

			insertProduct.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					try {
						(new ProductBll()).insertProduct(new Product(Integer.parseInt(text1.getText()),text2.getText(),Integer.parseInt(text6.getText())));
					} catch (NumberFormatException e1) {
						System.out.println(e1.getMessage());
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				}  
			}); 


			d.setSize(600,400);
			d.setVisible(true);
		}

		if(source == buttonC2){

			int optiune=opt.getSelectedIndex();
			JDialog d = new JDialog(this, "Update");
			d.setLayout(new GridBagLayout());
			JLabel labelCl1 = new JLabel("Update column          ");
			JLabel labelCl2 = new JLabel("Set value:          ");
			JTextField text1 = new JTextField(8);  //setVal
			JTextField text2 = new JTextField(8);  //setVal1

			if(optiune==0) {

				String optionTableSet[]=this.retrieveNameColumn(new Client()); 
				JList optSet=new JList(optionTableSet);
				String optionTableWhere[]=this.retrieveNameColumn(new Client()); 
				JList optWhere=new JList(optionTableWhere);
				optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				optWhere.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JButton updateButton = new JButton("Update Client");

				c.gridx = 0;
				c.gridy = 0;
				d.add(labelCl1, c);

				c.gridx = 0;
				c.gridy = 1;
				d.add(optSet, c);

				c.gridx = 1;
				c.gridy = 0;
				d.add(labelCl2, c);

				c.gridx = 1;
				c.gridy = 1;
				d.add(text1, c);

				c.gridx = 0;
				c.gridy = 2;
				d.add(new JLabel("Condtion column"), c);

				c.gridx = 0;
				c.gridy = 3;
				d.add(optWhere, c);

				c.gridx = 1;
				c.gridy = 2;
				d.add(new JLabel("Condition value"), c);

				c.gridx = 1;
				c.gridy = 3;
				d.add(text2, c);

				c.gridx = 0;
				c.gridy = 4;
				d.add(updateButton, c);

				updateButton.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){  
						String optiuneSet=(String) optSet.getSelectedValue();
						String optiuneWhere=(String) optWhere.getSelectedValue();
						(new ClientBll()).updateClient(optiuneSet,text1.getText(),optiuneWhere,text2.getText());
					}  
				});
			}
			if(optiune==1) {
				String optionTableSet[]=this.retrieveNameColumn(new Product()); 
				JList optSet=new JList(optionTableSet);
				String optionTableWhere[]=this.retrieveNameColumn(new Product()); 
				JList optWhere=new JList(optionTableWhere);
				optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				optWhere.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JButton updateButton = new JButton("Update Product");

				c.gridx = 0;
				c.gridy = 0;
				d.add(labelCl1, c);

				c.gridx = 0;
				c.gridy = 1;
				d.add(optSet, c);

				c.gridx = 1;
				c.gridy = 0;
				d.add(labelCl2, c);

				c.gridx = 1;
				c.gridy = 1;
				d.add(text1, c);

				c.gridx = 0;
				c.gridy = 2;
				d.add(new JLabel("Condtion column"), c);

				c.gridx = 0;
				c.gridy = 3;
				d.add(optWhere, c);

				c.gridx = 1;
				c.gridy = 2;
				d.add(new JLabel("Condition value"), c);

				c.gridx = 1;
				c.gridy = 3;
				d.add(text2, c);

				c.gridx = 0;
				c.gridy = 4;
				d.add(updateButton, c);

				updateButton.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){  
						String optiuneSet=(String) optSet.getSelectedValue();
						String optiuneWhere=(String) optWhere.getSelectedValue();
						(new ProductBll()).updateProduct(optiuneSet,text1.getText(),optiuneWhere,text2.getText());
					}  
				});
			}


			d.setSize(600,400);
			d.setVisible(true);

		}

		if(source == buttonC3){
			int optiune=opt.getSelectedIndex();
			JDialog d = new JDialog(this, "Delete");
			d.setLayout(new GridBagLayout());
			JLabel labelCl1 = new JLabel("Delete condtion column          ");
			JLabel labelCl2 = new JLabel("Condition value:          ");
			JTextField text1 = new JTextField(8);  //setVal

			if(optiune==0) {

				String optionTableSet[]=this.retrieveNameColumn(new Client()); 
				JList optSet=new JList(optionTableSet);
				optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JButton updateButton = new JButton("Delete");

				c.gridx = 0;
				c.gridy = 0;
				d.add(labelCl1, c);

				c.gridx = 0;
				c.gridy = 1;
				d.add(optSet, c);

				c.gridx = 1;
				c.gridy = 0;
				d.add(labelCl2, c);

				c.gridx = 1;
				c.gridy = 1;
				d.add(text1, c);


				c.gridx = 0;
				c.gridy = 4;
				d.add(updateButton, c);

				updateButton.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){  
						String optiuneSet=(String) optSet.getSelectedValue();
						(new ClientBll()).deleteClient(optiuneSet,text1.getText());
					}  
				});
			}
			if(optiune==1) {
				String optionTableSet[]=this.retrieveNameColumn(new Product()); 
				JList optSet=new JList(optionTableSet);
				optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JButton updateButton = new JButton("Update Product");

				c.gridx = 0;
				c.gridy = 0;
				d.add(labelCl1, c);

				c.gridx = 0;
				c.gridy = 1;
				d.add(optSet, c);

				c.gridx = 1;
				c.gridy = 0;
				d.add(labelCl2, c);

				c.gridx = 1;
				c.gridy = 1;
				d.add(text1, c);

				c.gridx = 0;
				c.gridy = 4;
				d.add(updateButton, c);

				updateButton.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){  
						String optiuneSet=(String) optSet.getSelectedValue();
						(new ProductBll()).deleteProduct(optiuneSet,text1.getText());
					}  
				});
			}
			d.setSize(600,400);
			d.setVisible(true);
		}


		if(source == buttonC4){

			int optiune=opt.getSelectedIndex();
			if(optiune==0) {
				List<Client> lista=(new ClientBll()).findAll();
				this.retrieveProperties(lista);
			}
			if(optiune==1) {
				List<Product> lista=(new ProductBll()).findAll();
				this.retrieveProperties(lista);
			}
		}

		if(source == buttonC5){
			int optiune=opt.getSelectedIndex();
			JDialog d = new JDialog(this, "FindByColumn");
			d.setLayout(new GridBagLayout());
			JLabel labelCl1 = new JLabel("Find condition column          ");
			JLabel labelCl2 = new JLabel("Condition value:          ");
			JTextField text1 = new JTextField(8);  //setVal

			if(optiune==0) {

				String optionTableSet[]=this.retrieveNameColumn(new Client()); 
				JList optSet=new JList(optionTableSet);
				optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JButton findButton = new JButton("Find");

				c.gridx = 0;
				c.gridy = 0;
				d.add(labelCl1, c);

				c.gridx = 0;
				c.gridy = 1;
				d.add(optSet, c);

				c.gridx = 1;
				c.gridy = 0;
				d.add(labelCl2, c);

				c.gridx = 1;
				c.gridy = 1;
				d.add(text1, c);


				c.gridx = 0;
				c.gridy = 4;
				d.add(findButton, c);

				findButton.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){  
						String optiuneSet=(String) optSet.getSelectedValue();
						List<Client> lista=(new ClientBll()).findClient(optiuneSet,text1.getText());
						retrieveProperties(lista);
					}  
				});
			}
			if(optiune==1) {
				String optionTableSet[]=this.retrieveNameColumn(new Product()); 
				JList optSet=new JList(optionTableSet);
				optSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JButton findButton = new JButton("Find");

				c.gridx = 0;
				c.gridy = 0;
				d.add(labelCl1, c);

				c.gridx = 0;
				c.gridy = 1;
				d.add(optSet, c);

				c.gridx = 1;
				c.gridy = 0;
				d.add(labelCl2, c);

				c.gridx = 1;
				c.gridy = 1;
				d.add(text1, c);


				c.gridx = 0;
				c.gridy = 4;
				d.add(findButton, c);

				findButton.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){  
						String optiuneSet=(String) optSet.getSelectedValue();
						List<Product> lista=(new ProductBll()).findProduct(optiuneSet,text1.getText());
						retrieveProperties(lista);
					}  
				});

			}
			d.setSize(600,400);
			d.setVisible(true);
		}

		if(source== buttonP) {
			JPanel paneCl1 = new JPanel(new FlowLayout());
			JPanel paneCl2 = new JPanel(new FlowLayout());
			JLabel labelCl1 = new JLabel("ID order               ");
			JLabel labelCl2 = new JLabel("Id Client              ");
			JLabel labelCl3 = new JLabel("Name Client            ");
			JLabel labelCl4 = new JLabel("Id Product             ");
			JLabel labelCl5 = new JLabel("Name Product           ");
			JLabel labelCl6 = new JLabel("Quantity               ");


			JTextField text1 = new JTextField(8);  //id
			JTextField text2 = new JTextField(8);  //name
			JTextField text3 = new JTextField(8);  //address 
			JTextField text4 = new JTextField(8);  //email
			JTextField text5 = new JTextField(8);  //age
			JTextField text6 = new JTextField(8);  //age

			JButton orderButton = new JButton("Place Order");

			paneCl1.add(labelCl1);
			paneCl1.add(labelCl2);
			paneCl1.add(labelCl3);
			paneCl1.add(labelCl4);
			paneCl1.add(labelCl5);
			paneCl1.add(labelCl6);

			paneCl2.add(text1);
			paneCl2.add(text2);
			paneCl2.add(text3);
			paneCl2.add(text4);
			paneCl2.add(text5);
			paneCl2.add(text6);

			JDialog d = new JDialog(this, "Place order");
			d.setLayout(new GridBagLayout());

			c.gridx = 0;
			c.gridy = 0;
			d.add(paneCl1, c);

			c.gridx = 0;
			c.gridy = 1;
			d.add(paneCl2, c);
			
			c.gridx = 0;
			c.gridy = 2;
			d.add(orderButton, c);
			
			orderButton.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){  
					try {
						(new OrdersBll()).insertOrder(new Orders(Integer.parseInt(text1.getText()),Integer.parseInt(text2.getText()),text3.getText(),Integer.parseInt(text4.getText()),text5.getText(),Integer.parseInt(text6.getText())));
					} catch (ExceptieStock e1) {
						JDialog d1 = new JDialog(d,"Error");
						d1.setLayout(new GridBagLayout());
						
						JLabel errorMessage = new JLabel();
						errorMessage.setText(e1.getMessage());
						
						c.gridx = 0;
						c.gridy = 0;
						d1.add(errorMessage, c);
		
						d1.setSize(400,200);
						d1.setVisible(true);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}  
			});
			
			d.setSize(700,300);
			d.setVisible(true);
			
		}
	}

	public static String[] retrieveNameColumn(Object object) {

		String []a=new String[20];
		int i=0;
		for(Field field:object.getClass().getDeclaredFields()) {
			a[i]=field.getName();
			i++;
		}
		return a;
	}

	public <T> void retrieveProperties(List<T> lista) {

		DefaultTableModel model = new DefaultTableModel();

		int i=0;
		int ok=1;

		for(Object object:lista) {
			String data[]=new String[100];
			for(Field field:object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try {
					value=field.get(object);
					if(ok==1)
						model.addColumn(field.getName());
					data[i]=value.toString();
					i++;


				}
				catch(IllegalArgumentException e) {
					e.printStackTrace();
				}
				catch(IllegalAccessException e) {
					e.printStackTrace();
				}
			} 
			model.addRow(data);
			i=0;
			ok=0;
		}

		JTable j=new JTable(model);
		JScrollPane sp = new JScrollPane(j);

		JDialog d = new JDialog(this, "Result query");
		d.add(sp);
		d.setSize(600,400);
		d.setVisible(true);
	}


	public static void main(String args[]) {
		JFrame frame = new View("Magazin");
		frame.setMinimumSize(new Dimension(400, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}