/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.controller;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jhonatan
 */
public class FrmRegistrarController implements Initializable {

    private JFXButton btnRegistrar;

    @FXML
    private JFXPasswordField txtConfPassword;
    @FXML
    private JFXTextField txtUsernamedoc;
    @FXML
    private JFXTextField txtEspecialidaddoc;
    @FXML
    private JFXTextField txtMaildoc;
    @FXML
    private JFXTextField txtDirecciondoc;
    @FXML
    private JFXTextField txtTelefonodoc;
    @FXML
    private JFXRadioButton rdbtnMdoc;
    @FXML
    private ToggleGroup radbtnGrupGDoc;
    @FXML
    private JFXRadioButton rdbtnFdoc;
    @FXML
    private JFXRadioButton rdbtnLGBTIdoc;
    @FXML
    private JFXPasswordField txtPassworddoc;
    @FXML
    private JFXButton btnRegistrardoc;
    @FXML
    private MFXToggleButton btnActivedoc;
    @FXML
    private JFXTextField cedulaEnf;
    @FXML
    private JFXTextField mailEnf;
    @FXML
    private JFXTextField direccionEnf;
    @FXML
    private JFXTextField telefonoEnf;
    @FXML
    private JFXTextField nombreEnf;
    @FXML
    private JFXTextField recuperarEnf;
    @FXML
    private MFXToggleButton btnActiveenf;
    @FXML
    private RadioButton btnMenf;
    @FXML
    private ToggleGroup tggrupGeneroEnf;
    @FXML
    private RadioButton btnFenf;
    @FXML
    private RadioButton btnlgbtiEnf;
    @FXML
    private GridPane passEnf;
    @FXML
    private JFXPasswordField confpassEnf;
    @FXML
    private JFXPasswordField jtxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void closewindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/frmCarga.fxml"));

            Parent root = loader.load();

            FrmCargaController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

//            Stage stage1 = (Stage) this.btnRegistrar.getScene().getWindow();
//            stage1.close();

        } catch (IOException ex) {
            Logger.getLogger(FrmRegistrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getRegister(ActionEvent event) {
       
    }

}
