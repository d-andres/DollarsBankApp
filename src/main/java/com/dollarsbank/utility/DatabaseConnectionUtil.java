package com.dollarsbank.utility;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * An object used to connect to a SQL database. 
 */
public class DatabaseConnectionUtil {
    public static final String URL = "jdbc:mysql://localhost:3306/dollarsbank";
    public static final String USER = "root";
    public static final String PASS = "root";
    
    /**
     * Returns a Connection object with the given url, username, and password for a SQL database.
     * @return the Connection object containing url, username, and password
     */
    public static Connection getConnection()
    {
      try {
          DriverManager.registerDriver(new Driver());
          return DriverManager.getConnection(URL, USER, PASS);
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
    }
}