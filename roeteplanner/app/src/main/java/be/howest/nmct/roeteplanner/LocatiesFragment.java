package be.howest.nmct.roeteplanner;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import be.howest.nmct.roeteplanner.classes.Locatie;
import be.howest.nmct.roeteplanner.classes.OnFragementReplaceListener;
import be.howest.nmct.roeteplanner.repositories.LocatieRepo;
import butterknife.Bind;
import butterknife.ButterKnife;

public class LocatiesFragment extends Fragment  {

    @Bind(R.id.txvKopTekst) TextView txvKopTekst;
    @Bind(R.id.lstLocaties) ListView lstLocaties;
    @Bind(R.id.btnSelecteren) Button btnSelecteren;
    @Bind(R.id.btnNieuw) Button btnNieuw;

    private OnFragementReplaceListener _fragmentReplaceListener;
    private OnLocatieGevondenListener _locatieGevodenlistener;
    private static String _kopTekst = "Uw locaties";
    private static LocatieRepo _locatieRepo;

    public LocatiesFragment() {
    }

    public static LocatiesFragment newInstance() {
        return newInstance("Uw locaties");
    }

    public static LocatiesFragment newInstance(String kopTekst) {
        _kopTekst = kopTekst;

        return new LocatiesFragment();
    }

    public static LocatiesFragment newInstance(String kopTekst, LocatieRepo locatieRepo) {
        _locatieRepo = locatieRepo;
        return newInstance(kopTekst);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_locaties, container, false);
        ButterKnife.bind(this, view);

        txvKopTekst.setText(_kopTekst);

        _fragmentReplaceListener = (StartActivity) getActivity();

        btnNieuw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_fragmentReplaceListener != null) {
                    _fragmentReplaceListener.newFragment(NieuweLocatieFragment.newInstance());
                }
            }
        });

        return view;
    }

    public interface OnLocatieGevondenListener {
        void onLocatieGevonden(Locatie locatie);
    }
}
