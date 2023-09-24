import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
	private static Connection connection = null;
	private static Statement statement = null;
	
	private static final String BASE_URL = "jdbc:mysql://localhost:3306/";

	public static void start() {
		try {
			connection = DriverManager.getConnection(BASE_URL + "g&m", "root", "");
			statement = connection.createStatement();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if(connection == null) {
			start();
		}
		return connection;
	}
	
	public static Statement getStatement() {
		if(statement == null) {
			start();
		}
		return statement;
	}
	
	public static ResultSet getById(DBEntity entity, String id_column, String id) {
		ResultSet rs = null;
		
		try {	
			String psQuery = "SELECT * FROM " + entity.databaseName;
			psQuery += " WHERE " + id_column + " = ?";
			
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement(psQuery);
			
			ps.setString(1, id);
			
			rs = ps.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static ResultSet getAll(DBEntity entity, String[] condition_names, String[] condition_values){
		ResultSet rs = null;
		
		try {	
			String psQuery = "SELECT * FROM " + entity.databaseName;
			
			if(condition_names.length > 0) psQuery += " WHERE ";
			
			for(int i = 0; i < condition_names.length; i++) {
				if(condition_values.length > i) {
					psQuery += condition_names[i] + " = ?";
					if(i+1 < condition_names.length && i+1 < condition_values.length) {
						psQuery += " AND ";
					}
				}
			}
			
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement(psQuery);
			
			for(int i = 0; i < condition_names.length; i++) {
				if(condition_values.length > i) {
					ps.setString(i+1, condition_values[i]);
				}
			}
			
			rs = ps.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}