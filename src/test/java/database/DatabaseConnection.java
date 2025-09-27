package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    public static Connection getOracleConnection(DatabaseConfig config) {
        Connection conn = null;
        try {
            Properties props = new Properties();
            props.put("user", config.getOracleUsername());
            props.put("password", config.getOraclePassword());
            props.put("internal_logon", config.getOracleRole()); // SYSDBA
            conn = DriverManager.getConnection(config.getOracleUrl(), props);
            System.out.println("Connected to Oracle DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getMySQLConnection(DatabaseConfig config) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(config.getMySQLUrl(),
                    config.getMySQLUsername(),
                    config.getMySQLPassword());
            System.out.println("Connected to MySQL DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
