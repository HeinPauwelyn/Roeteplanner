package be.howest.nmct.roeteplanner;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import be.howest.nmct.roeteplanner.classes.OnFragementReplaceListener;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NavigationFragment extends Fragment {

    @Bind (R.id.btnToonRoete) Button btnToonRoete;
    @Bind (R.id.btnZoekAankomstpunt) ImageButton btnZoekAankomstpunt;
    @Bind (R.id.btnZoekVertekpunt) ImageButton btnZoekVertekpunt;
    @Bind (R.id.edtAankomstpunt) EditText edtAankomstpunt;
    @Bind (R.id.edtVertrekpunt) EditText edtVertrekpunt;

    private OnFragementReplaceListener _fragmentReplaceListener;

    public NavigationFragment() {
    }

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        ButterKnife.bind(this, view);

        _fragmentReplaceListener = (StartActivity)getActivity();

        btnZoekAankomstpunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _fragmentReplaceListener.newFragment(LocatiesFragment.newInstance("Kies uw aankomst locatie"));
            }
        });

        btnZoekVertekpunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _fragmentReplaceListener.newFragment(LocatiesFragment.newInstance("Kies uw vertrek locatie"));
            }
        });

        return view;
    }
}
