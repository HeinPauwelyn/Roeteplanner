package be.howest.nmct.roeteplanner;

import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import be.howest.nmct.roeteplanner.classes.LocatieSituatie;
import be.howest.nmct.roeteplanner.listeners.OnActivityReplaceListener;
import be.howest.nmct.roeteplanner.listeners.OnFragementReplaceListener;
import be.howest.nmct.roeteplanner.classes.Route;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NavigationFragment extends Fragment {

    @Bind (R.id.btnToonRoete) Button btnToonRoete;
    @Bind (R.id.btnZoekAankomstpunt) ImageButton btnZoekAankomstpunt;
    @Bind (R.id.btnZoekVertekpunt) ImageButton btnZoekVertekpunt;
    @Bind (R.id.edtAankomstpunt) EditText edtAankomstpunt;
    @Bind (R.id.edtVertrekpunt) EditText edtVertrekpunt;

    private OnFragementReplaceListener _fragmentReplaceListener;
    private OnActivityReplaceListener<RoeteActivity> _activityReplaceListener;

    private static Route _route;

    public NavigationFragment() {
    }

    public static NavigationFragment newInstance(Route route) {
        _route = route;

        return new NavigationFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        ButterKnife.bind(this, view);

        //_activityReplaceListener = (StartActivity)getActivity();
        _fragmentReplaceListener = (StartActivity)getActivity();

        btnZoekAankomstpunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _fragmentReplaceListener.newFragment(LocatiesFragment.newInstance(LocatieSituatie.AANKOMST));
            }
        });

        btnZoekVertekpunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _fragmentReplaceListener.newFragment(LocatiesFragment.newInstance(LocatieSituatie.VERTREK));
            }
        });

        btnToonRoete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (_route.getVertrekLocatie() != null && _route.getAankomstLocatie() != null) {

                    _fragmentReplaceListener.newFragment(RoeteActivity.newInstance(_route));
                }
                else {
                    Snackbar.make(v, "Geef uw vertrek en aankomst locatie in.", Snackbar.LENGTH_LONG).show();
                }
                //_fragmentReplaceListener.newFragment(new FragmentActivity());
            }
        });

        if (_route.getAankomstLocatie() != null) {
            edtAankomstpunt.setText(_route.getAankomstLocatie().toString());
        }

        if (_route.getVertrekLocatie() != null) {
            edtVertrekpunt.setText(_route.getVertrekLocatie().toString());
        }

        return view;
    }
}
