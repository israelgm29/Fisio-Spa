/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Jhonatan
 */
public class FrmRegistrarController implements Initializable {

    @FXML
    private JFXButton btnR;

    @FXML
    private JFXButton btnRegistrar;

    @FXML
    private JFXComboBox<String> cbboxTipo;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtMail;
    @FXML
    private JFXTextField txtEspecialidad;
    @FXML
    private JFXTextField txtDireccion;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXPasswordField TxtPassword;
    @FXML
    private JFXPasswordField txtConfPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbboxTipo.getItems().add("Enfermera(o)");
        cbboxTipo.getItems().add("Medico");
        
    }

}
