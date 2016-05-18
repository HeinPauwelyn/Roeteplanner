package be.howest.nmct.roeteplanner.classes;

import java.io.Serializable;

public class Route implements Serializable {

    private Locatie _aankomstLocatie;
    private Locatie _vertrekLocatie;

    public Route() {
    }

    public Locatie getAankomstLocatie() {
        return _aankomstLocatie;
    }

    public void setAankomstLocatie(Locatie aankomstLocatie) {
        _aankomstLocatie = aankomstLocatie;
    }

    public Locatie getVertrekLocatie() {
        return _vertrekLocatie;
    }

    public void setVertrekLocatie(Locatie vertrekLocatie) {
        _vertrekLocatie = vertrekLocatie;
    }
}
