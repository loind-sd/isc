/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Shado
 */
public class MSSQLConnection {

    public static final String HOSTNAME = "localhost";
    public static final String PORT = "1433";
    public static final String DBNAME = "baloshop";
    public static final String INTEGRATED_SECURITY = "false";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "mothernumber1@";

    /**
     * Get connection to MSSQL Server
     *
     * @return Connection
     */
    public static Connection getConnection() {
        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME
                + ":" + PORT + ";"
                + "databaseName=" + DBNAME + ";"
                + "integratedSecurity=" + INTEGRATED_SECURITY + ";";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Where is your MSSQL JDBC Driver?");
            return null;
        }
        System.out.println("MSSQL JDBC Driver Registered!");

        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.err.println("Connection Failed! Check output console");
            return con;
        }
        return con;
    }

    /**
     * Close connection to MSSQL Server
     *
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Close connection failed!");
            }
        }
    }

    /**
     * Close PreparedStatement to MSSQL Server
     *
     * @param ps
     */
    public static void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Close PreparedStatement failed!");
            }
        }
    }

    /**
     * Close ResultSet to MSSQL Server
     *
     * @param rs
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Close ResultSet failed!");
            }
        }
    }
}
