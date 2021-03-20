package com.boot.controller;

import com.boot.dataaccess.Conexion;
import com.boot.models.HistoriaClinica;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

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
    private TableColumn<HistoriaClinica, Byte[]> clmFoto;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private JFXTextField jtxtNombre;

    @FXML
    private JFXTextField jtxtCateg;

    @FXML
    private JFXTextField jtxtCedula;

    @FXML
    private JFXTextField jtxtEdad;

    private static FrmPrincipalController instance; // instancia del controlador 

    int index = -1;

    public FrmPrincipalController() {
        instance = this;
    }

    public static FrmPrincipalController getInstance() {
        return instance;
    }

    ObservableList<HistoriaClinica> pacientes;

    @FXML
    private ObservableList<HistoriaClinica> cargarTabla() {
        ObservableList<HistoriaClinica> hclinicas = FXCollections.observableArrayList();
        Conexion con = new Conexion();
        con.conectar();
        PreparedStatement pst = null;
        String sql = ("SELECT codigo, paciente, cedula, edad, foto, categoria FROM \"historia clinica\" ORDER BY codigo ASC");
        try {
            ResultSet rst = con.ejecutarQuery(sql);

            while (rst.next()) {
                HistoriaClinica HClinica = new HistoriaClinica();
////                //Transformar foto
                HClinica.setCodigo(rst.getInt("codigo"));
                HClinica.setCategoria(rst.getString("categoria"));
                HClinica.setPaciente(rst.getString("paciente"));
                HClinica.setCedula(rst.getString("cedula"));
                HClinica.setImagen(rst.getBytes("foto"));
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

        setFirstname(FrmCargaController.getInstance().username());
        this.pacientes = cargarTabla();

        // cargar columnas
        this.clmCategoria.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, String>("categoria"));
        this.clmHclinica.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, Integer>("codigo"));
        this.clmNombre.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, String>("paciente"));
        this.clmCedula.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, String>("cedula"));
        this.clmEdad.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, Integer>("edad"));
        this.clmFoto.setCellValueFactory(new PropertyValueFactory<HistoriaClinica, Byte[]>("imagen"));
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
        

    }

}
