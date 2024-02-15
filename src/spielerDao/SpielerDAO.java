package spielerDao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spieler.Spieler;

public class SpielerDAO
{
	private Connection myConn;

	public SpielerDAO()
	{
		String url = "jdbc:mysql://localhost:3306/usersdb";
		String user = "root";
		String password = "M0949250125m";

//		connect to database
		try
		{
			myConn = DriverManager.getConnection(url, user, password);
			System.out.println("DB connection successful!");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		
	}

	public List<Spieler> getAllSpieler() throws Exception
	{
		List<Spieler> list = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try
		{
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from spieler");

			while (myRs.next())
			{
				Spieler tempSpieler = convertRowToSpieler(myRs);
				list.add(tempSpieler);
			}

			return list;
		} 
		finally
		{
			close(myStmt, myRs);
		}
	}

	public List<Spieler> searchSpieler(String nachName) throws Exception
	{
		List<Spieler> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try
		{
			nachName += "%";
			myStmt = myConn.prepareStatement("select * from spieler where nach_name like ?");
			
			myStmt.setString(1, nachName);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next())
			{
				Spieler tempSpieler = convertRowToSpieler(myRs);
				list.add(tempSpieler);
			}
			
			return list;
		} 
		finally
		{
			close(myStmt, myRs);
		}
	}

	private Spieler convertRowToSpieler(ResultSet myRs) throws SQLException
	{
		String nachName = myRs.getString("nach_name");
		String vorName = myRs.getString("vor_name");
		String email = myRs.getString("email");

		Spieler tempSpieler = new Spieler(nachName, vorName, email);

		return tempSpieler;
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException
	{

		if (myRs != null)
		{
			myRs.close();
		}

		if (myStmt != null)
		{

		}

		if (myConn != null)
		{
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException
	{
		close(null, myStmt, myRs);
	}

	public static void main(String[] args) throws Exception
	{

		SpielerDAO dao = new SpielerDAO();
		System.out.println(dao.searchSpieler("Brown"));
	}

	public void addSpieler(Spieler dieSpieler) throws Exception
	{
		PreparedStatement myStmt = null;
		
		try
		{
//			prepare Statement
			myStmt = myConn.prepareStatement("insert into spieler"
					+ " (vor_name, nach_name, email)"
					+ " values (?, ?, ?)");
			
//			set params
			myStmt.setString(1, dieSpieler.getVorName());
			myStmt.setString(2, dieSpieler.getNachName());
			myStmt.setString(3, dieSpieler.getEmail());
			
//			execute SQL
			myStmt.executeUpdate();
		} 
		finally
		{
			close(myStmt);
		}
	}
	
	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);		
	}
}
