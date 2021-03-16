
package com.boot.controller;

import com.boot.models.Administrador;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jhonatan
 */
public class FrmPrincipalController implements Initializable {
   
 
    @FXML
    private HBox advancevboc1;

    @FXML
    private HBox advancevboc2;

    @FXML
    private HBox advancevboc3;

    @FXML
    private HBox advancevboc4;

    @FXML
    private HBox advancevboc41;

    @FXML
    private MenuButton usrname;
    
    @FXML
    private BorderPane principal;
    
    @FXML
    private Label code;

    
    public void setFirstname( String firstNameString){
     code.setText(firstNameString);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    
}
