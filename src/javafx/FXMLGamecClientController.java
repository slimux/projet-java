/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import Class.Game;
import IService.IGameService;
import Service.GameService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author slimu
 */
public class FXMLGamecClientController implements Initializable {
 
    
    
    @FXML
    private Label date;

    @FXML
    private Label note_lecteur;

    @FXML
    private Label genre;
    

    
    @FXML
    private Text test;

            
    @FXML
    private Label platforme;

    @FXML
    private Label GameTitre;

    @FXML
    private ImageView imgVw;

    @FXML
    private Label note_redaction;
     @FXML
    private Label prix;

    @FXML
    private Label editeur;
    @FXML
    private TableView<Game> table_jeu;

    @FXML
    private TableColumn<?, ?> Ctitre_jeu;
       ObservableList <Game> data = FXCollections.observableArrayList();

       Game game = new Game();
        IGameService service=new GameService();
    
    private void buildTableviewData() {
       
    
        Ctitre_jeu.setCellValueFactory(new PropertyValueFactory<>("titre"));
        
               
    
        GameService gp = new GameService() {};
        
        data = gp.lister();
        table_jeu.setItems(null);
        table_jeu.setItems(data);
      
        
      }
        

    private void GameData() {
        table_jeu.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Game> observable,
                Game oldValue,
                Game newValue) -> {
            if (newValue == null) {
              //  editID = 0;
               
                GameTitre.setText(null);
                imgVw.setImage(null);
                note_redaction.setText(null);
                note_lecteur.setText(null);
                platforme.setText(null);
                editeur.setText(null);
                genre.setText(null);
                test.setText(null);
                
                return;
            }
                     
               
           //    String date = .getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
              GameTitre.setText(newValue.getTitre());  
             
              test.setText(newValue.getDescription());
              note_redaction.setText(String.valueOf(newValue.getNote_presse()));
              note_lecteur.setText(String.valueOf(newValue.getNote_joueur()));  
              platforme.setText(newValue.getConsole());
              editeur.setText(newValue.getDeveloppeur());
              genre.setText(newValue.getGenre());
              date.setText(newValue.getDate_sortie().toString());
              prix.setText(String.valueOf(newValue.getPrix()));
              
      
        Image img = new Image("file:///C:/Users/slimu/Desktop/image/"+newValue.getImage()) ;
      System.out.println("file:///C:/Users/slimu/Desktop/image/"+newValue.getImage());
      imgVw.setImage(img);
      
      
           
        });
    }
       
    @FXML
    void GotoAstuceclient(ActionEvent event) throws IOException {
            Parent p1 = FXMLLoader.load(getClass().getResource("FXMLAstuceClient.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();
                

    }
    
            @FXML
    void Gotoformulaire(ActionEvent event) throws IOException {
           
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLFormulaire.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
           
            stage.setScene(new Scene(root1));  
            stage.show();
                

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildTableviewData();
        GameData();
    }    
    
}
