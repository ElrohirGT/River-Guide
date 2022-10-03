package com.river_guide.riverguide;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;

public class rios  {
    SimpleStringProperty nombre;
    SimpleStringProperty contami1;

    public rios(String nombre, String contami1){
        this.nombre= new SimpleStringProperty(nombre);
        this.contami1=new SimpleStringProperty(contami1);

    }
    public String getContami1(){
        return contami1.get();
    }
    public void setContami1(String contami1){
        this.contami1=new SimpleStringProperty(contami1);
    }


    public String getNombre(){
        return nombre.get();
    }
    public void setNombre(String nombre){
        this.nombre=new SimpleStringProperty(nombre);
    }


}
