package webd4201.nazirm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * DatabaseConnect class to connect to database
 *
 * @author Darren Puffer, Musab Nazir
 * @version 1.0 (2018 February 1st)
 * @since 1.0
 */
class DatabaseConnect
{
	/**
	 * Database location
	 */
	private static final String url = "jdbc:postgresql://127.0.0.1:5432/webd4201_db";
	/**
	 * Connection object to open port to db
	 */
	private static Connection aConnection;
	/**
	 * database user
	 */
	private static final String user = "webd4201_admin";
	/**
	 * database user password
	 */
	private static final String password = "webd4201_password";
	
	/**
	 * establishes the database connection
	 * @return Connection to the webd4201example_db database
	 */
	public static Connection initialize()
	{
		try
 		{ 	
			Class.forName("org.postgresql.Driver"); // loads the JDBC Driver for PostGreSQL
			aConnection = DriverManager.getConnection(url, user, password); // creates the database connection instance
	    	
	 	}
		catch (ClassNotFoundException e)  //will occur if you did not import the PostGreSQL *.jar file with drivers
		{
			System.out.println(e);
		}
		catch (SQLException e)	//any other database exception (misnamed db, misnamed user, worng password, etc)
			{ e.printStackTrace(); }
		return aConnection;
	}

	/**
	 * closes the database connection
	 */
	public static void terminate()
	{
		try
 		{
    		aConnection.close();
		}
		catch (SQLException e)
			{ System.out.println(e);	}
	}
}
