package com.river_guide.riverguide;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    Connection conn = null;
    ResultSet rs = null;
    Statement st = null;
    private HashMap<String, ArrayList<rios>> riversByDepartments = new HashMap<>();
    private HashMap<String, Department> departmentsByName = new HashMap<>();
    private HashMap<String, Image> images = new HashMap<>();
    Random random = new Random();
    @FXML
    private TextField texto;

    @FXML
    private TableView<rios> riversTable;

    @FXML
    private TableColumn depa;
    @FXML
    private TableColumn<rios, String> rio2;

    @FXML
    private TableColumn<rios, String> contami2;

    private final ObservableList<rios> list2 = FXCollections.observableArrayList();

    @FXML
    private TableView<departamentos> departmentsTable;

    @FXML
    private Label imageCaptionLabel;

    @FXML
    private ComboBox<String> combobox;

    ObservableList<String> list = FXCollections.observableArrayList("Guatemala", "Alta Verapaz", "Baja Verapaz",
            "Chimaltenango", "El Progreso", "Escuintla", "Huehuetenango", "Izabal", "Jalapa", "Jutiapa", "Petén",
            "Quetzaltenango", "Quiché", "Retalhuleu", "Sacatepéquez", "San Marcos", "Santa Rosa", "Sololá",
            "Suchitepéquez", "Totonicapán", "Zacapa");

    @FXML
    private Label label1;

    @FXML
    private TextArea info;

    @FXML
    private TableView<rios> table;

    @FXML
    private TableColumn<rios, String> rio;

    @FXML
    private TableColumn<rios, String> contami;

    @FXML
    private ImageView imgView;

    @FXML
    private Label extraLabel;

    private HostServices _hostServices;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        combobox.setItems(list);
        conn = ConnectDB.ConnectMariaDB();
        String query = "SELECT d.IdDepartamento as IdDepartamento, d.Nombre as NombreDept, c.IdRio as IdRio, c.nombre as NombreRio, c.contami FROM contaminacion AS c INNER JOIN departamento AS d ON c.IdDepartamento=d.IdDepartamento;";
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rios rioDB;
        try {
            while (rs.next()) {
                String nombre = rs.getString("NombreRio");
                String contami = rs.getString("contami");
                String department = rs.getString("NombreDept");
                rioDB = new rios(rs.getInt("IdRio"), nombre, contami);
                if (!list2.contains(rioDB)) {
                    list2.add(rioDB);
                }
                if (!riversByDepartments.containsKey(department)) {
                    riversByDepartments.put(department, new ArrayList<>());
                }
                riversByDepartments.get(department).add(rioDB);

                if (!departmentsByName.containsKey(department)) {
                    departmentsByName.put(department, new Department(rs.getInt("IdDepartamento"), department));
                }
            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        depa.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        rio2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        contami2.setCellValueFactory(new PropertyValueFactory<>("contami"));
        FilteredList<rios> filtrado = new FilteredList<>(list2, b -> true);
        texto.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrado.setPredicate(rios -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercase = newValue.toLowerCase();
                if (rios.getNombre().toLowerCase().indexOf(lowercase) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<rios> ordenada = new SortedList<>(filtrado);
        ordenada.comparatorProperty().bind(riversTable.comparatorProperty());
        riversTable.setItems(ordenada);

        rio.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        contami.setCellValueFactory(new PropertyValueFactory<>("contami"));

        for (var riverList : riversByDepartments.values()) {
            for (var river : riverList) {
                if (!images.containsKey(river.getNombre())) {
                    try {
                        images.put(river.getNombre(),
                                new Image("/" + river.getNombre() + ".jpg", 278, 132, true, true));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @FXML
    protected void onbtnClick() {
        table.getItems().clear();
        String texto = combobox.getValue().toLowerCase();
        String motagua = "\"Río Motagua\" En la Cuenca del Motagua, se capacitó a 17 932 personas, \n y en lo referente a la reforestación se plantaron 144 mil árboles, para \nlograr la recuperación de 145 hectáreas de zonas degradadas.";
        String samala = "\"Río Samalá\" El Ministerio de Ambiente y Recursos Naturales (MARN) \n impulsa y da seguimiento a este proyecto. En ese marco, se efectuó un \n taller en el cual participaron usuarios de la cuenca y personal del \n Viceministerio del Agua. La actividad tuvo lugar en Santa Cruz Muluá, \n Retalhuleu.";
        String naranjo = "\"Río Naranjo\" La cartera tiene como  prioridad el cuidado de las cuencas \n del país; por ello, se han establecido diferentes mesas técnicas de \n trabajo que definen acciones para su resguardo.";
        String suchiate = "\"Río Suchiate\" La Delegación de San Marcos del Ministerio de Ambiente \n y Recursos Naturales (MARN), hizo una jornada de limpieza \n en la ribera del río Suchiate, \n fronterizo con México, como parte de las actividades de la campaña \n “Hacé tu parte, no más basura”.";
        String coyolate = "\n \"Río Coyolate\" Coordinación para liberación de 2,500 alevines de \n mojarras nativas (tusa, balcera y prieta) con el propósito de contribuir \n y conservar la biodiversidad acuática.";
        String ican = "\"Río Icán\" saneamiento del manto de agua, reforestación y \n recuperación de la biodiversidad.";

        imgView.setImage(null);
        imageCaptionLabel.setText("");
        extraLabel.setText("");

        if (!riversByDepartments.containsKey(texto)) {
            return;
        }

        int imageIndex = random.nextInt(riversByDepartments.get(texto).size());
        for (int i = 0; i < riversByDepartments.get(texto).size(); i++) {
            var river = riversByDepartments.get(texto).get(i);
            table.getItems().add(river);

            if (i == imageIndex) {
                imgView.setImage(images.get(river.getNombre()));
                imageCaptionLabel.setText(river.getNombre());
            }
        }

        if (texto.equalsIgnoreCase("Guatemala")) {
            extraLabel.setText(
                    "\"Río Las Vacas\" El Ministerio de Ambiente y Recursos Naturales (MARN) \n anunció que colocará cercas en el río Las Vacas para recolectar desechos \n sólidos y evitar que lleguen al océano.\n"
                            + motagua);
        } else if (texto.equalsIgnoreCase("Alta Verapaz")) {
            extraLabel.setText(motagua);
        } else if (texto.equalsIgnoreCase("Baja Verapaz")) {
            extraLabel.setText(motagua);
        } else if (texto.equalsIgnoreCase("Chimaltenango")) {
            extraLabel.setText(motagua + coyolate);
        } else if (texto.equalsIgnoreCase("El Progreso")) {
            extraLabel.setText(motagua);
        } else if (texto.equalsIgnoreCase("Escuintla")) {
            extraLabel.setText(coyolate);
        } else if (texto.equalsIgnoreCase("Huehuetenango")) {
            extraLabel.setText("\"Río Ixcán\" ¡Desafortunadamente Nada!");
        } else if (texto.equalsIgnoreCase("Izabal")) {
            extraLabel.setText(motagua
                    + "\n \"Río Dulce\" se encuentra ubicado dentro del área protegida \"Parque \n Nacional Río Dulce\" el cual protege al ecosistema de Guatemala desde \n el año 1955. ");
        } else if (texto.equalsIgnoreCase("Jalapa")) {
            extraLabel.setText(motagua);
        } else if (texto.equalsIgnoreCase("Jutiapa")) {
            extraLabel.setText(
                    "\"Río Paz\" organizar y desarrollar de manera coordinada y priorizada en el \n espacio y en el tiempo, las diferentes acciones y actividades que son \n necesarias, para la gestión de los recursos naturales de la cuenca.");
        } else if (texto.equalsIgnoreCase("Petén")) {
            extraLabel.setText(
                    "\"Río La Pasión\" Se ha tratado de firmar acuerdos para combatir la \n contaminación causada por empresas acusadas de la contaminación del \n río.");
        } else if (texto.equalsIgnoreCase("Quetzaltenango")) {
            extraLabel.setText(samala + "\n" + naranjo);
        } else if (texto.equalsIgnoreCase("Quiché")) {
            extraLabel.setText(motagua);
        } else if (texto.equalsIgnoreCase("Retalhuleu")) {
            extraLabel.setText(samala);
        } else if (texto.equalsIgnoreCase("Sacatepéquez")) {
            extraLabel.setText(coyolate);
        } else if (texto.equalsIgnoreCase("San Marcos")) {
            extraLabel.setText(naranjo + "\n" + suchiate);
        } else if (texto.equalsIgnoreCase("Santa Rosa")) {
            extraLabel.setText(
                    "\"Río de los Esclavos\" La delegación del Ministerio de Ambiente y Recursos Naturales MARN \n en Santa Rosa, realizó la entrega de 600 árboles de limón criollo a los \n pobladores de aldeas de la cuenca baja de río los Esclavos,\n  con el objetivo de incrementar la cobertura de árboles \n frutales en esa área.");
        } else if (texto.equalsIgnoreCase("Sololá")) {
            extraLabel.setText(motagua);
        } else if (texto.equalsIgnoreCase("Suchitepéquez")) {
            extraLabel.setText(coyolate + ican + "\"Río Nahualate\" ¡Desafortunadamente Nada!");
        } else if (texto.equalsIgnoreCase("Totonicapán")) {
            extraLabel.setText(samala);
        } else if (texto.equalsIgnoreCase("Zacapa")) {
            extraLabel.setText(motagua);
        } else {
            extraLabel.setText("Desafortunadamente ¡Nada!");
        }
    }

    @FXML
    protected void onSeeBibliography() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("bibliography-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 590, 530);
            BibliographyViewController controller = fxmlLoader.getController();
            controller.setHostServices(_hostServices);

            Stage stage = new Stage();
            stage.setTitle("Bibliografía");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onMouseClick() {
        departmentsTable.getItems().clear();
        var selectedItem = riversTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        if (selectedItem.getNombre() != null) {
            departmentsTable.setVisible(true);
            if (riversTable.getSelectionModel().getSelectedItem().getNombre().equalsIgnoreCase("Río Motagua")) {
                departmentsTable.getItems().add(new departamentos("Quiché"));
                departmentsTable.getItems().add(new departamentos("Alta Verapaz"));
                departmentsTable.getItems().add(new departamentos("Baja Verapaz"));
                departmentsTable.getItems().add(new departamentos("Sololá"));
                departmentsTable.getItems().add(new departamentos("Chimaltenango"));
                departmentsTable.getItems().add(new departamentos("Guatemala"));
                departmentsTable.getItems().add(new departamentos("Jalapa"));
                departmentsTable.getItems().add(new departamentos("Chiquimula"));
                departmentsTable.getItems().add(new departamentos("El progreso"));
                departmentsTable.getItems().add(new departamentos("Zacapa"));
                departmentsTable.getItems().add(new departamentos("Izabal"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre().equalsIgnoreCase("Río Samalá")) {
                departmentsTable.getItems().add(new departamentos("Totonicapán"));
                departmentsTable.getItems().add(new departamentos("Quetzaltenango"));

            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre()
                    .equalsIgnoreCase("Río Las Vacas")) {
                departmentsTable.getItems().add(new departamentos("Guatemala"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre()
                    .equalsIgnoreCase("Río La Pasión")) {
                departmentsTable.getItems().add(new departamentos("Alta Verapaz"));
                departmentsTable.getItems().add(new departamentos("Petén"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre().equalsIgnoreCase("Río Ixcán")) {
                departmentsTable.getItems().add(new departamentos("Alta Verapaz"));
                departmentsTable.getItems().add(new departamentos("Quiché"));
                departmentsTable.getItems().add(new departamentos("Huehuetenango"));
                departmentsTable.getItems().add(new departamentos("Guatemala"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre().equalsIgnoreCase("Río Dulce")) {
                departmentsTable.getItems().add(new departamentos("Izabal"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre().equalsIgnoreCase("Río Coyolate")) {
                departmentsTable.getItems().add(new departamentos("Chimaltenango"));
                departmentsTable.getItems().add(new departamentos("Suchitepéquez"));
                departmentsTable.getItems().add(new departamentos("Escuintla"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre()
                    .equalsIgnoreCase("Río María Linda")) {
                departmentsTable.getItems().add(new departamentos("Sacatepéquez"));
                departmentsTable.getItems().add(new departamentos("Guatemala"));
                departmentsTable.getItems().add(new departamentos("Santa Rosa"));
                departmentsTable.getItems().add(new departamentos("Escuintla"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre().equalsIgnoreCase("Río Paz")) {
                departmentsTable.getItems().add(new departamentos("Jutiapa"));
                departmentsTable.getItems().add(new departamentos("Santa Rosa"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre()
                    .equalsIgnoreCase("Río de los Esclavos")) {
                departmentsTable.getItems().add(new departamentos("Santa Rosa"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre().equalsIgnoreCase("Río Icán")) {
                departmentsTable.getItems().add(new departamentos("Suchitepéquez"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre()
                    .equalsIgnoreCase("Río Nahualate")) {
                departmentsTable.getItems().add(new departamentos("Sólola"));
                departmentsTable.getItems().add(new departamentos("Suchitepéquez"));
                departmentsTable.getItems().add(new departamentos("Escuintla"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre().equalsIgnoreCase("Río Naranjo")) {
                departmentsTable.getItems().add(new departamentos("San Marcos"));
                departmentsTable.getItems().add(new departamentos("Quetzaltenango"));
                departmentsTable.getItems().add(new departamentos("Retalhuleu"));
            } else if (riversTable.getSelectionModel().getSelectedItem().getNombre().equalsIgnoreCase("Río Suchiate")) {
                departmentsTable.getItems().add(new departamentos("San Marcos"));
            }
        }

    }

    public void setHostServices(HostServices hostServices) {
        _hostServices = hostServices;
    }
}