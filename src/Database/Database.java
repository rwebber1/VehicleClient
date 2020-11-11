package Database;

import java.sql.*;

/*
Database class based off Singleton pattern to allow DAO class to access the SQLite database
 */
public class Database {
    private static Connection conn = null;

    //Prevent any other instantiation of db
    private Database() {
        String path = "jdbc:sqlite:C://Users//Riley Webber//Desktop//MitchellCodingChallenge/vehicles.db";

    //If database exists, connect to it
    //If database does not exist, create and connect
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(path);

            String tableCreate = "CREATE TABLE IF NOT EXISTS Vehicles (id INTEGER PRIMARY KEY," +
                                                                    " year INTEGER," +
                                                                    " make VARCHAR(30) NOT NULL," +
                                                                    " model VARCHAR(30) NOT NULL)";
            Statement stmt = conn.createStatement();
            stmt.execute(tableCreate);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //Function to gain access to single database connection
    public static Connection getConn() {
        if (conn == null) {
            Database db = new Database();
        }
        return conn;
    }

    //Not sure if necessary
    public static void close() throws SQLException {
        conn.close();
    }
}