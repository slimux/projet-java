/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author slimu
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import IService.IGameService;
import Connection.DataSource;
import Class.Game;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author slimu
 */

public class GameService implements  IGameService{

    public Connection connection;
    public PreparedStatement ps;

 public  GameService() {
        connection = DataSource.getInstance().getConnection();
    }

   
    @Override
    public void addgame(Game game) {
        String req = "insert into jeu (titre,genre,date_sortie,note_presse,note_joueur,description,prix,console,image,developpeur) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1,game.getTitre());
            ps.setString(2,game.getGenre());
            ps.setDate(3, (java.sql.Date) game.getDate_sortie());
            ps.setInt(4,game.getNote_presse());
            ps.setInt(5,game.getNote_joueur());
            ps.setString(6,game.getDescription());
            ps.setInt(7,game.getPrix());
            ps.setString(8,game.getConsole());
            ps.setString(9,game.getImage());
            ps.setString(10,game.getDeveloppeur());
        
  
            
            //ps.setInt(2, product.getCreator().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
    }

    @Override
    public void deletegame(int id_Jeu) {

        String req = "delete from jeu where id_Jeu =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id_Jeu);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Override
         public Game findById(int id_Jeu) {
              String req = "select * from jeu where id_Jeu = ?";
        Game game = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id_Jeu);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                game = new Game( resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getString(7),resultSet.getInt(8), resultSet.getString(9),resultSet.getString(10),resultSet.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

   
         @Override
   public List<Game> listerGame(){
          String req = "select * from jeu ";
        List<Game> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Game game = new Game( resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getString(7),resultSet.getInt(8), resultSet.getString(9),resultSet.getString(10),resultSet.getString(11));
               
                list.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
public void updateGame(Game g ){
            String req = "UPDATE jeu set titre = ? ,genre =?, date_sortie  =? , note_presse = ? ,note_joueur=? , description=? , prix=? , console=? , image=?, developpeur=? where id_Jeu=?";

            try {
                ps = connection.prepareStatement(req);

                ps.setString(1, g.getTitre());
                ps.setString(2, g.getGenre());
                ps.setDate(3, (java.sql.Date) g.getDate_sortie());
                ps.setInt(4, g.getNote_presse());
                ps.setInt(5, g.getNote_joueur());
                ps.setString(6, g.getDescription());
                ps.setInt(7, g.getPrix());
                ps.setString(8, g.getConsole());
                ps.setString(9, g.getImage());
                ps.setString(10, g.getDeveloppeur());
             
                ps.setInt(11, g.getId_Jeu());


                ps.executeUpdate();
                System.out.println("DONE UPDATE");
            } catch (Exception ex) {
                //Logger.getLogger(BoutiqueDAO.class.getName()).log(Level.SEVERE, null, ex);
                 ex.printStackTrace();
                
            }
            

}
   public ObservableList<Game> lister() {

        ObservableList <Game> list = FXCollections.observableArrayList();
        ResultSet rs; // pour récupérer le résultat de select
        String req = "SELECT * FROM jeu";
        try {
            ps = connection.prepareStatement(req);
            rs = ps.executeQuery(req);
            
            while (rs.next()) {
               Game g = 
                        new Game();

                g.setId_Jeu(rs.getInt("id_Jeu"));
                g.setTitre(rs.getString("titre"));
                g.setGenre(rs.getString("genre"));
                g.setDate_sortie(rs.getDate("date_sortie"));
                g.setNote_presse(rs.getInt("note_presse"));
                g.setNote_joueur(rs.getInt("note_joueur"));
                g.setDescription(rs.getString("description"));
                g.setPrix(rs.getInt("prix"));
                g.setConsole(rs.getString("console"));
                g.setImage(rs.getString("image"));
                g.setDeveloppeur(rs.getString("developpeur"));
               
                

                list.add(g);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(GameService.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        } 
    

}

    
    }
    


