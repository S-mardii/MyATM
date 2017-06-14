
import java.sql.*;

public class MySqlConn {
	//JDBC Driver name and Database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/atm?autoReconnect=true&&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	Connection conn;
	Statement objStatement;
	
	public MySqlConn() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			objStatement = conn.createStatement();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//fetch data from Accounts join User Tables
	public ResultSet fetchAccountData() {
		ResultSet result = null;
		try {
			String sql = "SELECT * FROM accounts as A JOIN users as U ON A.userid = U.id";
			result = objStatement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//update Balance to DB
	public void updateAccountBalance(String cardNumber, double newBalance) {
		try {
			String sql = "UPDATE accounts SET balance = " + newBalance + " WHERE cardNumber = \'" + cardNumber + "\'";
			
			objStatement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
