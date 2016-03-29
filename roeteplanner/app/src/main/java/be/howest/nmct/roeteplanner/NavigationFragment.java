package be.howest.nmct.roeteplanner;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import be.howest.nmct.roeteplanner.classes.Locatie;
import be.howest.nmct.roeteplanner.classes.LocatieSituatie;
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
    private static LocatieSituatie _heenTerug;
    private static Locatie _locatie;
    private static final String VERTREK_STRING = "vertek locatie string";
    private static final String AANKOMST_STRING = "aankomst locatie string";
    private static final String VERTREK_OBJECT = "vertrek locatie object";
    private static final String AANKOMST_OBJECT = "aankomst locatie object";

    public NavigationFragment() {
    }

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    public static NavigationFragment newInstance(Locatie locatie, LocatieSituatie heenTerug){
        _locatie = locatie;
        _heenTerug = heenTerug;

        return  newInstance();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(AANKOMST_STRING, edtAankomstpunt.getText().toString());
        savedInstanceState.putString(VERTREK_STRING, edtVertrekpunt.getText().toString());

        savedInstanceState.putString(AANKOMST_OBJECT, edtAankomstpunt.getText().toString());
        savedInstanceState.putString(VERTREK_OBJECT, edtVertrekpunt.getText().toString());
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
                _fragmentReplaceListener.newFragment(LocatiesFragment.newInstance(LocatieSituatie.AANKOMST));
            }
        });

        btnZoekVertekpunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _fragmentReplaceListener.newFragment(LocatiesFragment.newInstance(LocatieSituatie.VERTREK));
            }
        });

        if (_locatie != null){
            switch (_heenTerug){
                case VERTREK:
                    edtVertrekpunt.setText(_locatie.toString());
                    edtVertrekpunt.setTag(_locatie);
                    break;

                case AANKOMST:
                    edtAankomstpunt.setText(_locatie.toString());
                    edtAankomstpunt.setTag(_locatie);
                    break;

                case NIET_GEKEND:
                    break;
            }
        }

        return view;
    }
}
