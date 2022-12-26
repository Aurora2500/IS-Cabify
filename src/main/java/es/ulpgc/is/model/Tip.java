package es.ulpgc.is.model;

public class Tip {
    private Chofer chofer;
    private double tip;

    public Tip() {
        this.chofer = chofer;
        this.tip = tip;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public Chofer chofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }
}
