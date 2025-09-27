package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private Properties props = new Properties();

    public DatabaseConfig() {
        try (FileInputStream fis = new FileInputStream("src/test/resources/database.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOracleUrl() {
        return props.getProperty("oracle.url");
    }

    public String getOracleUsername() {
        return props.getProperty("oracle.username");
    }

    public String getOraclePassword() {
        return props.getProperty("oracle.password");
    }

    public String getOracleRole() {
        return props.getProperty("oracle.role");
    }

    public String getMySQLUrl() {
        return props.getProperty("mysql.url");
    }

    public String getMySQLUsername() {
        return props.getProperty("mysql.username");
    }

    public String getMySQLPassword() {
        return props.getProperty("mysql.password");
    }
}
