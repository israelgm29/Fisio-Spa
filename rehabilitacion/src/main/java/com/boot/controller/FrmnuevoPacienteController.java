package com.boot.controller;

import com.boot.models.HistoriaClinica;
import com.boot.models.OperacionHistoriaC;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.javafx.Icon;

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
    private MFXComboBox<String> cmbGenero;
    @FXML
    private JFXTextField jtxtCorreo;
    @FXML
    private JFXTextField jtxtOcupacion;
    @FXML
    private JFXTextField jtxtEstatura;
    @FXML
    private JFXTextField JtxtPeso;
    @FXML
    private MFXDatePicker dtpickFechanac;
    @FXML
    private JFXTextField jtxtTelefono;
    @FXML
    private ImageView imgPerfil;
    @FXML
    private JFXButton btnTomar;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private MFXButton onCamera;

    public static Webcam webcam; // For taking pictures
    public static boolean isCapture = false; // For stop & resume thread of camera
    private String pathFoto = null;
    @FXML
    private MFXTextField jtxtCateg;
    @FXML
    private MFXTextField asd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbGenero.getItems().add("Masculino");
        cmbGenero.getItems().add("Femenino");
        cmbGenero.getItems().add("LGBTI");
        cmbGenero.getItems().add("Otro");
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("campo obligatorio");
        validator.setIcon(new FontIcon("mdi-account"));
        jtxtNombre.getValidators().add(validator);
        jtxtNombre.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                jtxtNombre.validate();
            }
        });

        // TODO
    }

    @FXML
    private void ChargeFoto(ActionEvent event) {
        Stage open = new Stage();
        String destino = new String(System.getProperty("user.home").toString() + "/Documentos/imagesPacientes/");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        File recordsDir = new File(System.getProperty("user.home"), "Documentos/imagesPacientes");
        if (!recordsDir.exists()) {
            recordsDir.mkdirs();
        }

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(open);

        try {
            String ext1 = FilenameUtils.getExtension(imgFile.getCanonicalPath());

            if (ext1.compareTo("jpg") != 0 && ext1.compareTo("png") != 0) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Tipo de archivo no valido");
                error.setContentText("Seleccione archivos que sean solo imagenes o con extencion .jpg, .");
                error.showAndWait();
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmnuevoPacienteController.class.getName()).log(Level.SEVERE, null, ex);

        }
        if (imgFile != null) {
            try {
                Image image = new Image("file:" + imgFile.getAbsolutePath());
                imgPerfil.setImage(image);

                Path from = Paths.get(imgFile.toURI());
                System.out.println(destino);
                Path to = Paths.get(destino + imgFile.getName());
                System.out.println(to);
                CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(from, to, options);
                pathFoto = "file:" + to.toString();

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
        System.out.println(pathFoto);

        HistoriaClinica hc = new HistoriaClinica();
        hc.setPaciente(jtxtNombre.getText());
        hc.setCedula(JtxtCedula.getText());
        hc.setTelefono(jtxtTelefono.getText());
        hc.setSexo(cmbGenero.getSelectedValue());
        hc.setDireccion(jtxtDireccion.getText());
        hc.setMail(jtxtCorreo.getText());
        hc.setOcupacion(jtxtOcupacion.getText());
        hc.setImagen(pathFoto);
        hc.setCategoria(jtxtCateg.getText());
        hc.setEdad(jtxtEdad.getValue());
        hc.setEstatura(Double.parseDouble(jtxtEstatura.getText()));
        hc.setPeso(Double.parseDouble(JtxtPeso.getText()));
        hc.setFecha_nacimiento(dtpickFechanac.getDate());

        OperacionHistoriaC opc = new OperacionHistoriaC();
        if (opc.InsertarPaciente(hc) > 0) {
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setTitle("FISIO-SPA");
            message.setContentText("Paciente inggresado correctamente");
            message.setHeaderText("Exito");
            message.showAndWait();

        }

    }

    @FXML
    void IniciarCamara(ActionEvent event) {
        /* Init camera */
        webcam = Webcam.getDefault();
        if (webcam == null) {
            System.out.println("Camera not found !");
            System.exit(-1);
        }
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();

        new VideoTacker().start();
    }

    @FXML
    private void tomarFoto(ActionEvent event) {
        isCapture = true;
        Stage open = new Stage();
        FileChooser archivo = new FileChooser();
        File recordsDir = new File(System.getProperty("user.home"), "Documentos/imagesPacientes");
        if (!recordsDir.exists()) {
            recordsDir.mkdirs();
        }
        archivo.setInitialDirectory(recordsDir);
        File file = archivo.showSaveDialog(open);

        if (file != null)
            try { // Save picture with .png extension
            ImageIO.write(SwingFXUtils.fromFXImage(imgPerfil.getImage(), null), "PNG", file);
            String dataPath = new String(file.getAbsolutePath());
            System.out.println(dataPath);
            new VideoTacker().stop();

        } catch (IOException ex) {
            ex.printStackTrace(); // Can't save picture

        }

    }

    class VideoTacker extends Thread {

        @Override
        public void run() {
            String file = "/imagesPacientes/";
            while (!isCapture) { // For each 30 millisecond take picture and set it in image view
                try {
                    imgPerfil.setImage(SwingFXUtils.toFXImage(webcam.getImage(), null));

                    sleep(30);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FrmnuevoPacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
