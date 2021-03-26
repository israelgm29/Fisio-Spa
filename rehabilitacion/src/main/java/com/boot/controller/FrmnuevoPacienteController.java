/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.tk.FileChooserType;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * FXML Controller class
 *
 * @author develop
 */
public class FrmnuevoPacienteController implements Initializable {

    @FXML
    private FontIcon btnTomarFoto;
    @FXML
    private JFXButton btnCargar;
    @FXML
    private JFXTextField jtxtNombre;
    @FXML
    private JFXTextField JtxtCedula;
    @FXML
    private Spinner<Integer> jtxtEdad;
    @FXML
    private JFXTextField jtxtDireccion;
    @FXML
    private ComboBox<String> cmbGenero;
    @FXML
    private JFXTextField jtxtCorreo;
    @FXML
    private JFXTextField jtxtOcupacion;
    @FXML
    private JFXTextField jtxtEstatura;
    @FXML
    private JFXTextField JtxtPeso;
    @FXML
    private DatePicker dtpickFechanac;
    @FXML
    private JFXTextField jtxtTelefono;
    @FXML
    private Circle imgPerfil;
    @FXML
    private JFXButton btnTomar;
    @FXML
    private JFXButton btnGuardar;

    private Path to;
    private Path from;
    private File selectedFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbGenero.getItems().add("Masculino");
        cmbGenero.getItems().add("Femenino");
        cmbGenero.getItems().add("LGBTI");
        cmbGenero.getItems().add("Otro");
        // TODO

    }

    @FXML
    private void TakeFoto(ActionEvent event) {
    }

    @FXML
    private void ChargeFoto(ActionEvent event) {
        Stage open = new Stage();
        String destino = "src/main/resources/imagesPacientes/";

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(open);
        String formato = "jpg";
        // Mostar la imagen
        if (imgFile != null) {
            try {
                String ext1 = FilenameUtils.getExtension(imgFile.getCanonicalPath());

                if (ext1.compareTo(formato) != 0) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Tipo de archivo no valido");
                    error.setContentText("Seleccione archivos que sean solo imagenes o con extencion .jpg, .");
                    error.showAndWait();

                } else {
                    Image image = new Image("file:" + imgFile.getCanonicalPath());
                    imgPerfil.setFill(new ImagePattern(image));

                    Path from = Paths.get(imgFile.toURI());
                    Path to = Paths.get(destino + imgFile.getName());
                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to, options);
                }
//                
//                System.out.println(from);

            } catch (IOException ex) {
                Logger.getLogger(FrmnuevoPacienteController.class.getName()).log(Level.SEVERE, null, ex);

            }
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Tipo de archivo no valido");
            error.setContentText("Seleccione archivos que sean solo imagenes o con extencion .jpg, .");
        }

    }

    @FXML
    private void Guardad(ActionEvent event) {
    }

}
