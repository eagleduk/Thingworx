package com.SJLEE;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.thingworx.entities.utils.ThingUtilities;
import com.thingworx.persistence.postgres.PostgresDatasource;
import com.thingworx.persistence.provider.IPersistenceConnection;
import com.thingworx.persistence.provider.PersistenceProvider;
import com.thingworx.persistence.provider.PersistenceProviderManager;
import com.thingworx.things.database.DatabaseSystem;


public class DBConnect {
	static private DBConnect instance;
	//static private int clients;

	//private Vector<Driver> drivers = new Vector<Driver>();
	//private Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();
	
	static public DBConnect getInstance() throws IOException {
		if(instance == null) {
			instance = new DBConnect();
		}
		//clients++;
		return instance;
	}
	
	private DBConnect() throws IOException {
		initialization();
	}
	
	private void initialization() throws IOException {
//		InputStream is = getClass().getResourceAsStream("/com/hhi/resouce/db.properties");
//		System.out.println(is);
//		Properties props = new Properties();
//		try {
//			props.load(is);
//		} catch(Exception e) {
//			return;
//		}
//		loadDrivers(props);
//		createPool(props);
	}
	
	private void loadDrivers(Properties props) {
//		Enumeration propNames = props.propertyNames();
//		String className = props.getProperty("driver");
//		while(propNames.hasMoreElements()) {
//			String name = (String)propNames.nextElement();
//			if(name.endsWith(".driver")) {
//				String poolName = name.substring(0, name.lastIndexOf("."));
//				className = props.getProperty(poolName + ".driver");
//			}
//		}
//		
//		StringTokenizer st = new StringTokenizer(className);
//		while(st.hasMoreElements()) {
//			String stName = st.nextToken().trim();
//			try {
//				Driver driver = (Driver)Class.forName(stName).newInstance();
//				DriverManager.registerDriver(driver);
//				drivers.addElement(driver);
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}

	public void freeConnection(String name, Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static long l_count = 1; 
	
	public Connection getConnection(String name) {
//		DBConnectionPool pool = (DBConnectionPool)pools.get(name);
//		if(pool != null) {
//			return pool.getConnection();
//		}
//		return null;
		
		
		PersistenceProvider provider = PersistenceProviderManager.getInstance().getEntity("HHI_PersistenceProvider");
		//.getPersistenceConnection()
		
		
		//DatabaseSystem databaseSystem = (DatabaseSystem)ThingUtilities.findThing(name);
		//System.out.println("provider === ");
		Connection con = null;
		try {
			
			PostgresDatasource datasource = (PostgresDatasource)provider.getPersistenceConnection();
			int connectionCount = datasource.getNumConnections();
			
			
			if((++l_count % 1000) == 0){
				System.out.println("datasource.getNumConnections()  == " + connectionCount);
				if(l_count > 10000L){
					l_count = 1;
				}
			}
//			if(connectionCount > 30){
//				System.out.println("datasource.getNumConnections()  == " + connectionCount);
//			}
			
			con = (Connection)datasource.getConnection();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
	
//	public Connection getConnection(String name, long time) {
////		DBConnectionPool pool = (DBConnectionPool)pools.get(name);
////		if(pool != null) {
////			return pool.getConnection(time);
////		}
////		return null;	
//		DatabaseSystem databaseSystem = (DatabaseSystem)ThingUtilities.findThing(name);
//		
//		Connection con = null;
//		try {
//			con = databaseSystem.getDataSource().getConnection();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return con;
//	}
	
	public synchronized void release() {
//		if(--clients != 0) {
//			return;
//		}
//		
//		Enumeration allPools = pools.elements();
//		while(allPools.hasMoreElements()) {
//			DBConnectionPool pool = (DBConnectionPool)allPools.nextElement();
//			pool.release();
//		}
//		
//		Enumeration allDrivers = drivers.elements();
//		while(allDrivers.hasMoreElements()) {
//			Driver driver = (Driver)allDrivers.nextElement();
//			try {
//				DriverManager.deregisterDriver(driver);
//			} catch(SQLException e) {
//				
//			}
//		}
	}
	
	private void createPool(Properties props) {
//		Enumeration propNames = props.propertyNames();
//		while(propNames.hasMoreElements()) {
//			String name = (String)propNames.nextElement();
//			if(name.endsWith(".url")) {
//				String poolName = name.substring(0, name.lastIndexOf("."));
//				String url = props.getProperty(poolName + ".url");
//				if(url == null) {
//					continue;
//				}
//				String user = props.getProperty(poolName + ".user");
//				String password = props.getProperty(poolName + ".password");
//				String maxconn = props.getProperty(poolName + ".maxconn", "0");
//				int max;
//				try {
//					max = Integer.valueOf(maxconn).intValue();
//				} catch(NumberFormatException e) {
//					max = 0;
//				}
//				
//				DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
//				pools.put(poolName, pool);
//			} // end if
//		}
	}
	
	
	public static void closeDBConnection(ResultSet rs, Statement st) throws Exception {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
}