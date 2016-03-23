package be.howest.nmct.roeteplanner.repositories;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import be.howest.nmct.roeteplanner.classes.Locatie;

public class LocatieRepo {

    private static ArrayList<Locatie> _locaties = new ArrayList<Locatie>();
    private final String _bestandsnaam = "Roeteplaner_locaties.csv";
    private Context _ctx;

    public static ArrayList<Locatie> getLocaties() {
        return _locaties;
    }

    public void setLocaties(ArrayList<Locatie> locaties) {
        _locaties = locaties;
    }

    public LocatieRepo() {
    }

    public LocatieRepo(Context ctx) {
        _ctx = ctx;
        readLocaties();
    }

    public void readLocaties(){

        if (_ctx != null) {
            try {
                FileInputStream fos = _ctx.openFileInput(_bestandsnaam);
                int i = fos.read();
                fos.close();
            }
            catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    private String schrijfCsvData() {

        StringBuilder tekst = new StringBuilder();

        for (Locatie locatie : _locaties) {
            tekst.append(locatie.getGemeente());
            tekst.append(";");
            tekst.append(locatie.getStraat());
            tekst.append("\r\n");
        }

        return tekst.toString();
    }

    public void writeLocaties(){

        String tekst = schrijfCsvData();

        if (_ctx != null) {
            try {
                FileOutputStream fos = _ctx.openFileOutput(_bestandsnaam, Context.MODE_PRIVATE);
                fos.write(tekst.getBytes());
                fos.close();
            }
            catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public void addLocatie(Locatie locatie){
        _locaties.add(locatie);
    }

}
