package be.howest.nmct.roeteplanner.classes;

import android.widget.Button;

public interface INieweLocatieFragment {

    OnNieuweLocatieCreatieListener _nieuweLocatieCreatieListener = null;
    OnFragementReplaceListener _fragmentReplaceListener = null;

    Button btnAnnuleren = null;
    Button btnToevoegen = null;
}
