package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.ParameterizedType;
import connection.ConnectionFactory;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;

public class AbstractDAO<T> {
	private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
	private final Class<T> type;

	public AbstractDAO() {
		this.type=(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private String createSelectQuery(String field) {
		StringBuilder sb=new StringBuilder();
		sb.append("select ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE "+field+" =?");
		return sb.toString();
	}

	public String createInsertQuery()
	{
		StringBuilder fields= new StringBuilder();
		StringBuilder vars= new StringBuilder();
		for(Field field : type.getDeclaredFields())
		{
			String name=field.getName();
			if(fields.length()>1)
			{
				fields.append(",");
				vars.append(",");
			}
			fields.append(name);
			vars.append("?");
		}
		String table=type.getSimpleName();
		String Sql="INSERT INTO " +table + "(" + fields.toString() + ") VALUES(" + vars.toString() + ")";
		return Sql; 
	}
	
	public String createUpdateQuery(String setCol, String setCol1)
	{
		String table=type.getSimpleName();
		String Sql="Update " +table + " Set "+setCol+"=?"+" where "+setCol1+"=?";
		return Sql; 
	}
	
	public String createDeleteQuery(String setCol)
	{
		String table=type.getSimpleName();
		String Sql="Delete from " +table +" where "+setCol+"=?";
		return Sql; 
	}

	public List<T> findById(String setCol,String a) {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		String query=createSelectQuery(setCol);
		try {

			connection=ConnectionFactory.getConnection();
			statement=connection.prepareStatement(query);
			statement.setObject(1, a);

			resultSet=statement.executeQuery();
			return createObjects(resultSet);
		}
		catch(SQLException e){
			LOGGER.log(Level.WARNING, type.getName()+"DAO findById ", e.getMessage());
		}
		finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			//ConnectionFactory.close(connection);
		}
		return null;
	}

	public List<T> findAll() {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		String query="Select * from "+type.getSimpleName();
		try {

			connection=ConnectionFactory.getConnection();
			statement=connection.prepareStatement(query);
			
            System.out.println(statement);
			resultSet=statement.executeQuery();
			return createObjects(resultSet);
		}
		catch(SQLException e){
			LOGGER.log(Level.WARNING, type.getName()+"DAO findALL ", e.getMessage());
		}
		finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			//ConnectionFactory.close(connection);
		}
		return null;
	}
	
	

	public void insert (Object pr) {
		Connection connection=null;
		PreparedStatement statement=null;
		String query=createInsertQuery();
		try {

			connection=ConnectionFactory.getConnection();
			statement=connection.prepareStatement(query);

			Class<?> zclass=pr.getClass();
			Field[] fields=zclass.getDeclaredFields();
			for(int i=0;i<fields.length;i++)
			{
				Field field=fields[i];
				field.setAccessible(true); 
				Object value=field.get(pr);
				statement.setObject((i+1), value);
			}
			statement.executeUpdate();
		}
		catch(SQLException e){
			LOGGER.log(Level.WARNING, type.getName()+"DAO insert ", e.getMessage());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			ConnectionFactory.close(statement);
			//ConnectionFactory.close(connection);
		}
	}
	
	public void update (String setCol, String setVal, String setCol1, String setVal1) {
		Connection connection=null;
		PreparedStatement statement=null;
		String query=createUpdateQuery(setCol, setCol1);
		try {

			connection=ConnectionFactory.getConnection();
			statement=connection.prepareStatement(query);
            statement.setObject(1, setVal);
            statement.setObject(2, setVal1);
			
			statement.executeUpdate();
		}
		catch(SQLException e){
			LOGGER.log(Level.WARNING, type.getName()+"DAO insert ", e.getMessage());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			ConnectionFactory.close(statement);
			//ConnectionFactory.close(connection);
		}
	}
	
	public void delete (String setCol, String setVal) {
		Connection connection=null;
		PreparedStatement statement=null;
		String query=createDeleteQuery(setCol);
		try {

			connection=ConnectionFactory.getConnection();
			statement=connection.prepareStatement(query);
            statement.setObject(1, setVal);

			statement.executeUpdate();
		}
		catch(SQLException e){
			LOGGER.log(Level.WARNING, type.getName()+"DAO insert ", e.getMessage());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			ConnectionFactory.close(statement);
			//ConnectionFactory.close(connection);
		}
	}
	

	private List<T> createObjects(ResultSet resultSet){
		List<T> list=new ArrayList<T>();
		try {
			while(resultSet.next()) {
				T instance=type.newInstance();
				for(Field field : type.getDeclaredFields()) {
					Object value=resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getName(),type);
					Method method=propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);			
			}
		}
		catch(Exception e) {
			System.out.println("");
		}
		return list;
	}
}
