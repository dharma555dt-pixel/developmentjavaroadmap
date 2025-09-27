package database;

import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        DatabaseConfig config = new DatabaseConfig();

        // -----------------------------
        // Oracle Operations
        // -----------------------------
        try (Connection oracleConn = DatabaseConnection.getOracleConnection(config)) {
            if (oracleConn != null) {
                String dbName = "ORACLE";
                System.out.println("=== " + dbName + " DB Operations ===");

                oracleConn.createStatement().executeUpdate("TRUNCATE TABLE TEST_TABLE");

                // Insert
                DatabaseOperations.insertData(oracleConn, dbName, "TEST_TABLE", 1, "OracleUser1");
                DatabaseOperations.insertData(oracleConn, dbName, "TEST_TABLE", 2, "OracleUser2");

                // Select
                DatabaseOperations.selectData(oracleConn, dbName, "TEST_TABLE");

                // Update
                DatabaseOperations.updateData(oracleConn, dbName, "TEST_TABLE", 1, "UpdatedOracleUser1");
                DatabaseOperations.selectData(oracleConn, dbName, "TEST_TABLE");

                // Delete
                DatabaseOperations.deleteData(oracleConn, dbName, "TEST_TABLE", 2);
                DatabaseOperations.selectData(oracleConn, dbName, "TEST_TABLE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // -----------------------------
        // MySQL Operations
        // -----------------------------
        try (Connection mysqlConn = DatabaseConnection.getMySQLConnection(config)) {
            if (mysqlConn != null) {
                String dbName = "MYSQL";
                System.out.println("=== " + dbName + " DB Operations ===");

                mysqlConn.createStatement().executeUpdate("TRUNCATE TABLE test_table");

                // Insert
                DatabaseOperations.insertData(mysqlConn, dbName, "test_table", 1, "MySQLUser1");
                DatabaseOperations.insertData(mysqlConn, dbName, "test_table", 2, "MySQLUser2");

                // Select
                DatabaseOperations.selectData(mysqlConn, dbName, "test_table");

                // Update
                DatabaseOperations.updateData(mysqlConn, dbName, "test_table", 1, "UpdatedMySQLUser1");
                DatabaseOperations.selectData(mysqlConn, dbName, "test_table");

                // Delete
                DatabaseOperations.deleteData(mysqlConn, dbName, "test_table", 2);
                DatabaseOperations.selectData(mysqlConn, dbName, "test_table");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
