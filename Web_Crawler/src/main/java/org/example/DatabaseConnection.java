package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // created connection object...it is an interface which connects
    static Connection connection = null;
    public static Connection getConnection(){
        if(connection!=null)return connection;
        String user="root";
        String pwd="HelloShiva@1234";
        String db="searchEngineApp";
        return getConnection(user, pwd, db);
    }
    private static Connection getConnection(String user, String pwd, String db){
        try{
            //returns the class object
            Class.forName("com.mysql.cj.jdbc.Driver");
            //driver manager to establish a connection between driver and database
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+db+"?user="+user+"&password="+pwd);
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
        return connection;
    }
}
