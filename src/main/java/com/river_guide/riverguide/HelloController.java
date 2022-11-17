package com.river_guide.riverguide;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

import java.util.List;
import javafx.scene.control.Button;

public class HelloController implements Initializable {

    private List<String> lista = new ArrayList<String>();
    int j = 0;
    double orgCliskSceneX, orgReleaseSceneX;
    Button lbutton, rButton;

    Connection conn = null;
    ResultSet rs = null;
    Statement st = null;
    private HashMap<String, ArrayList<River>> riversByDepartments = new HashMap<>();
    private HashMap<String, Department> departmentsByName = new HashMap<>();
    private HashMap<String, Image> images = new HashMap<>();
    private List<String> listanombreList = new ArrayList<String>();
    Random random = new Random();
    @FXML
    private TextField texto;

    @FXML
    private Label updateNotifLabel;

    @FXML
    private TableView<River> riversTable;

    @FXML
    private TableColumn<String, String> depa;
    @FXML
    private TableColumn<River, String> rio2;

    @FXML
    private TableColumn<River, String> contami2;

    private final ObservableList<River> list2 = FXCollections.observableArrayList();

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
    private TableView<River> table;

    @FXML
    private TableColumn<River, String> rio;

    @FXML
    private TableColumn<River, String> contami;

    @FXML
    private Label extraLabel;

    @FXML
    private Button leftButton;

    @FXML
    private Button righButton;

    @FXML
    private ImageView ImagenCarrusel;

    @FXML
    private ComboBox<Department> updateDepartment;

    @FXML
    private ComboBox<River> updateRiver;

    @FXML
    private TextField updateRiverName;

    @FXML
    private TextArea updateRiverContami;

    @FXML
    private TextArea updateRiverExtra;

    @FXML
    private TabPane tabPane;

    private HostServices _hostServices;

    @Override
    public void initialize(URL location, ResourceBundle resource) {

        combobox.setItems(list);
        conn = ConnectDB.ConnectMariaDB();
        RefreshData();
        depa.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        rio2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        contami2.setCellValueFactory(new PropertyValueFactory<>("contami"));
        FilteredList<River> filtrado = new FilteredList<>(list2, b -> true);
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

        SortedList<River> ordenada = new SortedList<>(filtrado);
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
        updateDepartment.setItems(FXCollections.observableArrayList(departmentsByName.values()));
        updateDepartment.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                return department.getName();
            }

            @Override
            public Department fromString(String departmentName) {
                return departmentsByName.get(departmentName);
            }

        });
        updateRiver.setConverter(new StringConverter<River>() {

            @Override
            public String toString(River river) {
                return river.getNombre();
            }

            @Override
            public River fromString(String riverName) {
                var departmentName = updateDepartment.getValue().getName();
                return riversByDepartments.get(departmentName)
                        .stream()
                        .filter(r -> r.getNombre().equals(riverName))
                        .findAny()
                        .orElse(null);

            }

        });
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            RefreshData();
        });
    }

    private void RefreshData() {
        riversByDepartments.clear();
        departmentsByName.clear();

        String query = "SELECT d.IdDepartamento as IdDepartamento, d.Nombre as NombreDept, c.IdRio as IdRio, c.nombre as NombreRio, c.contami, ExtraInfo FROM rio AS c INNER JOIN departamento AS d ON c.IdDepartamento=d.IdDepartamento;";
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
        River rioDB;
        try {
            while (rs.next()) {
                String nombre = rs.getString("NombreRio");
                String contami = rs.getString("contami");
                String department = rs.getString("NombreDept");
                String extraInfo = rs.getString("ExtraInfo");
                rioDB = new River(rs.getInt("IdRio"), nombre, contami, extraInfo);
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
    }

    @FXML
    protected void onDepartmentSelectedForUpdate(ActionEvent e) {
        updateNotifLabel.setText("");
        final Department selectedDepartment = updateDepartment.getValue();
        final String selectedDepartmentName = selectedDepartment.getName().toLowerCase();
        if (!riversByDepartments.containsKey(selectedDepartmentName)) {
            return;
        }

        updateRiver.getItems().clear();
        riversByDepartments.get(selectedDepartmentName).stream()
                .forEach(r -> updateRiver.getItems().add(r));
    }

    @FXML
    protected void onRiverSelectedForUpdate(ActionEvent e) {
        updateNotifLabel.setText("");
        final String selectedDepartmentName = updateDepartment.getValue().getName().toLowerCase();
        if (!departmentsByName.containsKey(selectedDepartmentName)) {
            return;
        }

        final River selectedRiver = updateRiver.getValue();
        if (selectedRiver == null) {
            updateRiverName.clear();
            updateRiverContami.clear();
            updateRiverExtra.clear();
            return;
        }
        final String selectedRiverName = selectedRiver.getNombre();

        updateRiverName.setText(selectedRiverName);
        updateRiverContami.setText(selectedRiver.getContami());
        updateRiverExtra.setText(selectedRiver.getExtraInfo());
    }

    @FXML
    protected void onUpdateRiver(ActionEvent e) {
        String riverName = updateRiverName.getText();
        String riverContami = updateRiverContami.getText();
        String riverExtra = updateRiverExtra.getText();

        River selectedRiver = updateRiver.getValue();

        String query = "UPDATE rio SET Nombre=?, Contami=?, ExtraInfo=? WHERE IdRio=" + selectedRiver.getId();
        try {
            var prepared = conn.prepareStatement(query);
            prepared.setString(1, riverName);
            prepared.setString(2, riverContami);
            prepared.setString(3, riverExtra);
            prepared.executeUpdate();
            updateNotifLabel.setText("Se actualizó satisfactoriamente!");
        } catch (Exception ex) {
            System.out.println("There is an Exception.");
            System.out.println(ex.getMessage());
            updateNotifLabel.setText("Lo sentimos, ocurrió un error en la actualización!");
        }
    }

    @FXML
    protected void onbtnClick() {

        table.getItems().clear();
        String texto = combobox.getValue().toLowerCase();

        ImagenCarrusel.setImage(null);
        imageCaptionLabel.setText("");
        extraLabel.setText("");

        if (!riversByDepartments.containsKey(texto)) {
            return;
        }

        final var rivers = riversByDepartments.get(texto);

        int imageIndex = random.nextInt(rivers.size());
        for (int i = 0; i < riversByDepartments.get(texto).size(); i++) {
            var river = riversByDepartments.get(texto).get(i);
            table.getItems().add(river);

            if (i == imageIndex) {
                ImagenCarrusel.setImage(images.get(river.getNombre()));
                imageCaptionLabel.setText(river.getNombre());
            }
        }

        extraLabel.setText(
                rivers.stream()
                        .map(r -> r.getNombre() + ": " + r.getExtraInfo())
                        .reduce("", (s1, s2) -> String.format("%s\n%s", s1, s2)));
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