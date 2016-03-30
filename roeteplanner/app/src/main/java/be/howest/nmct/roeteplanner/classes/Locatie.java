package be.howest.nmct.roeteplanner.classes;

import java.io.Serializable;

public class Locatie implements Serializable {

    private String _gemeente;
    private String _postcode;
    private String _nummer;
    private String _straat;
    private String _land;
    private String _plaatsnaam;
    private String _formateerd = "";
    private String _deelgemeente;
    private double _longitude;
    private double _latitude;

    public String getPlaatsnaam() {
        return _plaatsnaam;
    }

    public void setPlaatsnaam(String plaatsnaam) {
        _plaatsnaam = plaatsnaam;
    }

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

    public String getPostcode() {
        return _postcode;
    }

    public void setPostcode(String postcode) {
        _postcode = postcode;
    }

    public String getNummer() {
        return _nummer;
    }

    public void setNummer(String nummer) {
        _nummer = nummer;
    }

    public String getLand() {
        return _land;
    }

    public void setFormateerd(String formateerd) {
        _formateerd = formateerd;
    }

    public String getFormateerd() {
        return _formateerd;
    }

    public void setLand(String land) {
        _land = land;
    }

    public void setDeelgemeente(String deelgemeente) {
        _deelgemeente = deelgemeente;
    }

    public String getDeelgemeente() {
        return _deelgemeente;
    }

    public double getLongitude() {
        return _longitude;
    }

    public void setLongitude(double longitude) {
        _longitude = longitude;
    }

    public double getLatitude() {
        return _latitude;
    }

    public void setLatitude(double latitude) {
        _latitude = latitude;
    }

    public Locatie(String gemeente, String postcode, String nummer, String straat, String land, String plaatsnaam, String deelgemeente, String formateerd, double longitude, double latitude) {

        this(gemeente, straat);
        _postcode = postcode;
        _nummer = nummer;
        _land = land;
        _plaatsnaam = plaatsnaam;
        _deelgemeente = deelgemeente;
        _formateerd = formateerd;
        _latitude = latitude;
        _longitude = longitude;
    }

    public Locatie(String gemeente, String straat) {
        _gemeente = gemeente;
        _straat = straat;
    }

    public Locatie() {
    }

    @Override
    public String toString() {
        return _formateerd;
    }

    @Override
    public boolean equals(Object arg){

        if (arg instanceof Locatie){
            Locatie locatie = (Locatie)arg;

            return locatie._formateerd.equals(_formateerd);
        }

        return false;
    }
}
