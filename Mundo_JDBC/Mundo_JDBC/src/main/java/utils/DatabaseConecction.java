package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConecction {
    private static Connection con = null;
    public static Connection getConnection()
    {
        String url = "jdbc:mysql://192.168.56.101:3306/world";
        String user = "user";
        String pass = "user";
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
