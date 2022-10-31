package com.river_guide.riverguide;

import javafx.beans.property.SimpleStringProperty;

public class departamentos {
    SimpleStringProperty departamento;
    public departamentos(){

    }
    public departamentos(String departamento){
        this.departamento = new SimpleStringProperty(departamento);
    }

    public String getDepartamento() {
        return departamento.get();
    }

    public void setDepartamento(String departamento) {
        this.departamento = new SimpleStringProperty(departamento);
    }
}
