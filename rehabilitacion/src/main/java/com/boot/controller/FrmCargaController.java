package com.boot.controller;

import com.boot.models.OperacionHistoriaC;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jhonatan
 */
public class FrmCargaController implements Initializable {

    @FXML
    private TextField txtuser;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnCerrar;

    private String nombre;

    private static FrmCargaController instance;

    @FXML
    private JFXButton btnRegistrer;

    public FrmCargaController() {
        instance = this;
    }

    public static FrmCargaController getInstance() {
        return instance;
    }

    public String username() {
        return txtuser.getText();
    }

    @FXML
    void closeApp(ActionEvent event) {
        Alert close = new Alert(Alert.AlertType.CONFIRMATION);
        close.setTitle("FISIOSPA");
        close.setHeaderText(null);
        close.setContentText("¿Esta seguro que desea salir?");
        Optional<ButtonType> resultado = close.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        if (this.txtuser.getText().isEmpty() || this.txtpassword.getText().isEmpty()) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setTitle("FISIOSPA");
            message.setContentText("Debe llenar los campos");
            message.setHeaderText(null);
            message.showAndWait();
        } else {
            String us = txtuser.getText();
            String pass = txtpassword.getText();
            OperacionHistoriaC opHc = new OperacionHistoriaC();
            opHc.Conectar();
            int log = opHc.loginadmin(us, pass);

            if (log == 1) {

                try {
                    nombre = this.txtuser.getText();
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("FISIO-SPA");
                    message.setContentText("Bienvenido");
                    message.setHeaderText(null);
                    message.showAndWait();
                    ((Node) (event.getSource())).getScene().getWindow().hide();

                    Stage home = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/frmPrincipal.fxml"));

                    Scene scene = new Scene(root);
                    home.setScene(scene);
                    home.show();
                } catch (Exception e) {
                    Logger.getLogger(FrmCargaController.class.getName()).log(Level.SEVERE, null, e);
                }

            } else {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("FISIO-SPA");
                message.setContentText("Usuario o contraseña incorrectos");
                message.setHeaderText("¡Acceso denegado!");
                message.showAndWait();
                this.txtpassword.setText("");
                this.txtpassword.setText("");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void SingUp(ActionEvent event) {
        try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/frmRegistrar.fxml"));
         
         Parent root = loader.load();
         
         FrmRegistrarController controlador = loader.getController();
         
         Scene scene= new Scene(root);
         Stage stage = new Stage();
         
         stage.setScene(scene);
         stage.initStyle(StageStyle.UNDECORATED);
         stage.show();
         
         stage.setOnCloseRequest(e-> controlador.closewindows());
         Stage stage1 = (Stage) this.btnRegistrer.getScene().getWindow();
         stage1.close();
         
            
        } catch (IOException ex) {
            Logger.getLogger(FrmCargaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void login(KeyEvent event) {
    }

}
