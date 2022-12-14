package com.river_guide.riverguide;

import javafx.beans.property.SimpleStringProperty;

public class River {
    private int _id;
    private SimpleStringProperty _nombre;
    private SimpleStringProperty _contami;
    private String _extraInfo;

    public River(int id, String nombre, String contami, String extraInfo) {
        _id = id;
        _nombre = new SimpleStringProperty(nombre);
        _contami = new SimpleStringProperty(contami);
        _extraInfo = extraInfo;
    }

    public String getExtraInfo() {
        return _extraInfo;
    }

    public int getId() {
        return _id;
    }

    public String getContami() {
        return _contami.get();
    }

    public void setContami(String contami) {
        _contami.set(contami);
    }

    public String getNombre() {
        return _nombre.get();
    }

    public void setNombre(String nombre) {
        _nombre.set(nombre);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_nombre == null) ? 0 : _nombre.hashCode());
        result = prime * result + ((_contami == null) ? 0 : _contami.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        River other = (River) obj;
        if (getNombre() == null) {
            if (other.getNombre() != null)
                return false;
        } else if (!getNombre().equals(other.getNombre()))
            return false;
        if (getContami() == null) {
            if (other.getContami() != null)
                return false;
        } else if (!getContami().equals(other.getContami()))
            return false;
        return true;
    }

    public void setExtraInfo(String extraInfo) {
        _extraInfo = extraInfo;
    }

}
