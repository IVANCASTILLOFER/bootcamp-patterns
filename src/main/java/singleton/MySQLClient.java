package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLClient {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/icastillof";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static MySQLClient instance;

    public static MySQLClient getInstance() {
        if (instance == null) {
            instance = new MySQLClient();
        }
        return instance;
    }

    public List<String> getAllUsers() throws SQLException {
        List<String> users = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name FROM users");

        while(resultSet.next()) {
            users.add(resultSet.getString(1));
        }

        return users;
    }

    private MySQLClient() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException sqlException) {
            System.out.println("Error Message: " + sqlException.getMessage());
            System.out.println("SQLState: " + sqlException.getSQLState());
            System.out.println("Error Code: " + sqlException.getErrorCode());
        }
    }
}