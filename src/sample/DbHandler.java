package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbHandler
{

        private static Connection connection;

        public static Connection getConnection() {
            if (connection == null) {
                final String connectionString = "jdbc:mysql://localhost:3306/dormitory";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, e);
                }
                try {
                    connection = DriverManager.getConnection(connectionString, "root", "passworld");
                } catch (SQLException throwables) {
                    Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, throwables);
                }
            }
            return connection;
        }
}
