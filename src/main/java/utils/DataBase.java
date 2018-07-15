package utils;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {
	 //  Database credentials
	
	  
	private Connection connection = null;
	private Statement st;
	private WorkWithMainConfig config = new WorkWithMainConfig();
	
	public void connectToDb() {

		System.out.println("open connection to PostgreSQL JDBC");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
			e.printStackTrace();
			return;
		}

		System.out.println("PostgreSQL JDBC Driver successfully connected");
		
		try {
			System.out.println();
			connection = DriverManager.getConnection(
							"jdbc:postgresql://" 
							+ config.getDbDataProp("dbAddress") 
							+ ":" 
							+ config.getDbDataProp("dbPort")
							+ "/" 
							+ config.getDbDataProp("dbName"),
					config.getDbDataProp("dbUserName"), config.getDbDataProp("dbUserPass"));

		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
			fail("Test Failed");
			return;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println("caught:\r\n" + e);
	        fail("Test Failed");
		}

		if (connection != null) {
			System.out.println("You successfully connected to database now");
		} else {
			System.out.println("Failed to make connection to database");
		}

	}
	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Connection close");
		} else {
			System.out.println("Failed to Connection close");
		}
	}
	
	public String executeQueryToString(String SQLquery) {
		String resultString = "";
		if (connection != null) {
			try {
				
				st = connection.createStatement();
				// Statement позволяет отправлять запросы базе данных
				st.executeQuery(SQLquery);
				ResultSet rs = st.getResultSet();
				
				int x = rs.getMetaData().getColumnCount();
				// Resultset.getMetaData() получаем информацию
				// результирующей таблице
				while (rs.next()) {
					for (int i = 1; i <= x; i++) {
						resultString=resultString+rs.getString(i) + "\t";
					}
					resultString=resultString+"\n\r";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultString;
	}
	
	public List<String> executeQueryToList(String SQLquery) {
		List<String> resultList = new ArrayList<String>();
		String resultString="";
		if (connection != null) {
			try {
				
				st = connection.createStatement();
				// Statement позволяет отправлять запросы базе данных
				st.executeQuery(SQLquery);
				ResultSet rs = st.getResultSet();
				
				int x = rs.getMetaData().getColumnCount();
				// Resultset.getMetaData() получаем информацию
				// результирующей таблице
				while (rs.next()) {
					resultString="";
					for (int i = 1; i <= x; i++) {
						resultString=resultString+rs.getString(i) + "\t";
					}
					resultList.add(resultString);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultList;
	}
	
	public Map<String,String> executeQueryToMap(String SQLquery) {
		Map<String,String> resultMap = new HashMap<String,String>();
		
		if (connection != null) {
			try {
				
				st = connection.createStatement();
				// Statement позволяет отправлять запросы базе данных
				st.executeQuery(SQLquery);
				ResultSet rs = st.getResultSet();
				
				int x = rs.getMetaData().getColumnCount();
				// Resultset.getMetaData() получаем информацию
				// результирующей таблице
				while (rs.next()) {
					for (int i = 1; i <= x; i++) {
						resultMap.put(rs.getMetaData().getColumnName(i),rs.getString(i));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMap;
	}
	
	
}

