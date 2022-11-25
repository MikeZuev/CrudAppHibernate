package com.zuev.repositories.databaseimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String PASSWORD_KEY = "password";
    private static final String USERNAME_KEY = "username";
    private static final String DATABASE_URL_KEY = "url";

    static {
        loadDriver();
    }

    private ConnectionManager(){


    }

    public static Connection open(){
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(DATABASE_URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }






    private static void loadDriver(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
