package com.boot.controller;

import com.boot.models.Paciente;
import com.boot.models.OperacionHistoriaC;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
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
    private JFXButton btnCargar, btnTomar, btnGuardar, btnCancel;
    @FXML
    private Spinner<Integer> jtxtEdad;
    @FXML
    private JFXComboBox<String> cmbGenero;
    @FXML
    private JFXTextField jtxtCorreo, jtxtOcupacion, jtxtEstatura, JtxtPeso, jtxtTelefono, jtxtDireccion;
    @FXML
    private JFXTextField JtxtCedula, jtxtNombre, jtxtApellidop, jtxtApellidom;
    @FXML
    private MFXDatePicker dtpickFechanac;
    @FXML
    private ImageView imgPerfil;
    @FXML
    private MFXTextField jtxtCateg;

    private Paciente paciente;
    private ObservableList<Paciente> pacientes;

    public static Webcam webcam; // iniciar camara

    public static boolean isCapture = false; // encender o parar camara

    private String pathFoto = null;

    ValidationSupport validationSupport = new ValidationSupport();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MMM-dd");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cmbGenero.getItems().add("Masculino");
        cmbGenero.getItems().add("Femenino");
        cmbGenero.getItems().add("LGBTI");
        cmbGenero.getItems().add("Otro");
        SpinnerValueFactory<Integer> valor = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);
        jtxtEdad.setValueFactory(valor);
        validationSupport.registerValidator(jtxtNombre, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(JtxtCedula, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(jtxtCorreo, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(jtxtDireccion, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(jtxtEstatura, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(jtxtOcupacion, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(jtxtTelefono, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(JtxtPeso, Validator.createEmptyValidator("Text is required"));
//        validationSupport.registerValidator(jtxtEdad, Validator.createEmptyValidator("Text is required"));

        JtxtPeso.addEventHandler(KeyEvent.KEY_TYPED, event -> onlyNumber(event));

        jtxtEstatura.addEventHandler(KeyEvent.KEY_TYPED, event -> onlyNumber(event));
        jtxtTelefono.addEventHandler(KeyEvent.KEY_TYPED, event -> onlyNumber(event));
        JtxtCedula.addEventHandler(KeyEvent.KEY_TYPED, event -> onlyNumber(event));
    }

    public void initAttributtes(ObservableList<Paciente> paciente) {
        this.pacientes = paciente;
    }

    @FXML
    private void tomarFoto(ActionEvent event) {
        if (IniciarCamara() == false) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setTitle("FISIO-SPA");
            message.setContentText("Debe encender primero la camara");
            message.setHeaderText("¡Error!");
            message.showAndWait();
        } else {

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
                pathFoto = "file:" + dataPath;
                webcam.close();

            } catch (IOException ex) {
                ex.printStackTrace(); // Can't save picture

            }
        }

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
                new FileChooser.ExtensionFilter("All imagenes", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(open);

        if (imgFile != null) {
            try {
                String ext1 = FilenameUtils.getExtension(imgFile.getCanonicalPath());
                if (ext1.compareTo("jpg") != 0 && ext1.compareTo("png") != 0) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("Tipo de archivo no valido");
                    error.setContentText("Seleccione archivos que sean solo imagenes con extencion (.jpg),(.png)");
                    error.showAndWait();
                } else {
                    Image image = new Image("file:" + imgFile.getAbsolutePath());
                    imgPerfil.setImage(image);

                    Path from = Paths.get(imgFile.toURI());
                    Path to = Paths.get(destino + imgFile.getName());
                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to, options);
                    pathFoto = "file:" + to.toString();
                }

            } catch (IOException ex) {
                Logger.getLogger(FrmnuevoPacienteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Tipo de archivo no valido");
            error.setContentText("Seleccione uan imagen, .");
        }

    }

    @FXML
    private void Guardad(ActionEvent event) {

        if (jtxtNombre.getText().isEmpty() || JtxtCedula.getText().isEmpty() || JtxtPeso.getText().isEmpty()
                || jtxtCorreo.getText().isEmpty() || jtxtEdad.getValue().equals("") || jtxtDireccion.getText().isEmpty()
                || jtxtTelefono.getText().isEmpty() || cmbGenero.getValue().equals("")
                || jtxtOcupacion.getText().isEmpty() || jtxtEstatura.getText().isEmpty()||pathFoto == null) {

            Alert message = new Alert(Alert.AlertType.WARNING);
            message.setTitle("FISIO-SPA");
            message.setContentText("Debe llenar todos los campos y agregar una foto al paciente");
            message.setHeaderText("¡Advertencia!");
            message.showAndWait();

        } else {

            dtpickFechanac.setDateFormatter(formatter);
            Paciente hc = new Paciente();
            hc.setCedula(JtxtCedula.getText());
            hc.setNombre(jtxtNombre.getText());
            hc.setApellidop(jtxtApellidop.getText());
            hc.setApellidom(jtxtApellidom.getText());
            hc.setTelefono(jtxtTelefono.getText());
            hc.setGenero(cmbGenero.getValue());
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
            opc.Conectar();
//          Compruebo si la persona existe
            if (opc.InsertarPaciente(hc) > 0 && !pacientes.contains(hc)) {
//                this.paciente = hc;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Se ha añadido correctamente");
                alert.showAndWait();

                // Cerrar ventana
                Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona ya existe");
                alert.showAndWait();
            }
        }
    }

    @FXML
    boolean IniciarCamara() {
        boolean isOn = false;
        /* Init camera */
        webcam = Webcam.getDefault();
        if (isOn != false) {
            if (webcam == null) {
                System.out.println("Camera not found !");
                System.exit(-1);
            } else {

                webcam.setViewSize(new Dimension(640, 480));
                webcam.open();
                isOn = true;
                new VideoTacker().start();
            }
        }
        return isOn;

    }

    public void onlyNumber(KeyEvent keyEvent) {
        try {
            char key = keyEvent.getCharacter().charAt(0);

            if (!Character.isDigit(key)) {
                keyEvent.consume();
            }

        } catch (Exception ex) {
        }
    }

    @FXML
    private void exitWindows(ActionEvent event) {
        Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
    }

    public Paciente getPaciente() {
        return paciente;
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
