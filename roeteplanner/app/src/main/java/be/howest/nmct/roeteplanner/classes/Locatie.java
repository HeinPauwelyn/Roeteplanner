package be.howest.nmct.roeteplanner.classes;

public class Locatie {

    private String _gemeente;
    private String _postcode;
    private String _nummer;
    private String _straat;
    private String _land;
    private String _plaatsnaam;
    private String _formateerd = "";
    private String _deelgemeente;

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

    public Locatie(String gemeente, String postcode, String nummer, String straat, String land, String plaatsnaam, String deelgemeente, String formateerd) {

        this(gemeente, straat);
        _postcode = postcode;
        _nummer = nummer;
        _land = land;
        _plaatsnaam = plaatsnaam;
        _deelgemeente = deelgemeente;
        _formateerd = formateerd;
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
