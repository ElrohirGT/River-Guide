package com.river_guide.riverguide;

public class Department {
    private int _id;

    private String _name;

    public Department(int id, String name) {
        _id = id;
        _name = name;
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }
}
