package PPFISM;

import javax.swing.*;
import java.sql.*;

public class MyDataBase {
    private static Connection conn;

    public static void createConnection(String db)//Creating connection
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, "root", "root");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception in creating connection:" + e);
        }
    }

    public static void close()//Closing connection
    {
        try {
            conn.close();
        } catch (SQLException ex) {
        }
    }

    public static void commit() {
        if (conn == null)
            return;
        try {
            conn.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception raised while commiting the connection.");
        }
    }

    public static PreparedStatement createStatement(String query) throws SQLException//Creating statement
    {
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt;
    }

    public static ResultSet executeQuery(String query) throws SQLException//Executing ResultSet queries
    {
        PreparedStatement stmt = createStatement(query);
        ResultSet rs = stmt.executeQuery();
        stmt.close();
        return rs;
    }

    public static int executeUpdate(String query) throws SQLException//Executing Database Queries
    {
        PreparedStatement stmt = createStatement(query);
        int n = stmt.executeUpdate();
        stmt.close();
        return n;

    }
}
