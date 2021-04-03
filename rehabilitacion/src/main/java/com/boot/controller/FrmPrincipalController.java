package com.boot.controller;

import com.boot.models.Paciente;
import com.boot.models.OperacionHistoriaC;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
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
    private TableView<Paciente> tableSignos;
    @FXML
    private TableColumn<Paciente, String> clmCategoria;
    @FXML
    private TableColumn<Paciente, Integer> clmHclinica;
    @FXML
    private TableColumn<Paciente, String> clmNombre, clmApellido;
    @FXML
    private TableColumn<Paciente, String> clmCedula;
    @FXML
    private TableColumn<Paciente, Integer> clmEdad;
    @FXML
    private TableColumn<Paciente, String> clmFoto;
    @FXML
    private JFXTextField jtxtNombre;
    @FXML
    private JFXTextField jtxthClinica;
    @FXML
    private JFXTextField jtxtCateg;
    @FXML
    private JFXTextField jtxtCedula;
    @FXML
    private JFXTextField jtxtEdad;
    @FXML
    private TextField txtSearch;
    @FXML
    private JFXTextField txtTemp;
    @FXML
    private JFXTextField txtFrecu;
    @FXML
    private JFXTextField txtPresArt;
    @FXML
    private JFXTextField txtAlerg;
    @FXML
    private Circle perfil2;
    @FXML
    private Label jtxthora;
    @FXML
    private JFXTextArea jtxaAntec, jtxaSintoma;
    @FXML
    private JFXTextField jtxtSatOx;
    @FXML
    private MenuItem btnewUser;

    private static FrmPrincipalController instance; // instancia del controlador 

    int index = -1;

    public FrmPrincipalController() {
        instance = this;
    }

    public static FrmPrincipalController getInstance() {
        return instance;
    }

    ObservableList<Paciente> pacientes;
    ObservableList<Paciente> filtropacientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filtropacientes = FXCollections.observableArrayList();
        setFirstname(FrmCargaController.getInstance().username());
        this.pacientes = cargarTabla();
        getHora(); // obterner hora actual

        // cargar columnas
        this.clmCategoria.setCellValueFactory(new PropertyValueFactory<Paciente, String>("categoria"));
        this.clmHclinica.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("numerohc"));
        this.clmNombre.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nombre"));
        this.clmApellido.setCellValueFactory(new PropertyValueFactory<Paciente, String>("apellidop"));
        this.clmCedula.setCellValueFactory(new PropertyValueFactory<Paciente, String>("cedula"));
        this.clmEdad.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("edad"));
        this.clmFoto.setCellValueFactory(new PropertyValueFactory<Paciente, String>("imagen"));
        this.clmFoto.setVisible(false);
        //llenar tabla
        this.tableSignos.setItems(pacientes);
    }

    private ObservableList<Paciente> cargarTabla() {

        ObservableList<Paciente> hclinicas = FXCollections.observableArrayList();
        OperacionHistoriaC ophc = new OperacionHistoriaC();
        ophc.Conectar();
        ResultSet rst = ophc.Mostrarpacientes();

        try {

            while (rst.next()) {

                Paciente HClinica = new Paciente();
                HClinica.setNumerohc(rst.getInt("numhclinic"));
                HClinica.setCategoria(rst.getString("categoria"));
                HClinica.setNombre(rst.getString("nombre"));
                HClinica.setApellidop(rst.getString("apellido_p"));
                HClinica.setCedula(rst.getString("cedula"));
                HClinica.setImagen(rst.getString("foto"));
                HClinica.setEdad(rst.getInt("edad"));
                hclinicas.add(HClinica);

            }

        } catch (Exception e) {
        }
        return hclinicas;
    }

    public void setFirstname(String firstNameString) {
        this.usrname.setText(firstNameString);

    }

    @FXML
    private void selecPaciente(MouseEvent event) {

        index = tableSignos.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        jtxtCateg.setText(clmCategoria.getCellData(index).toString());
        jtxtNombre.setText(clmNombre.getCellData(index).toString());
        jtxtCedula.setText(clmCedula.getCellData(index).toString());
        jtxtEdad.setText(clmEdad.getCellData(index).toString());
        jtxthClinica.setText(clmHclinica.getCellData(index).toString());
        Image perfil = new Image(clmFoto.getCellData(index));
        perfil2.setFill(new ImagePattern(perfil));
        getHora();

    }

    @FXML
    void filtrar(KeyEvent event) {
        String filtroNombre = this.txtSearch.getText();
        if (filtroNombre.isEmpty()) {
            this.tableSignos.setItems(pacientes);
        } else {

            this.filtropacientes.clear();

            for (Paciente paciente : pacientes) {
                if (paciente.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())) {
                    this.filtropacientes.add(paciente);

                }
            }
            this.tableSignos.setItems(filtropacientes);
        }
    }

    void getHora() {

        LocalDateTime locaDate = LocalDateTime.now();
        String hours = Integer.toString(locaDate.getHour());
        String minutes = Integer.toString(locaDate.getMinute());
        String seconds = Integer.toString(locaDate.getSecond());
        jtxthora.setText(hours + ":" + minutes + ":" + seconds);

    }

    @FXML
    private void newUser(ActionEvent event) {

        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/frmnuevoPaciente.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // instanciar el controlador y enviar los datos de la tabla al 
            //controlador 2 para verificar si la persona existe
            FrmnuevoPacienteController controlador = loader.getController();
            controlador.initAttributtes(pacientes);

            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            //cargar la tabla con el nuevo paciente ingresado
            this.pacientes = cargarTabla();     
            this.tableSignos.setItems(pacientes);
            this.tableSignos.refresh();
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void tableUpdate(ActionEvent event) {
        // Refresco la tabla
        this.tableSignos.refresh();
    }

}
