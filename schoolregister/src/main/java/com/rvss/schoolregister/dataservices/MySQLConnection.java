package com.rvss.schoolregister.dataservices;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.rvss.schoolregister.dataservices.DatabaseConfig;


public class MySQLConnection {

    /*DatabaseConfig databaseConfig = new DatabaseConfig();
    private   final String JDBC_URL = databaseConfig.getJdbcUrl();
    private   final String USERNAME = databaseConfig.getJdbcUsername();
    private    final String PASSWORD = databaseConfig.getJdbcPassword();
    private   final String DRIVER = databaseConfig.getJdbcDriver();*/

   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/RVSS_SCHOOLADMIN";
    private static final String USERNAME = "RVSS_ADMIN";
    private static final String PASSWORD = "tech@Rvss2024";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Method to establish connection
    public  static Connection getConnection() throws SQLException {
        try {
            // Register MySQL JDBC Driver
            System.out.println("DB iNfo "+DRIVER.toString()+"  "+USERNAME.toString()+"  "+PASSWORD.toString());
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found");
        } catch (NullPointerException nep){
            nep.printStackTrace();
        }

        // Establish the connection
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Method to close connection
    public  static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

