package be.howest.nmct.roeteplanner.repositories;

import java.util.ArrayList;

import be.howest.nmct.roeteplanner.classes.Locatie;

public class LocatieRepo {
    private ArrayList<Locatie> _locaties = new ArrayList<Locatie>();

    public ArrayList<Locatie> getLocaties() {
        return _locaties;
    }

    public void setLocaties(ArrayList<Locatie> locaties) {
        _locaties = locaties;
    }

    public LocatieRepo() {
        readLocaties();
    }

    public void readLocaties(){

    }

    public void writeLocaties(){

    }

    public void addLocatie(Locatie locatie){
        _locaties.add(locatie);
    }
}
