/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cdkhu
 */
public class DBUtils implements Serializable{
    public static Connection makeConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Furniture";
            Connection con = DriverManager.getConnection(url,"sa","1234567890");
            return con;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
