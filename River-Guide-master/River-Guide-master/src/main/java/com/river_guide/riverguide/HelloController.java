package com.river_guide.riverguide;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField input;
    @FXML
    private TableView<rios> table;

    @FXML
    private TableColumn<rios, String> rio;


    @FXML
    private TableColumn<rios, String> contami;


    @Override
    public void initialize(URL location, ResourceBundle resource){
        rio.setCellValueFactory(new PropertyValueFactory <>("nombre"));
        contami.setCellValueFactory(new PropertyValueFactory <>("contami1"));
    }
    @FXML
    protected void onbtnClick() {
        table.getItems().clear();
        String texto= input.getText().toLowerCase();
        if((texto.equals("quiché"))||(texto.equals("alta verapaz"))||(texto.equals("baja verapaz"))||(texto.equals("sololá"))||(texto.equals("jalapa"))||(texto.equals("chiquimula"))||(texto.equals("el progreso"))||texto.equals("zacapa")){
            table.getItems().add(new rios("Río Motagua","20,000,000 kg de basura fluyen por el río anualmente"));
        }
        else if((texto.equals("totonicapan"))||(texto.equals("retalhuleu"))){
            table.getItems().add(new rios("Río Samalá","626,000kg de plástico son emitido anualmente"));

        }
        else if(texto.equals("guatemala")){
            table.getItems().add(new rios("Río Motagua","20,000,000 kg de basura fluyen por el río anualmente"));
            table.getItems().add(new rios("Río Las Vacas","20mil tonelada van del río las vacas al río Motagua anualmente"));

        }
        else if(texto.equals("petén")){
            table.getItems().add(new rios("Río La Pasión","La Pasión es una de las tragedias ambientales más importantes del país. En el 2015\n químicos fueron vertidos al río, los cuales causaron un envenenamiento de varios\n organismos"));
        }
        else if(texto.equals("huehuetenango")){
            table.getItems().add(new rios("Río Ixcán","El rastro municipal contamina al río con heces, sangre y restos de animales"));
        }
        else if(texto.equals("izabal")){
            table.getItems().add(new rios("Río Motagua","20,000,000 kg de basura fluyen por el río anualmente"));
            table.getItems().add(new rios("Río Dulce","401,000kg de plástico son emitido anualmente"));
        }
        else if(texto.equals("chimaltenango")){
            table.getItems().add(new rios("Río Motagua","20,000,000 kg de basura fluyen por el río anualmente"));
            table.getItems().add(new rios("Río Coyolate","417,000kg de plástico son emitido anualmente"));

        }
        else if(texto.equals("sacatepéquez")){
            table.getItems().add(new rios("Río Coyolate","417,000kg de plástico son emitido anualmente"));
        }
        else if(texto.equals("escuintla")){
            table.getItems().add(new rios("Río Coyolate","417,000kg de plástico son emitido anualmente"));
            table.getItems().add(new rios("Río María Linda","1,258,000kg de plástico son emitido anualmente"));

        }
        else if(texto.equals("jutiapa")){
            table.getItems().add(new rios("Río Paz","En el 2016 el río fue contaminado con melaza, la cual causó la muerte de varios\n peces"));
        }
        else if(texto.equals("santa rosa")){
            table.getItems().add(new rios("Río de los Esclavos","Uno de los contaminantes más grandes al río es la fuente agrícola, o sea, las aguas,\nmieles y pulpa de café"));
        }
        else if(texto.equals("suchitepequez")){
            table.getItems().add(new rios("Río Coyolate","417,000kg de plástico son emitido anualmente"));
            table.getItems().add(new rios("Río Icán","365,000kg de plástico son emitido anualmente"));
            table.getItems().add(new rios("Río Nahualte","523,000kg de plástico son emitido anualmente"));
        }
        else if(texto.equals("quetzaltenango")){
            table.getItems().add(new rios("Río Samalá","626,000kg de plástico son emitido anualmente"));
            table.getItems().add(new rios("Río Naranjo","552,000kg de plástico son emitido anualmente"));
        }
        else if(texto.equals("san marcos")){
            table.getItems().add(new rios("Río Naranjo","552,000kg de plástico son emitido anualmente"));
            table.getItems().add(new rios("Río Suchiate","419,000kg de plástico son emitido anualmente"));
        }
    }


}