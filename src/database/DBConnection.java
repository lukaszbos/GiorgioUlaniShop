package database;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection(Connection connection) {
        System.out.println("-------- Oracle JDBC Connection Testing ------");
        if (isYourDriverAvailable()) return null;
        return connectToDatabase(connection);
    }

    private static boolean isYourDriverAvailable() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private static Connection connectToDatabase(Connection connection) {
        try {
            connection = connectToOracleDatabase();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return connection;
        }
    }

    private static Connection connectToOracleDatabase() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "lmichows", "7KKKFsm");
    }

    public static ResultSet getColumn(String sql, Connection connection) {
        Statement sqlStatement = getSqlStatement(connection);
        ResultSet sqlResultSet = setSqlResult(sql, sqlStatement);
        return sqlResultSet;
    }

    private static Statement getSqlStatement(Connection connection) {
        Statement sqlStatement = null;
        try {
            sqlStatement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("blad przy sqlStatement");
            e.printStackTrace();
        }
        return sqlStatement;
    }

    private static ResultSet setSqlResult(String sql, Statement sqlStatement) {
        ResultSet sqlResultSet = null;
        try {
            sqlResultSet = sqlStatement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("blad przy sqlResultSet");
            e.printStackTrace();
        }
        return sqlResultSet;
    }
}