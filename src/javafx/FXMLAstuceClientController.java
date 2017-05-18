/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import Class.Astuce;

import IService.IAstuceService;

import Service.AstuceService;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
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
public class FXMLAstuceClientController implements Initializable {

    
    @FXML
    private Text test;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableColumn<Astuce, String> Ctitre;
    @FXML
    private Label GameTitre;
    @FXML
    private Label AstuceTitre;
    @FXML
    private ImageView imgVw;
    @FXML
    private TableView<Astuce> table_astuce;

    ObservableList <Astuce> data1 = FXCollections.observableArrayList();

    Astuce astuce = new Astuce();
    IAstuceService service=new AstuceService();
    
    private void buildTableviewData() {
       
    
        Ctitre.setCellValueFactory(new PropertyValueFactory<>("title_astuce"));
        
               
    
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
                test.setText(null);
                GameTitre.setText(null);
                AstuceTitre.setText(null);
                imgVw.setImage(null);
                return;
            }
            test.setText(newValue.getDescription_astuce());
                GameTitre.setText(String.valueOf(newValue.getJeuId()));
                AstuceTitre.setText(newValue.getTitle_astuce());
            Image img = new Image("file:///C:/Users/slimu/Desktop/image/"+newValue.getImage_astuce()) ;
      System.out.println("file:///C:/Users/slimu/Desktop/image/"+newValue.getImage_astuce());
      imgVw.setImage(img);
            //imgVw.setImage(newValue.getImage());
           
        });
    }
    
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

    @FXML
    void GotoGameclient(ActionEvent event) throws IOException {
            Parent p1 = FXMLLoader.load(getClass().getResource("FXMLGamecClient.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();
                

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildTableviewData();
        AstuceData();
        AstuceSearch();
    }    
    
}
