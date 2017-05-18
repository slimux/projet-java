/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import IService.IAstuceService;
import Connection.DataSource;
import Class.Astuce;
import Class.Game;
import Service.AstuceService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author slimu
 */
public class AstuceService implements IAstuceService {
     private Connection connection;
    private PreparedStatement ps;

    public AstuceService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void addAstuce(Astuce astuce) {
          String req = "insert into astuce (jeu_id,title_astuce,description_astuce,image_astuce) values (?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1,astuce.getJeuId());
            ps.setString(2,astuce.getTitle_astuce());
            ps.setString(3,astuce.getDescription_astuce());
            ps.setString(4,astuce.getImage_astuce());
            
           //  ps.setDate(10,game.getDate());
            //ps.setInt(2, product.getCreator().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
       
    }
    

    @Override
    public void deleteAstuce(int id_astuce) {
         String req = "delete from astuce where id_astuce =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id_astuce);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Astuce findByIdAs(int id_astuce) {
         String req = "select * from astuce where id_astuce = ?";
        Astuce astuce = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id_astuce);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                astuce = new Astuce( resultSet.getInt(1),resultSet.getInt(2), resultSet.getString(3),resultSet.getString(4), resultSet.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return astuce;
        
    }

    @Override
    public void modifAstuce(Astuce astuce) {
        String req = "UPDATE astuce set  jeu_id = ?, title_astuce = ? ,description_astuce =? ,image_astuce=? where id_astuce=?  " ;

            try {
                ps = connection.prepareStatement(req);

            ps.setInt(1,astuce.getJeuId());
            ps.setString(2,astuce.getTitle_astuce());
            ps.setString(3,astuce.getDescription_astuce());
            ps.setString(4,astuce.getImage_astuce());
             ps.setInt(5,astuce.getIdAstuce());
               

                ps.executeUpdate();
                System.out.println("DONE UPDATE");
            } catch (Exception ex) {
                //Logger.getLogger(BoutiqueDAO.class.getName()).log(Level.SEVERE, null, ex);
                 ex.printStackTrace();
                
            }
        
    }

    @Override
    public List<Astuce> getAll() {
          String req = "select * from astuce ";
        List<Astuce> astuces= new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Astuce astuce = new Astuce(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                astuces.add(astuce);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return astuces;
        
        
    }
    
     public ObservableList<Astuce> lister() {

        ObservableList <Astuce> list = FXCollections.observableArrayList();
        ResultSet rs; // pour récupérer le résultat de select
        String req = "SELECT * FROM astuce";
        try {
            ps = connection.prepareStatement(req);
            rs = ps.executeQuery(req);
            
            while (rs.next()) {
               Astuce a = new Astuce();

                a.setIdAstuce(rs.getInt("id_astuce"));
                a.setJeuId(rs.getInt("jeu_id"));
                a.setTitle_astuce(rs.getString("title_astuce"));
                a.setDescription_astuce(rs.getString("description_astuce"));
                a.setImage_astuce(rs.getString("image_astuce"));
                

                list.add(a);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AstuceService.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        } 
    

}
    
}

