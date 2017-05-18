/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author slimu
 */
public class FXMLFormulaireController implements Initializable {


    @FXML
    private Button btm_sendMail;

    @FXML
    private TextField titre;

    @FXML
    private TextField mdp;

    @FXML
    private TextArea description;

    @FXML
    private TextField email;

    @FXML
    void btm_sendMail(ActionEvent event) {
        
String mail = String.valueOf(email.getText());
String motdepasse= String.valueOf(mdp.getText());
String title= String.valueOf(titre.getText());
String desc = String.valueOf(description.getText());
        email.addEventFilter(KeyEvent.KEY_TYPED, (Event arg) -> {
            KeyEvent arg0 = (KeyEvent) arg;

            if (!(email.getText().endsWith("@esprit.tn") || (email.getText().endsWith("@gmail.com")) || (email.getText().endsWith("@yhoo.fr")))) {
               email.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                //     arg0.consume();
                btm_sendMail.setDisable(true);
            } else {

                email.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.😎 50%),rgb(218, 226, 224);");
                btm_sendMail.setDisable(false);
            }
        });
         Mail ma= new Mail(); 
             Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Confirm l'envoi du mail", ButtonType.OK);
            a.setTitle("Confirmer envoi");
            a.showAndWait();
            if (a.getResult() == ButtonType.OK) {
     
    try {
        ma.sendMail(mail,motdepasse,title,desc );
    } catch (MessagingException ex) {
        Logger.getLogger(FXMLFormulaireController.class.getName()).log(Level.SEVERE, null, ex);
    }
        }}
           
           @FXML
    void GotoClient(ActionEvent event) throws IOException {
            Parent p1 = FXMLLoader.load(getClass().getResource("FXMLFormulaire.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.close();
                

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
