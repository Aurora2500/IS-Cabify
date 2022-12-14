package es.ulpgc.is.model;

public class Chofer {
    private String name;
    private String id;

    public Chofer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
