package es.ulpgc.is.model;

public class Tip {
    private Chofer chofer;
    private int tip;

    public Tip(Chofer chofer, int tip) {
        this.chofer = chofer;
        this.tip = tip;
    }

    public int tip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public Chofer chofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }
}
