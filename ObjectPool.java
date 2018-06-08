/*
 * Author: Quanfeng Du 
 * Data: June 6, 2018
 * Objective: demo the Factory Method pattern
 * Object pools (otherwise known as resource pools) are used to 
 * manage the object caching. A client with access to a Object 
 * pool can avoid creating a new Objects by simply asking the pool
 *  for one that has already been instantiated instead
 */
package DesignPattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

abstract class Pool<T>{
	private long expirationTime;
	private Hashtable<T, Long> locked, unlocked;
	public Pool() {
		expirationTime=30000;
		locked=new Hashtable<T, Long>();
		unlocked=new Hashtable<T, Long>();
	}
	
	protected abstract T create();
	public abstract boolean validate(T o);
	public abstract void expire(T o);
	
	public synchronized T checkOut() {
		long now=System.currentTimeMillis();
		T t;
		if(unlocked.size()>0) {
			Enumeration<T> e=unlocked.keys();
			while(e.hasMoreElements()) {
				t=e.nextElement();
				if((now-unlocked.get(t))>expirationTime) {
					unlocked.remove(t);
					expire(t);
					t=null;
				}else {
					if(validate(t)) {
						unlocked.remove(t);
						locked.put(t, now);
						return t;
					}else {
						unlocked.remove(t);
						expire(t);
						t=null;
					}
				}
			}
		}
		t=create();
		locked.put(t,  now);
		return t;
	}
	
	public synchronized void checkIn(T t) {
		locked.remove(t);
		unlocked.put(t, System.currentTimeMillis());
	}
}

class JDBCConnectionPool extends Pool<Connection>{
	private String dsn, usr, pwd;
	public JDBCConnectionPool(String driver, String dsn, String usr, String pwd) {
		super();
		try {
			Class.forName(driver).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		this.dsn=dsn;
		this.usr=usr;
		this.pwd=pwd;
	}
	@Override 
	protected Connection create() {
		try {
			return (DriverManager.getConnection(dsn, usr, pwd));
		}catch(SQLException e){
			e.printStackTrace();
			return (null);
		}
	}
	
	@Override 
	public void expire(Connection o) {
		try {
			((Connection) o).close();
		}
		catch(SQLException e) {
			e.printStackTrace();()
		}
	}
	
	@Override
	public boolean validate(Connection o) {
		try {
			return (!((Connection) o).isClosed());
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
}



public class ObjectPool {
	public static void main(String[] args) {
		
		//Create Connnection pool
		JDBCConnectionPool pool =new JDBCConnectionPool(
				"org.hsqldb.jdbcDriver", "jdbc:hsqldb://localhost/mysql",
				"sa", "secret");
		
		//Get a connection
		Connection con=pool.checkOut();
		
		// use the Connection do Something
		
		
		//return the connection
		pool.checkIn(con);
					
	}
}
