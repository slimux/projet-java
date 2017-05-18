/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import IService.IGameService;
import Connection.DataSource;
import Class.Game;
import Service.GameService;
import javafx.JavaFX;
import javafx.FXMLDocumentController;

/**
 *
 * @author slimu
 */
public class DataSource {
    

    
    static String url = "jdbc:mysql://localhost:3306/project";
    static String user = "root";
    static String pwd = "";
    private static  Connection connection;
    private static DataSource instance;
    private static Statement ste;
    private DataSource()
    {
         try {
        connection = DriverManager.getConnection(url, user, pwd);
        System.out.println("connexion établie");
        ste=connection.createStatement();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
    }
    public static DataSource getInstance()
    {
        if (instance==null)
          instance=new DataSource();
        return instance ;
    }
    public Connection getConnection() {
        return connection;
    }

}