package com.river_guide.riverguide;

import javafx.beans.property.SimpleStringProperty;

public class rios {
    SimpleStringProperty nombre;
    SimpleStringProperty contami;

    public rios(String nombre, String contami) {
        this.nombre = new SimpleStringProperty(nombre);
        this.contami = new SimpleStringProperty(contami);

    }

    public String getContami() {
        return contami.get();
    }

    public void setContami(String contami) {
        this.contami = new SimpleStringProperty(contami);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

}
