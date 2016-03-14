package be.howest.nmct.roeteplanner.classes;

public class Locatie {

    private String _gemeente;
    private String _straat;

    public String getGemeente() {
        return _gemeente;
    }

    public void setGemeente(String gemeente) {
        _gemeente = gemeente;
    }

    public String getStraat() {
        return _straat;
    }

    public void setStraat(String straat) {
        _straat = straat;
    }

    public Locatie() {
    }

    public Locatie(String gemeente, String straat) {
        _gemeente = gemeente;
        _straat = straat;
    }
    
    @Override
    public String toString() {
        return _gemeente + "\r\n" + _straat;
    }
}
