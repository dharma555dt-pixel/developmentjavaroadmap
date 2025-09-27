package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperations {

    // INSERT data
    public static void insertData(Connection conn, String dbName, String tableName, int id, String name) {
        String sql = "INSERT INTO " + tableName + " (id, name) VALUES (?, ?)";
        System.out.println("[" + dbName + "][INSERT] " + sql.replace("?", id + ", '" + name + "'"));
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            int rows = stmt.executeUpdate();
            System.out.println("[" + dbName + "] Inserted " + rows + " row(s) into " + tableName + "\n");
        } catch (SQLException e) {
            System.err.println("[" + dbName + "][INSERT ERROR] " + e.getMessage());
        }
    }

    // SELECT all data
    public static void selectData(Connection conn, String dbName, String tableName) {
        String sql = "SELECT * FROM " + tableName;
        System.out.println("[" + dbName + "][SELECT] " + sql);
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("[" + dbName + "] Data from table: " + tableName);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("[" + dbName + "] id=" + id + ", name=" + name);
            }
            System.out.println("-----------------------------------\n");
        } catch (SQLException e) {
            System.err.println("[" + dbName + "][SELECT ERROR] " + e.getMessage());
        }
    }

    // UPDATE data
    public static void updateData(Connection conn, String dbName, String tableName, int id, String newName) {
        String sql = "UPDATE " + tableName + " SET name = ? WHERE id = ?";
        System.out.println("[" + dbName + "][UPDATE] " + sql.replace("?", "'" + newName + "'").replace("?", String.valueOf(id)));
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            System.out.println("[" + dbName + "] Updated " + rows + " row(s) in " + tableName + "\n");
        } catch (SQLException e) {
            System.err.println("[" + dbName + "][UPDATE ERROR] " + e.getMessage());
        }
    }

    // DELETE data
    public static void deleteData(Connection conn, String dbName, String tableName, int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        System.out.println("[" + dbName + "][DELETE] " + sql.replace("?", String.valueOf(id)));
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("[" + dbName + "] Deleted " + rows + " row(s) from " + tableName + "\n");
        } catch (SQLException e) {
            System.err.println("[" + dbName + "][DELETE ERROR] " + e.getMessage());
        }
    }
}
