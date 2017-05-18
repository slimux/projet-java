/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;


import Class.Astuce;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.function.Predicate;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;

import Connection.DataSource;
import IService.IAstuceService;
import Service.AstuceService;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static javafx.FXMLDocumentController.editID;
import javafx.stage.FileChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author slimu
 */
public class FXMLastuceController implements Initializable {

    /**
     * Initializes the controller class.
     */
 @FXML
    private Button upload;

    @FXML
    private TableColumn<Astuce, Integer> Ctitre_jeu;
        @FXML
    private TableColumn<?, ?> Ctitre_astuce;
    @FXML
    private Button editAstuce;
    @FXML
    private Button btnNew;
    @FXML
    private TextField txtSearch;

    @FXML
    private TableColumn<?, ?> Cdescription;

    @FXML
    private Button button;
    
    @FXML
    private Button delete;

    @FXML
    private TextField Titre_astuce;

    @FXML
    private  TextArea desc_j;

    @FXML
    private Label photopath;

    @FXML
    private TableColumn<?, ?> Cid_astuce;

    @FXML
    private TableColumn<?, ?> Cimage;

    @FXML
    private TextField AstuceID;

    @FXML
    private TableView<Astuce> table_astuce;

    @FXML
    private Label photoname;
        @FXML
    private ComboBox<Integer> myComboBox;


    @FXML
    private ImageView imgVw;
     ObservableList <Astuce> data1 = FXCollections.observableArrayList();
     final private ObservableList data2=FXCollections.observableArrayList();
    
     Astuce astuce = new Astuce();
        IAstuceService service=new AstuceService();
        String oldName;

    
        

    @FXML
    void AddAstuce(ActionEvent event) {
        astuce.setJeuId(myComboBox.getValue()); 
     astuce.setTitle_astuce(Titre_astuce.getText());
        astuce.setDescription_astuce(desc_j.getText());
         astuce.setImage_astuce(oldName);
        if(photoname.getText()!="")
        {
         astuce.setImage_astuce(photoname.getText());
        }     
        service.addAstuce(astuce);
        buildTableviewData();
        notificattion();
        Titre_astuce.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
               {
                if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç0123456789. ]")) 
               {
                    Titre_astuce.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç0123456789. ]", ""));
                }
                
        });
           desc_j.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
               {
                if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç0123456789. ]")) 
               {
                    desc_j.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç0123456789. ]", ""));
                }
                
        });
        
    }
    
    @FXML
    private void newAction(ActionEvent event) throws SQLException {
                AstuceID.setText(null);
                myComboBox.setValue(null);
                Titre_astuce.setText(null);
                desc_j.setText(null);
                imgVw.setImage(null);
                
    }
     
    
    @FXML
    void GoToGame(ActionEvent event) throws IOException {
            Parent p1 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();

    }
    
    private void combo ()
 {
     try
     {
         String req = "select DISTINCT id_jeu from jeu ";
            DataSource ds = DataSource.getInstance();
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
            data2.add(rs.getInt(1));
              }
            
              myComboBox.getItems().addAll(data2);
                //comboast.setItems(dataa).;
                      
          
           
 }catch(SQLException ex)
 {
      System.out.println(ex.getMessage());
     
  // Logger.getLogger(AstuceDAO.class.getName()).log(Level.SEVERE, null, ex);
                // ex.printStackTrace();   
 }
     //comboast.setItems(dataa);
 }
    

      private void buildTableviewData() {
       
        Cid_astuce.setCellValueFactory(new PropertyValueFactory<>("idAstuce"));
        Ctitre_jeu.setCellValueFactory(new PropertyValueFactory<>("JeuId"));
        Ctitre_astuce.setCellValueFactory(new PropertyValueFactory<>("title_astuce"));
        Cdescription.setCellValueFactory(new PropertyValueFactory<>("description_astuce"));
        Cimage.setCellValueFactory(new PropertyValueFactory<>("image_astuce"));
           
        AstuceService gp = new AstuceService() {};
        
        data1 = gp.lister();
        table_astuce.setItems(null);
        table_astuce.setItems(data1);
      
        
      }
      
      private void AstuceData() {
        table_astuce.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Astuce> observable,
                Astuce oldValue,
               Astuce newValue) -> {
            if (newValue == null) {              
                AstuceID.setText(null);
                myComboBox.setValue(null);
                desc_j.setText(null);
                Titre_astuce.setText(null);

                return;
            }
            AstuceID.setText(String.valueOf(newValue.getIdAstuce()));
            editID = newValue.getIdAstuce();
            desc_j.setText(newValue.getDescription_astuce());
            Titre_astuce.setText(newValue.getTitle_astuce());
            myComboBox.setValue(newValue.getJeuId());
            oldName= newValue.getImage_astuce();
              Image img = new Image("file:///C:/Users/slimu/Desktop/image/"+newValue.getImage_astuce()) ;
      System.out.println("file:///C:/Users/slimu/Desktop/image/"+newValue.getImage_astuce());
      imgVw.setImage(img);
     
        });
    }
    
      
    @FXML
    void GotoClient(ActionEvent event) throws IOException {
            Parent p1 = FXMLLoader.load(getClass().getResource("FXMLGamecClient.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();
                

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildTableviewData();
        combo();
        AstuceData();
        AstuceSearch();
        button.setStyle("-fx-base: blue");
        delete.setStyle("-fx-base: red");
        editAstuce.setStyle("-fx-base: #27ae60");
        editAstuce.setDisable(true);
         delete.setDisable(true);


         editAstuce.disableProperty().bind(AstuceID.textProperty().isEmpty());
          delete.disableProperty().bind(AstuceID.textProperty().isEmpty());
        
    }    
    
    @FXML
    private void deleteAstuce(ActionEvent event)  throws SQLException, ClassNotFoundException {
      
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Confirmer suppresion astuce", ButtonType.OK);
            a.setTitle("Confirm Delete");
            a.showAndWait();
            if (a.getResult() == ButtonType.OK) {
                service.deleteAstuce(Integer.parseInt(AstuceID.getText()));
                buildTableviewData();
              
            }
        
    }
    
    @FXML
    void editAstuce(ActionEvent event) {
        astuce.setIdAstuce(Integer.parseInt(AstuceID.getText()));  
        astuce.setJeuId(myComboBox.getValue()); 
        astuce.setTitle_astuce(Titre_astuce.getText());
        astuce.setDescription_astuce(desc_j.getText());
        astuce.setImage_astuce(oldName);
        if(photoname.getText()!="")
        {
         astuce.setImage_astuce(photoname.getText());
        }     
          Titre_astuce.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
               {
                if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç0123456789. ]")) 
               {
                    Titre_astuce.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç0123456789. ]", ""));
                }
                
        });
           desc_j.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
               {
                if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç0123456789. ]")) 
               {
                    desc_j.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç0123456789. ]", ""));
                }
                
        });
           
   Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Confirmer modification de l'astuce", ButtonType.OK);
            a.setTitle("Confirm Edit");
            a.showAndWait();
            if (a.getResult() == ButtonType.OK) {
                 service.modifAstuce(astuce);
        table_astuce.getItems().clear();
        buildTableviewData();
              
    }}
    
    private void AstuceSearch() {FilteredList<Astuce> filteredData = new FilteredList<>(data1, e-> true);
        txtSearch.setOnKeyReleased(e->{
        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) ->{
        filteredData.setPredicate((Predicate<? super Astuce>) game->{
         if (newValue ==null || newValue.isEmpty()){
         return true;
          }
             String lowerCaseFilter= newValue.toLowerCase();
             
          if(game.getTitle_astuce().toLowerCase().contains(lowerCaseFilter)){
            return true;   
             }
          
                return false; 
                 } );
                       } );
              SortedList<Astuce> sortedData = new SortedList<>(filteredData);
             sortedData.comparatorProperty().bind(table_astuce.comparatorProperty());
                   table_astuce.setItems(sortedData);
                  });}

  
 
    public void processUpload(ActionEvent event) {
      
          
                     FileChooser fileChooser = new FileChooser();
                     FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                     FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
                     FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                     fileChooser.getExtensionFilters().addAll(exjpg,exjpg2, expng);
                     fileChooser.setTitle("Choose an image File");

                     File file = fileChooser.showOpenDialog(null);
                     
                             if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                
                   Image img = new Image(file.toURI().toString() ); 
                                imgVw.setImage(img);
                                
                               
                                photopath.setText("C:\\wamp\\www\\pics\\"+file.getName());
                                photoname.setText(file.getName());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another image");
            }


            }}
     
    private void notificattion()
    {
        Image img = new Image("file:///C:/Users/slimu/Desktop/image/"+astuce.getImage_astuce()) ;
        //Image img = new Image("");
        Notifications notificationBuilder=Notifications.create()
                .title("nouvelle astuce ajouter")
                .text("vener decouvrir")
                .graphic(new ImageView (img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        
        notificationBuilder.show();
                

    } 
     
     
    
        }
         
         
     
     
    

