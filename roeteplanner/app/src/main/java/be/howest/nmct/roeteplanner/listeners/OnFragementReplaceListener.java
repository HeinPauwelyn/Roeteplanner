package be.howest.nmct.roeteplanner.listeners;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;

public interface OnFragementReplaceListener {

    void newFragment(Fragment fragment);

    void newFragment(FragmentActivity fragmentActivity);
}
