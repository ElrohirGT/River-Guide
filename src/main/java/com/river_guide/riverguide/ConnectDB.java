package com.river_guide.riverguide;
import java.sql.*;

public class ConnectDB {
    Connection conn= null;
    public static Connection ConnectMariaDB(){
        //Connection conn= null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/rios","Grupo3","POO2022");
            System.out.println("Connection Success");
            return conn;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
