package be.howest.nmct.roeteplanner.classes;

public enum LocatieSituatie {

    VERTREK("Kies uw vertrek locatie"),
    AANKOMST("Kies uw aankomst locatie"),
    NIET_GEKEND("Uw locaties");

    private String _tekst;

    private LocatieSituatie(String tekst) {
        _tekst = tekst;
    }

    public String getTekst() {
        return _tekst;
    }
}
