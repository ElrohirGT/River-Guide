package com.river_guide.riverguide;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController implements Initializable {

    private HashMap<String, rios[]> departments = new HashMap<>();
    private HashMap<String, Image> images = new HashMap<>();
    Random random = new Random();

    @FXML
    private Label imageCaptionLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField input;
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
        rio.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        contami.setCellValueFactory(new PropertyValueFactory<>("contami1"));

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
        departments.put("totonicapan", new rios[] { samala });
        departments.put("retalhuleu", new rios[] { samala });

        var vacas = new rios("Río Las Vacas", "20mil tonelada van del río las vacas al río Motagua anualmente");
        departments.put("guatemala", new rios[] { vacas, motagua });

        departments.put("petén", new rios[] { new rios("Río La Pasión",
                "La Pasión es una de las tragedias ambientales más importantes del país. En el 2015\n químicos fueron vertidos al río, los cuales causaron un envenenamiento de varios\n organismos") });

        departments.put("huehuetenanto", new rios[] {
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
        String texto = input.getText().toLowerCase();

        errorLabel.setText("");
        imgView.setImage(null);
        imageCaptionLabel.setText("");

        if (!departments.containsKey(texto)) {
            errorLabel.setText("Por favor ingresa un departamento válido!");
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

}