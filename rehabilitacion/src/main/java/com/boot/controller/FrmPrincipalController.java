package com.boot.controller;

import com.boot.models.HistoriaClinica;
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
    private TableView<HistoriaClinica> tableSignos;
    @FXML
    private TableColumn<HistoriaClinica, String> clmCategoria;
    @FXML
    private TableColumn<HistoriaClinica, Integer> clmHclinica;
    @FXML
    private TableColumn<HistoriaClinica, String> clmNombre;
    @FXML
    private TableColumn<HistoriaClinica, String> clmCedula;
    @FXML
    private TableColumn<HistoriaClinica, Integer> clmEdad;
    @FXML
    private TableColumn<HistoriaClinica, String> clmFoto;
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
    private JFXTextArea jtxaAntec;
    @FXML
    private JFXTextArea jtxaSintoma;

    private static FrmPrincipalController instance; // instancia del controlador 

    int index = -1;
    @FXML
    private JFXTextField jtxtSatOx;
    @FXML
    private MenuItem btnewUser;

    public FrmPrincipalController() {
        instance = this;
    }

    public static FrmPrincipalController getInstance() {
        return instance;
    }

    ObservableList<HistoriaClinica> pacientes;
    ObservableList<HistoriaClinica> filtropacientes;

    private ObservableList<HistoriaClinica> cargarTabla() {
        ObservableList<HistoriaClinica> hclinicas = FXCollections.observableArrayList();
        OperacionHistoriaC ophc = new OperacionHistoriaC();
        ophc.Conectar();
        ResultSet rst = ophc.Mostrarpacientes();
        
        try {
            
            while (rst.next()) {
                HistoriaClinica HClinica = new HistoriaClinica();
////                //Transformar foto
                HClinica.setCodigo(rst.getInt("codigo"));
                HClinica.setCategoria(rst.getString("categoria"));
                HClinica.setPaciente(rst.getString("paciente"));
                HClinica.setCedula(rst.getString("cedula"));
                HClinica.setImagen(rst.getString("foto"));
                HClinica.setEdad(rst.getInt("edad"));
//                lista.add(HClinica);
                hclinicas.add(HClinica);

            }

        } catch (Exception e) {
        }
        return hclinicas;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filtropacientes = FXCollections.observableArrayList();
        setFirstname(FrmCargaController.getInstance().username());
        this.pacientes = cargarTabla();
        getHora(); // obterner hora actual

        // cargar columnas
        this.clmCategoria.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, String>("categoria"));
        this.clmHclinica.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, Integer>("codigo"));
        this.clmNombre.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, String>("paciente"));
        this.clmCedula.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, String>("cedula"));
        this.clmEdad.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, Integer>("edad"));
        this.clmFoto.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, String>("imagen"));
        this.clmFoto.setVisible(false);
        //llenar tabla
        this.tableSignos.setItems(pacientes);
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

            for (HistoriaClinica paciente : pacientes) {
                if (paciente.getPaciente().toLowerCase().contains(filtroNombre.toLowerCase())) {
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
            Stage newUser = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/frmnuevoPaciente.fxml"));

            Scene scene = new Scene(root);
            newUser.setScene(scene);
            newUser.show();
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }
    
    
}
