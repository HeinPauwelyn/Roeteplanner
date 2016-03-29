package be.howest.nmct.roeteplanner.repositories;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import be.howest.nmct.roeteplanner.classes.Locatie;

public class LocatieRepo {

    private static ArrayList<Locatie> _locaties = new ArrayList<Locatie>();
    private final String _bestandsnaam = "Roeteplaner_locaties.json";
    private Context _ctx;

    public LocatieRepo(Context ctx) {
        _ctx = ctx;
        leesLocaties();
    }

    public static ArrayList<Locatie> getLocaties() {
        return _locaties;
    }

    public void leesLocaties(){

        if (_ctx != null && _locaties.size() == 0) {
            try {

                JSONArray jsonArrayLocaties = new JSONArray(leesJsonData());

                for (int locatieIndex = 0; locatieIndex < jsonArrayLocaties.length(); locatieIndex++) {

                    JSONObject locatieJson = jsonArrayLocaties.getJSONObject(locatieIndex);

                    _locaties.add(new Locatie(locatieJson.optString("gemeente"),
                                              locatieJson.optString("postcode"),
                                              locatieJson.optString("nummer"),
                                              locatieJson.optString("straat"),
                                              locatieJson.optString("land"),
                                              locatieJson.optString("plaatsnaam"),
                                              locatieJson.optString("deelgemeente"),
                                              locatieJson.optString("formateerd")
                    ));
                }
            }
            catch (IOException | JSONException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    public void shrijfLocaties(){

        try {
            String tekst = schrijfJsonData();

            if (_ctx != null) {
                FileOutputStream fos = _ctx.openFileOutput(_bestandsnaam, Context.MODE_PRIVATE);
                fos.write(tekst.getBytes());
                fos.close();
            }
        }
        catch (JSONException | IOException ex){
            Log.d("exception", ex.getMessage());
        }
    }

    public void addLocatie(Locatie locatie){
        //_locaties.clear();
        _locaties.add(locatie);
    }

    private String schrijfJsonData() throws JSONException {

        JSONArray jsonArrayLocaties = new JSONArray();

        for (Locatie locatie : _locaties) {
            jsonArrayLocaties.put(new JSONObject().put("plaatsnaam", locatie.getPlaatsnaam())
                                                  .put("straat", locatie.getStraat())
                                                  .put("nummer", locatie.getNummer())
                                                  .put("gemeente", locatie.getGemeente())
                                                  .put("postcode", locatie.getPostcode())
                                                  .put("deelgemeente", locatie.getDeelgemeente())
                                                  .put("land", locatie.getLand())
                                                  .put("formateerd", locatie.getFormateerd())
            );
        }

        return jsonArrayLocaties.toString();
    }

    private String leesJsonData() throws JSONException, IOException {

        FileInputStream fis = _ctx.openFileInput(_bestandsnaam);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }

        fis.close();

        return sb.toString();
    }
}
