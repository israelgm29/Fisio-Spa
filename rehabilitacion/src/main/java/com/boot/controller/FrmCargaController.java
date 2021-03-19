package com.boot.controller;

import com.boot.dataaccess.Conexion;
import com.boot.models.Administrador;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javax.swing.JOptionPane;

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
        } else if (loginadmin() == 1) {

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

    public int loginadmin() {
        String usuario = this.txtuser.getText();
        String clave = this.txtpassword.getText();
        int resultado = 0;

        String sql = "SELECT cedula, clave\n"
                + "  FROM public.administrador WHERE nombre='" + usuario + "' AND clave='" + clave + "'";
        Conexion con = new Conexion();
        con.conectar();
        try {
            ResultSet rst = con.ejecutarQuery(sql);
            if (rst.next()) {
                resultado = 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.desconectar();
            con.desconectar();

        }
        return resultado;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      

    }


    @FXML
    private void SingUp(ActionEvent event) {
        try {
            Stage signup = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/frmRegistrar.fxml"));
            
            Scene scene = new Scene(root);
            signup.setScene(scene);
            signup.show();
           
        } catch (IOException ex) {
            Logger.getLogger(FrmCargaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
