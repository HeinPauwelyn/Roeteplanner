package be.howest.nmct.roeteplanner.repositories;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.howest.nmct.roeteplanner.classes.Locatie;

public class GoogleLocationRepo extends AsyncTask<String, Void, Locatie> {

    @Override
    public Locatie doInBackground(String... params) {
        try {

            String json = dowloadJson(params[0]);
            Locatie locatie = new Locatie();

            if (json != null) {

                JSONObject rootObject = new JSONObject(json);
                JSONObject resultaatNul = rootObject.getJSONArray("results").getJSONObject(0);
                JSONObject jsonLocatie = resultaatNul.getJSONObject("geometry").getJSONObject("location");

                locatie.setLatitude(jsonLocatie.getDouble("lat"));
                locatie.setLongitude(jsonLocatie.getDouble("lng"));

                JSONArray adresComponenten = resultaatNul.getJSONArray("address_components");

                locatie.setFormateerd(resultaatNul.getString("formatted_address"));

                for (int compIndex = 0; compIndex < adresComponenten.length(); compIndex++) {

                    JSONObject element = adresComponenten.getJSONObject(compIndex);
                    JSONArray types = element.getJSONArray("types");

                    for (int typeIndex = 0; typeIndex < types.length(); typeIndex++) {

                        switch (types.get(typeIndex).toString()) {

                            case "premise":
                            case "point_of_interest":
                            case "establishment":
                                locatie.setPlaatsnaam(element.getString("long_name"));
                                break;

                            case "route":
                                locatie.setStraat(element.getString("long_name"));
                                break;

                            case "street_number":
                                locatie.setNummer(element.getString("long_name"));
                                break;

                            case "sublocality":
                                locatie.setDeelgemeente(element.getString("long_name"));
                                break;

                            case "locality":
                                locatie.setGemeente(element.getString("long_name"));
                                break;

                            case "country":
                                locatie.setLand(element.getString("long_name"));
                                break;

                            case "postal_code":
                                locatie.setPostcode(element.getString("long_name"));
                                break;

                            default:
                                break;
                        }
                    }
                }
            }

            return locatie;
        }
        catch (JSONException ex) {
            return new Locatie();
        }
        catch (IOException ex) {
            return null;
        }
    }

    private String dowloadJson(String locatie) throws IOException{
        HttpURLConnection urlConnection = null;

        locatie = Uri.encode(locatie);
        String json = "";

        URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&language=nl-be&address=" + locatie);
        urlConnection = (HttpURLConnection) url.openConnection();

        BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        while (br.ready()) {
            json += br.readLine();
        }


        if (urlConnection != null) {
            urlConnection.disconnect();
        }

        return json;
    }
}
