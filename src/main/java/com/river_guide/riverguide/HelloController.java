package com.river_guide.riverguide;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private HashMap<String, rios[]> departments = new HashMap<>();
    private HashMap<String, Image> images = new HashMap<>();
    Random random = new Random();
    @FXML
    private TextField texto;

    @FXML
    private TableView<rios> table2;

    @FXML
    private TableColumn depa;
    @FXML
    private TableColumn<rios, String> rio2;

    @FXML
    private TableColumn<rios, String> contami2;

    private final ObservableList<rios> list2 = FXCollections.observableArrayList();

    @FXML
    private TableView<departamentos> table3;

    @FXML
    private Label imageCaptionLabel;

    @FXML
    private ComboBox<String> combobox;

    ObservableList<String> list = FXCollections.observableArrayList("Guatemala","Alta Verapaz","Baja Verapaz","Chimaltenango","El Progreso","Escuintla", "Huehuetenango","Izabal","Jalapa","Jutiapa","Petén","Quetzaltenango","Quiché","Retalhuleu","Sacatepéquez","San Marcos","Santa Rosa","Sololá","Suchitepéquez","Totonicapán","Zacapa");

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

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        combobox.setItems(list);

        depa.setCellValueFactory(new PropertyValueFactory<>("departamento"));

        rio2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        contami2.setCellValueFactory(new PropertyValueFactory<>("contami"));

        rios rio1 = new rios("Río Motagua","20,000,000 kg de basura fluyen por el río anualmente");
        rios rio2 = new rios("Río Samalá","626,000kg de plástico son emitido anualmente");
        rios rio3 = new rios("Río Las Vacas", "20 mil tonelada van del río las vacas al río Motagua anualmente");
        rios rio4 = new rios("Río La Pasión", "La Pasión es una de las tragedias ambientales más importantes del país. En el 2015\n químicos fueron vertidos al río, los cuales causaron un envenenamiento de varios\n organismos");
        rios rio5 = new rios("Río Ixcán", "El rastro municipal contamina al río con heces, sangre y restos de animales");
        rios rio6 = new rios("Río Dulce", "401,000kg de plástico son emitido anualmente");
        rios rio7 = new rios("Río Coyolate", "417,000kg de plástico son emitido anualmente");
        rios rio8 = new rios("Río María Linda", "1,258,000kg de plástico son emitido anualmente");
        rios rio9 = new rios("Río Paz", "En el 2016 el río fue contaminado con melaza, la cual causó la muerte de varios\n peces");
        rios rio10 = new rios("Río de los Esclavos", "Uno de los contaminantes más grandes al río es la fuente agrícola, o sea, las aguas,\nmieles y pulpa de café");
        rios rio11 = new rios("Río Icán", "365,000kg de plástico son emitido anualmente");
        rios rio12 = new rios("Río Nahualate", "523,000kg de plástico son emitido anualmente");
        rios rio13 = new rios("Río Naranjo", "552,000kg de plástico son emitido anualmente");
        rios rio14 = new rios("Río Suchiate", "419,000kg de plástico son emitido anualmente");
        list2.addAll(rio1,rio2,rio3,rio4,rio5,rio6,rio7,rio8,rio9,rio10,rio11,rio12,rio13,rio14);

        FilteredList<rios> filtrado = new FilteredList<>(list2, b->true);
            texto.textProperty().addListener((observable, oldValue, newValue) ->{
                filtrado.setPredicate(rios -> {
                    if(newValue == null|| newValue.isEmpty()){
                        return true;
                    }
                    String lowercase = newValue.toLowerCase();
                    if(rios.getNombre().toLowerCase().indexOf(lowercase)!=-1){
                        return true;
                    }
                    else{
                        return false;
                    }
                });
            } );

        SortedList<rios> ordenada = new SortedList<>(filtrado);
        ordenada.comparatorProperty().bind(table2.comparatorProperty());
        table2.setItems(ordenada);


        rio.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        contami.setCellValueFactory(new PropertyValueFactory<>("contami"));

        var motagua = new rios("Río Motagua", "20,000,000 kg de basura fluyen por el río anualmente");
        departments.put("quiché", new rios[] { motagua });
        departments.put("alta verapaz", new rios[] { motagua });
        departments.put("baja verapaz", new rios[] { motagua });
        departments.put("sololá", new rios[] { motagua });
        departments.put("jalapa", new rios[] { motagua });
        departments.put("chiquimula", new rios[] { motagua });
        departments.put("el progreso", new rios[] { motagua });
        departments.put("zacapa", new rios[] { motagua });

        var samala = new rios("Río Samalá", "626,000kg de plástico son emitido anualmente");
        departments.put("totonicapán", new rios[] { samala });
        departments.put("retalhuleu", new rios[] { samala });

        var vacas = new rios("Río Las Vacas", "20 mil tonelada van del río las vacas al río Motagua anualmente");
        departments.put("guatemala", new rios[] { vacas, motagua });

        departments.put("petén", new rios[] { new rios("Río La Pasión",
                "La Pasión es una de las tragedias ambientales más importantes del país. En el 2015\n químicos fueron vertidos al río, los cuales causaron un envenenamiento de varios\n organismos") });

        departments.put("huehuetenango", new rios[] {
                new rios("Río Ixcán", "El rastro municipal contamina al río con heces, sangre y restos de animales") });

        departments.put("izabal",
                new rios[] { motagua, new rios("Río Dulce", "401,000kg de plástico son emitido anualmente") });

        var coyolate = new rios("Río Coyolate", "417,000kg de plástico son emitido anualmente");
        departments.put("chimaltenango", new rios[] { motagua, coyolate });

        departments.put("sacatepéquez", new rios[] { coyolate });

        departments.put("escuintla",
                new rios[] { coyolate, new rios("Río María Linda", "1,258,000kg de plástico son emitido anualmente") });
        departments.put("jutiapa", new rios[] { new rios("Río Paz",
                "En el 2016 el río fue contaminado con melaza, la cual causó la muerte de varios\n peces") });
        departments.put("santa rosa", new rios[] { new rios("Río de los Esclavos",
                "Uno de los contaminantes más grandes al río es la fuente agrícola, o sea, las aguas,\nmieles y pulpa de café") });
        departments.put("suchitepéquez",
                new rios[] { coyolate, new rios("Río Icán", "365,000kg de plástico son emitido anualmente"),
                        new rios("Río Nahualate", "523,000kg de plástico son emitido anualmente") });
        departments.put("quetzaltenango",
                new rios[] { samala, new rios("Río Naranjo", "552,000kg de plástico son emitido anualmente") });
        departments.put("san marcos",
                new rios[] { new rios("Río Naranjo", "552,000kg de plástico son emitido anualmente"),
                        new rios("Río Suchiate", "419,000kg de plástico son emitido anualmente") });

        for (var riverList : departments.values()) {
            for (var river : riverList) {
                if (!images.containsKey(river.getNombre())) {
                    var path = "/" + river.getNombre() + ".jpg";
                    URL url = getClass().getResource(path);
                    try {
                        String result = java.net.URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
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

        imgView.setImage(null);
        imageCaptionLabel.setText("");

        if (!departments.containsKey(texto)) {
            return;
        }

        int imageIndex = random.nextInt(departments.get(texto).length);
        for (int i = 0; i < departments.get(texto).length; i++) {
            var river = departments.get(texto)[i];
            table.getItems().add(river);

            if (i == imageIndex) {
                imgView.setImage(images.get(river.getNombre()));
                imageCaptionLabel.setText(river.getNombre());
            }
        }
    }
    @FXML
    protected void onMouseClick() {
        table3.getItems().clear();
        if(table2.getSelectionModel().getSelectedItem().getNombre()!=null){
            table3.setVisible(true);
            if(table2.getSelectionModel().getSelectedItem().getNombre()=="Río Motagua"){
                table3.getItems().add(new departamentos("Quiché"));
                table3.getItems().add(new departamentos("Alta Verapaz"));
                table3.getItems().add(new departamentos("Baja Verapaz"));
                table3.getItems().add(new departamentos("Sololá"));
                table3.getItems().add(new departamentos("Chimaltenango"));
                table3.getItems().add(new departamentos("Guatemala"));
                table3.getItems().add(new departamentos("Jalapa"));
                table3.getItems().add(new departamentos("Chiquimula"));
                table3.getItems().add(new departamentos("El progreso"));
                table3.getItems().add(new departamentos("Zacapa"));
                table3.getItems().add(new departamentos("Izabal"));
            }
        }

    }

}