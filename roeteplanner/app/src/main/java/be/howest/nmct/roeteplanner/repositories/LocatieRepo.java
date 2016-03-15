package be.howest.nmct.roeteplanner.repositories;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import be.howest.nmct.roeteplanner.classes.Locatie;

public class LocatieRepo {
    private ArrayList<Locatie> _locaties = new ArrayList<Locatie>();
    private final String _bestandsnaam = "Roeteplaner_locaties.csv";

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

        String tekst = "";

        try {

            //FileOutputStream fos = openFileOutput(_bestandsnaam, Context.MODE_PRIVATE);
            //fos.write(tekst.getBytes());
            //fos.close();
        }
        catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public void writeLocaties(){

    }

    public void addLocatie(Locatie locatie){
        _locaties.add(locatie);
    }
}
