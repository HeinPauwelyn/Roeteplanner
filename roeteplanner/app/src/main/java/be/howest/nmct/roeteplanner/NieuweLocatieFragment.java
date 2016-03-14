package be.howest.nmct.roeteplanner;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.howest.nmct.roeteplanner.classes.Locatie;

public class NieuweLocatieFragment extends Fragment {

    private OnNieuweLocatieCreatieListener mListener;

    public NieuweLocatieFragment() {
    }

    public static NieuweLocatieFragment newInstance() {
        return new NieuweLocatieFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nieuwe_locatie, container, false);
    }

    public interface OnNieuweLocatieCreatieListener {

        void onNieuweLocatieCreeerd(Locatie locatie);
    }
}
