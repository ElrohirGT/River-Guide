package com.river_guide.riverguide;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/** 
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
*/


public class HelloController implements Initializable {
    /* 
    private List<String> lista = new ArrayList<String>();
    int j = 0;
    double orgCliskSceneX, orgReleaseSceneX;
    Button lbutton, rButton;
    */

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
    
    @FXML
    private Button leftButton;
    
    @FXML
    private Button righButton;

    @FXML
    private ImageView ImagenCarrusel;

    private HostServices _hostServices;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
       
        combobox.setItems(list);
        conn = ConnectDB.ConnectMariaDB();
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
        rios rioDB;
        try {
            while (rs.next()) {
                String nombre = rs.getString("NombreRio");
                String contami = rs.getString("contami");
                String department = rs.getString("NombreDept");
                String extraInfo = rs.getString("ExtraInfo");
                rioDB = new rios(rs.getInt("IdRio"), nombre, contami, extraInfo);
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
        String coyolate = "\n \"Río Coyolate\"";
        String ican = "\"Río Icán\" saneamiento del manto de agua, reforestación y \n recuperación de la biodiversidad.";

        imgView.setImage(null);
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
                imgView.setImage(images.get(river.getNombre()));
                imageCaptionLabel.setText(river.getNombre());
            }
        }

        extraLabel.setText(
                rivers.stream()
                        .map(r -> r.getNombre() + ": " + r.getExtraInfo())
                        .reduce("", (s1, s2) -> String.format("%s\n%s", s1, s2)));
    }

    /** 
    @FXML
    protected void start(Stage primaryStage) {
        // images in src folder.
        try {
            list.add("Río Coyolate.jpg");
            list.add("Río de los Esclavos.jpg");
            list.add("Río Dulce.jpg");
            list.add("Río Icán.jpg");
            list.add("Río Ixcán.jpg");
            list.add("Río La Pasión.jpg");
            list.add("Río Las Vacas.jpg");
            list.add("Río María Linda.jpg");
            list.add("Río Motagua.jpg");
            list.add("Río Nahualate.jpg");
            list.add("Río Naranjo.jpg");
            list.add("Río Paz.jpg");
            list.add("Río Samalá.jpg");
            list.add("Río Suchiate.jpg");

    
            GridPane root = new GridPane();
            root.setAlignment(Pos.CENTER);
    
            lbutton = new Button("<");
            rButton = new Button(">");
    
            Image images[] = new Image[list.size()];
            for (int i = 0; i < list.size(); i++) {
                images[i] = new Image(list.get(i));
            }
    
            ImagenCarrusel = new ImageView(images[j]);
            ImagenCarrusel.setCursor(Cursor.CLOSED_HAND);
       
            ImagenCarrusel.setOnMouseReleased(e -> {
                orgReleaseSceneX = e.getSceneX();
                if (orgCliskSceneX > orgReleaseSceneX) {
                    lbutton.fire();
                } else {
                    rButton.fire();
                }
            });
    
            rButton.setOnAction(e -> {
                j = j + 1;
                if (j == list.size()) {
                    j = 0;
                }
                ImagenCarrusel.setImage(images[j]);
    
            });
            lbutton.setOnAction(e -> {
                j = j - 1;
                if (j == 0 || j > list.size() + 1 || j == -1) {
                    j = list.size() - 1;
                }
                ImagenCarrusel.setImage(images[j]);
    
            });
    
            ImagenCarrusel.setFitHeight(100);
            ImagenCarrusel.setFitWidth(300);
    
            HBox hBox = new HBox();
            hBox.setSpacing(15);
            hBox.setAlignment(Pos.CENTER);
            // hBox.getChildren().addAll(lbutton, imageView, rButton);
            hBox.getChildren().addAll(ImagenCarrusel);
    
            root.add(hBox, 1, 1);
            Scene scene = new Scene(root, 800, 300);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

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