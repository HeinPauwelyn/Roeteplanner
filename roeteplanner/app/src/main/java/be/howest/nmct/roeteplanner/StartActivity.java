package be.howest.nmct.roeteplanner;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;

import be.howest.nmct.roeteplanner.classes.Locatie;
import be.howest.nmct.roeteplanner.classes.LocatieSituatie;
import be.howest.nmct.roeteplanner.classes.OnActivityReplaceListener;
import be.howest.nmct.roeteplanner.classes.OnFragementReplaceListener;
import be.howest.nmct.roeteplanner.classes.OnNieuweLocatieCreatieListener;
import be.howest.nmct.roeteplanner.classes.Roete;
import be.howest.nmct.roeteplanner.repositories.LocatieRepo;

public class StartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnFragementReplaceListener, OnActivityReplaceListener<RoeteActivity>, OnNieuweLocatieCreatieListener, LocatiesFragment.OnLocatieGevondenListener {

    private LocatieRepo _locatieRepo = null;
    private Roete _roete = new Roete();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            toonFragment(NavigationFragment.newInstance(_roete));
        }

        _locatieRepo = new LocatieRepo(getApplicationContext());
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() <= 1) {
            super.onBackPressed();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_navigatie) {

            toonFragment(NavigationFragment.newInstance(_roete));
        }

        else if (id == R.id.nav_locatie) {

            toonFragment(LocatiesFragment.newInstance());
        }

        else if (id == R.id.nav_instellingen) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        _locatieRepo.shrijfLocaties();
    }

    private  void toonFragment(Fragment fragment) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frmlytContentMain, fragment);
        //transaction.add(R.id.frmlytContentMain, fragment, fragment.toString());
        transaction.addToBackStack("transaction_fromOldFragment_toNewFragment");
        transaction.commit();
    }

    @Override
    public void newFragment(Fragment fragment) {
        toonFragment(fragment);
    }

    @Override
    public void newFragment(FragmentActivity fragmentActivity) {

    }

    @Override
    public void onNieuweLocatieCreeerd(Locatie locatie) {
        _locatieRepo.addLocatie(locatie);
    }

    @Override
    public void onLocatieGevonden(Locatie locatie, LocatieSituatie heenTerug) {

        switch (heenTerug){

            case VERTREK:
                _roete.setVertrekLocatie(locatie);
                break;

            case AANKOMST:
                _roete.setAankomstLocatie(locatie);
                break;

            case NIET_GEKEND:
                return;
        }

        toonFragment(NavigationFragment.newInstance(_roete));
    }

    @Override
    public void newActivity(Class<RoeteActivity> activity) {

        Intent intent = new Intent(this, activity);
        intent.putExtra("roete", _roete);
        startActivity(intent);
    }
}
