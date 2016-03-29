package be.howest.nmct.roeteplanner;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import be.howest.nmct.roeteplanner.classes.Locatie;
import be.howest.nmct.roeteplanner.classes.LocatieSituatie;
import be.howest.nmct.roeteplanner.classes.OnFragementReplaceListener;
import be.howest.nmct.roeteplanner.helpers.LocatieRecyclerAdapter;
import be.howest.nmct.roeteplanner.repositories.LocatieRepo;
import butterknife.Bind;
import butterknife.ButterKnife;

public class LocatiesFragment extends Fragment  {

    @Bind(R.id.txvKopTekst) TextView txvKopTekst;
    @Bind(R.id.rcvLocaties) RecyclerView rcvLocaties;
    //@Bind(R.id.btnSelecteren) Button btnSelecteren;
    @Bind(R.id.btnNieuw) Button btnNieuw;

    private OnFragementReplaceListener _fragmentReplaceListener;
    private OnLocatieGevondenListener _locatieGevodenlistener;
    private static LocatieSituatie _situatie = LocatieSituatie.NIET_GEKEND;
    private static LocatieRepo _locatieRepo;
    private RecyclerView.LayoutManager _layoutManager;
    private LocatieRecyclerAdapter _locatieRecyclerAdapter;

    public LocatiesFragment() {
    }

    public static LocatiesFragment newInstance() {
        return newInstance(LocatieSituatie.NIET_GEKEND);
    }

    public static LocatiesFragment newInstance(LocatieSituatie situatie) {
        _situatie = situatie;

        return new LocatiesFragment();
    }

    public static LocatiesFragment newInstance(LocatieSituatie situatie, LocatieRepo locatieRepo) {
        _locatieRepo = locatieRepo;
        return newInstance(situatie);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_locaties, container, false);
        ButterKnife.bind(this, view);

        txvKopTekst.setText(_situatie.getTekst());

        _locatieGevodenlistener = (OnLocatieGevondenListener)getActivity();
        _layoutManager = new LinearLayoutManager(getActivity());
        _fragmentReplaceListener = (StartActivity) getActivity();

        rcvLocaties.clearDisappearingChildren();

        if (_locatieRecyclerAdapter != null) {
            _locatieRecyclerAdapter.notifyDataSetChanged();
        }

        rcvLocaties.setLayoutManager(null);
        rcvLocaties.setLayoutManager(_layoutManager);
        rcvLocaties.setItemAnimator(new DefaultItemAnimator());

        btnNieuw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_fragmentReplaceListener != null) {
                    _fragmentReplaceListener.newFragment(SmartNieuwLocatieFragement.newInstance());
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        _locatieRecyclerAdapter = new LocatieRecyclerAdapter(_locatieGevodenlistener, _situatie);

        _locatieRecyclerAdapter.notifyDataSetChanged();
        rcvLocaties.setAdapter(_locatieRecyclerAdapter);
    }

    public interface OnLocatieGevondenListener {
        void onLocatieGevonden(Locatie locatie, LocatieSituatie heenTerug);
    }
}
