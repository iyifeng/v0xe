package com.v0ex.concurrency.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionDispenser
 * <p/>
 * Using ThreadLocal to ensure thread confinement
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ConnectionDispenser {
    static String DB_URL = "jdbc:mysql://localhost/mydatabase";

    private ThreadLocal<Connection> connectionHolder
            = new ThreadLocal<Connection>(){
        @Override
        public Connection initialValue(){
            try{
                return DriverManager.getConnection(DB_URL);
            }catch (SQLException e){
                throw new RuntimeException("Unable to acquire Connection",e);
            }
        }
    };
    public Connection getConnection(){
        return connectionHolder.get();
    }
}
