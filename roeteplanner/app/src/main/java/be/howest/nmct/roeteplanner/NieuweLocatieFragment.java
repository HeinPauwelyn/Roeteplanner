package be.howest.nmct.roeteplanner;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import be.howest.nmct.roeteplanner.classes.Locatie;
import be.howest.nmct.roeteplanner.classes.OnFragementReplaceListener;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NieuweLocatieFragment extends Fragment {

    @Bind(R.id.btnToevoegen) Button btnToevoegen;
    @Bind(R.id.btnAnnuleren) Button btnAnnuleren;
    @Bind(R.id.edtGemeente) EditText edtGemeente;
    @Bind(R.id.edtStraat) EditText edtStraat;

    private OnNieuweLocatieCreatieListener _nieuweLocatieCreatieListener;
    private OnFragementReplaceListener _fragmentReplaceListener;

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
        View view = inflater.inflate(R.layout.fragment_nieuwe_locatie, container, false);
        ButterKnife.bind(this, view);

        _fragmentReplaceListener = (StartActivity) getActivity();

        btnAnnuleren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _fragmentReplaceListener.newFragment(LocatiesFragment.newInstance());
            }
        });

        return  view;
    }

    public interface OnNieuweLocatieCreatieListener {

        void onNieuweLocatieCreeerd(Locatie locatie);
    }
}
