/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.List;
import java.util.function.Predicate;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TextField;

import javafx.scene.control.Tooltip;
import IService.IGameService;

import Class.Game;
import Connection.DataSource;
import Service.GameService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.mail.MessagingException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 *
 * @author slimu
 */
public class FXMLDocumentController implements Initializable {
    String oldName;
    @FXML
    private Label label;   
     @FXML
    private Button travel;
   
    @FXML
    private ImageView imgVw;
    
    @FXML
    private Label photopath;
     
    @FXML
    private Label photoname;
    @FXML
    private DatePicker date_sortie_j;
    @FXML
    private TextField note_jj;
    @FXML
    private TextField prix_j;
    @FXML
    private TextField jeuID;
     
    @FXML
    private Button button;
    @FXML
    private TextField desc_j;
    @FXML
    private TextField note_presse_j;
    @FXML
    private Button button1;
    @FXML
    private Button edit;
    @FXML
    private Label image_j;
    @FXML
    private Button btnNew;
    @FXML
    private Button load;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnPrint;
    @FXML
    private Button btnExport;
    @FXML
    private ChoiceBox cmboPlat;
    @FXML
    private TextField id_j;
    @FXML
    private TextField developpeur;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableColumn<?,?> Cnote_presse;
    @FXML
    private TableColumn<Game,Game> Cimage;
    
    @FXML
    private TableColumn<?,?> Cdate_sortie;
    @FXML
    private TableColumn<?,?> Cgenre;
    @FXML
    private TableColumn<?,?> Cprix;
    @FXML
    private TableColumn<?,?> Cconsole;
    @FXML
    private TableColumn<?,?> Cnote_joueur;
    @FXML
    private TableColumn<?,?> Cid_jeu;
    @FXML
    private TableColumn<?,?> Ctitre_jeu;
    @FXML
    private TableColumn<?,?> Cdescription;
    @FXML
    private ComboBox<String> cmbSacco;
    @FXML
    private ComboBox<String> cmbSacco1;
    @FXML
    private TableView<Game>  table_jeu;

    ObservableList <Game> data = FXCollections.observableArrayList();
 

         static int editID;
         Game game = new Game();
         IGameService service=new GameService();
         List<Game> g=service.listerGame();

   
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
        
        game.setTitre(id_j.getText());
        game.setGenre(cmbSacco1.getValue());
        game.setDate_sortie(java.sql.Date.valueOf(date_sortie_j.getValue()));
        game.setNote_presse(Integer.parseInt(note_presse_j.getText()));
        game.setNote_joueur(Integer.parseInt(note_jj.getText()));
        game.setDescription(desc_j.getText());
        game.setPrix(Integer.parseInt(prix_j.getText()));       
        game.setConsole(cmbSacco.getValue()); 
        game.setImage(oldName);
        if(photoname.getText()!="")
        {
            game.setImage(photoname.getText());
        }
        game.setDeveloppeur(developpeur.getText());
     
          note_presse_j.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
               {
                if (!newValue.matches("[0123456789]")) 
               {
                    note_presse_j.setText(newValue.replaceAll("[^0123456789]", ""));
                }
                
        });
           note_jj.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
               {
                if (!newValue.matches("[0123456789]")) 
               {
                    note_jj.setText(newValue.replaceAll("[^0123456789]", ""));
                }
                
        });
            prix_j.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
               {
                if (!newValue.matches("[0123456789]")) 
               {
                    prix_j.setText(newValue.replaceAll("[^0123456789]", ""));
                }
                
        });
       
        
   
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Confirm add of the Game", ButtonType.OK);
            a.setTitle("Confirm add");
            a.showAndWait();
            if (a.getResult() == ButtonType.OK) {
   
                service.addgame(game);
                buildTableviewData();
                notificattion();
      
              
            }
        
    }
   
    @FXML
    private void delete(ActionEvent event)  throws SQLException, ClassNotFoundException {
      
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Deletion of the Game", ButtonType.OK);
            a.setTitle("Confirm Delete");
            a.showAndWait();
            if (a.getResult() == ButtonType.OK) {
                service.deletegame(Integer.parseInt(jeuID.getText()));
                buildTableviewData();
               
              
            }
        
    }
    
    @FXML
    private void edit(ActionEvent event) {
        
        game.setId_Jeu(Integer.parseInt(jeuID.getText()));
        game.setTitre(id_j.getText());
        game.setGenre(cmbSacco1.getValue());
        game.setDate_sortie(java.sql.Date.valueOf(date_sortie_j.getValue()));
        game.setNote_presse(Integer.parseInt(note_presse_j.getText()));
        game.setNote_joueur(Integer.parseInt(note_jj.getText()));
        game.setDescription(desc_j.getText());
        game.setPrix(Integer.parseInt(prix_j.getText()));
        game.setConsole(cmbSacco.getValue()); 
        game.setImage(oldName);
        if(photoname.getText()!="")
        {
            game.setImage(photoname.getText());
        }
        game.setDeveloppeur(developpeur.getText());
   
         note_presse_j.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
               {
                if (!newValue.matches("[0123456789]")) 
               {
                    note_presse_j.setText(newValue.replaceAll("[^0123456789]", ""));
                }
                
        });
           note_jj.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
               {
                if (!newValue.matches("[0123456789]")) 
               {
                    note_jj.setText(newValue.replaceAll("[^0123456789]", ""));
                }
                
        });
            prix_j.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
               {
                if (!newValue.matches("[0123456789]")) 
               {
                    prix_j.setText(newValue.replaceAll("[^0123456789]", ""));
                }
                
        });
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Edit of the Game", ButtonType.OK);
        a.setTitle("Confirm Edit");
        a.showAndWait();
        if (a.getResult() == ButtonType.OK) {
            service.updateGame(game);
           table_jeu.getItems().clear();
            buildTableviewData();

        }  

    }

    
    private void saveXLSFile(File file) {
        try {
            //System.out.println("Clicked,waiting to export....");
            
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("sheet 0");
            Row row1 = workSheet.createRow((short) 0);
            row1.createCell(0).setCellValue("id_Jeu");
            row1.createCell(1).setCellValue("titre");
            row1.createCell(2).setCellValue("genre");
            row1.createCell(3).setCellValue("date_sortie");
            row1.createCell(4).setCellValue("note_presse");
            row1.createCell(5).setCellValue("note_joueur");
            row1.createCell(6).setCellValue("description");
            row1.createCell(7).setCellValue("prix");
            row1.createCell(8).setCellValue("console");
            row1.createCell(9).setCellValue("image");
            row1.createCell(10).setCellValue("developpeur");
           
            Row row2;

            String req = "select * from jeu ";
            DataSource ds = DataSource.getInstance();
            Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int a = rs.getRow();
                row2 = workSheet.createRow((short) a);
                row2.createCell(0).setCellValue(rs.getInt(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getDate(4));
                row2.createCell(4).setCellValue(rs.getInt(5));
                row2.createCell(5).setCellValue(rs.getInt(6));
                row2.createCell(6).setCellValue(rs.getString(7));
                row2.createCell(7).setCellValue(rs.getInt(8));
                row2.createCell(8).setCellValue(rs.getString(9));
                row2.createCell(9).setCellValue(rs.getString(10));
                row2.createCell(10).setCellValue(rs.getString(11));
               
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();

            
            TrayNotification tn = new TrayNotification("NEW EXCEL FILE", "Specified excel file successfully generated", NotificationType.SUCCESS);
            tn.showAndWait();
        } catch (SQLException | IOException e) {
            TrayNotification tn = new TrayNotification("NEW EXCEL FILE", "Could not generate specified file", NotificationType.ERROR);
            tn.showAndWait();
            System.err.println(e);

        }
    }
        
    @FXML
    private void exportAction(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        chooser.getExtensionFilters().add(filter);
        // Show save dialog
        File file = chooser.showSaveDialog(btnExport.getScene().getWindow());
        if (file != null) {
            saveXLSFile(file);

        }
    }

    @FXML
    private void newAction(ActionEvent event) throws SQLException {
        jeuID.setText(null);
        id_j.setText(null);
        cmbSacco1.setValue(null);
        date_sortie_j.setValue(null);
        note_presse_j.setText(null);
        note_jj.setText(null);
        desc_j.setText(null);
        prix_j.setText(null);
        cmbSacco.setValue(null);
        developpeur.setText(null);

        
    }
  
    private void buildTableviewData() {
       
        Cid_jeu.setCellValueFactory(new PropertyValueFactory<>("id_Jeu"));
        Ctitre_jeu.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Cgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        Cdate_sortie.setCellValueFactory(new PropertyValueFactory<>("date_sortie"));
        Cnote_presse.setCellValueFactory(new PropertyValueFactory<>("note_presse"));
        Cnote_joueur.setCellValueFactory(new PropertyValueFactory<>("note_joueur"));
        Cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        Cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        Cconsole.setCellValueFactory(new PropertyValueFactory<>("console"));
        Cimage.setCellValueFactory(new PropertyValueFactory<>("image"));
               
    
        GameService gp = new GameService() {};
        
        data = gp.lister();
        table_jeu.setItems(null);
        table_jeu.setItems(data);
      
        
      }
    
   @FXML
    private void RefreshAction(ActionEvent event) {
        table_jeu.getItems().clear();
        buildTableviewData();
    }
    
   @FXML
    private void Refresh(ActionEvent event) {
        
        buildTableviewData();
    }

    
    
  @Override
    public void initialize(URL url, ResourceBundle rb) {
           buildTableviewData();
   
        
        button1.setStyle("-fx-base: blue");
        button.setStyle("-fx-base: red");
        edit.setStyle("-fx-base: #27ae60");
                //Set tiptool text on Export Button
        Tooltip tExport = new Tooltip("Export to Excel");
        btnExport.setTooltip(tExport);

        //Set tiptool text on Export Button
        Tooltip tRefresh = new Tooltip("Refresh Tableview");
        btnRefresh.setTooltip(tRefresh);
        
        ObservableList<String> SACCOS = FXCollections.observableArrayList("Ps4", "Ps3", "Xbox 360", "Xbox one", "Pc");
        cmbSacco.setItems(SACCOS);
        cmbSacco.getSelectionModel().selectFirst();
        ObservableList<String> SACCO = FXCollections.observableArrayList("Combat", "Action", "Beat them all", "Plates-formes", "Action-aventure", "Jeu de rôle");
        cmbSacco1.setItems(SACCO);
        cmbSacco1.getSelectionModel().selectFirst();
        
        GameSearch();
        GameData();
        
        edit.setDisable(true);
        button1.setDisable(true);


         edit.disableProperty().bind(jeuID.textProperty().isEmpty());
         button1.disableProperty().bind(jeuID.textProperty().isEmpty());
        
        

}


    private void GameSearch() {FilteredList<Game> filteredData = new FilteredList<>(data, e-> true);
        txtSearch.setOnKeyReleased(e->{
        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) ->{
        filteredData.setPredicate((Predicate<? super Game>) game->{
         if (newValue ==null || newValue.isEmpty()){
         return true;
          }
             String lowerCaseFilter= newValue.toLowerCase();
             
          if(game.getTitre().toLowerCase().contains(lowerCaseFilter)){
            return true;   
             }
             else if(game.getConsole().toLowerCase().contains(lowerCaseFilter)){
            return true;   
                }
              else if(game.getGenre().toLowerCase().contains(lowerCaseFilter)){
            return true;   
                       }
          
                return false; 
                 } );
                       } );
              SortedList<Game> sortedData = new SortedList<>(filteredData);
             sortedData.comparatorProperty().bind(table_jeu.comparatorProperty());
                   table_jeu.setItems(sortedData);
                  });}

    private void GameData() {
        table_jeu.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Game> observable,
                Game oldValue,
                Game newValue) -> {
            if (newValue == null) {
              //  editID = 0;
                jeuID.setText(null);
                id_j.setText(null);
                cmbSacco1.setValue(null);
                date_sortie_j.setValue(null);
                note_presse_j.setText(null);
                note_jj.setText(null);
                desc_j.setText(null);
                prix_j.setText(null);
                cmbSacco.setValue(null);
                imgVw.setImage(null);
                developpeur.setText(null);
              
                
               // imgVw.setImage(null);
                return;
            }
            
            java.sql.Date date= (java.sql.Date) newValue.getDate_sortie();
            LocalDate date1 = date.toLocalDate();
            jeuID.setText(String.valueOf(newValue.getId_Jeu()));
            editID = newValue.getId_Jeu();
            id_j.setText(newValue.getTitre());
            cmbSacco1.setValue(newValue.getGenre());
            date_sortie_j.setValue(date1);
            note_presse_j.setText(String.valueOf(newValue.getNote_presse()));
            note_jj.setText(String.valueOf(newValue.getNote_joueur()));
            desc_j.setText(newValue.getDescription());
            prix_j.setText(String.valueOf(newValue.getPrix()));
            cmbSacco.setValue(newValue.getConsole());
            developpeur.setText(newValue.getDeveloppeur());
      
            oldName= newValue.getImage();
       Image img = new Image("file:///C:/Users/slimu/Desktop/image/"+newValue.getImage()) ;
      System.out.println("file:///C:/Users/slimu/Desktop/image/"+newValue.getImage());
      imgVw.setImage(img);
      
      
           
        });
    }
    
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
                                imgVw.setFitHeight(150);
                                imgVw.setFitWidth(150);
                               
                                photopath.setText("C:\\wamp64\\www\\"+file.getName());
                                photoname.setText(file.getName());
                                
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another image");
            }


            }
        
     }
     
  private void notificattion()
    {
 Image img = new Image("file:///C:/Users/slimu/Desktop/image/"+game.getImage()) ;
        //Image img = new Image("");
        Notifications notificationBuilder=Notifications.create()
                .title("nouveau jeu ajouter")
                .text("vener decouvrir")
                .graphic(new ImageView (img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        
        notificationBuilder.show();
                

    } 

    
    @FXML
    void GoToAstuce(ActionEvent event) throws IOException {
            Parent p1 = FXMLLoader.load(getClass().getResource("FXMLastuce.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();
                

    }
    
            @FXML
    void viewChart(ActionEvent event) throws IOException {
            
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLPieChart.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
           
            stage.setScene(new Scene(root1));  
            stage.show();
                

    }
   

}