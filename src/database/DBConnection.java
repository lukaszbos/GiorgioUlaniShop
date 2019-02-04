package database;


import java.sql.*;

/**
 * This class provides the most important methods to connect to database and retrive data from database
 */
public class DBConnection {

    /**
     * Connecting with database
     * @param connection
     * @return
     */
    public static Connection getConnection(Connection connection) {
        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return null;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", "lmichows", "7KKKFsm");
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return connection;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }


    /**
     * Getting data form db
     *
     * @param sql sql code
     */
    public static ResultSet getColumn(String sql, Connection connection) {

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("blad przy stmt");
            e.printStackTrace();

        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("blad przy rs");

            e.printStackTrace();
        }
        System.out.println("Result Set query: " + rs);
        System.out.println("Goodbye!");

        return rs;
    }


}