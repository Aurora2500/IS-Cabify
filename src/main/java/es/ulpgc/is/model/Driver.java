package es.ulpgc.is.model;

public class Driver {
    private String name;
    private String id;

    public Driver(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String name() {
        return name;
    }

    public String id() {
        return id;
    }
}
