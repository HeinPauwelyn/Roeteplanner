package be.howest.nmct.roeteplanner.repositories;

import android.os.NetworkOnMainThreadException;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask;

import be.howest.nmct.roeteplanner.classes.Locatie;

public class GoogleLocationRepo extends AsyncTask<String, Void, Locatie> {

    @Override
    public Locatie doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        String tekst;

        try {
            URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&language=nl-be&address=" + params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
            tekst = "" + in.read();
        }
        catch (Exception ex){
            return null;
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return new Locatie(tekst, "");
    }
}
