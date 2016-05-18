package be.howest.nmct.roeteplanner;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import be.howest.nmct.roeteplanner.classes.Locatie;
import be.howest.nmct.roeteplanner.classes.LocatieSituatie;
import be.howest.nmct.roeteplanner.listeners.OnFragementReplaceListener;
import be.howest.nmct.roeteplanner.listeners.OnNieuweLocatieCreatieListener;
import be.howest.nmct.roeteplanner.classes.Route;
import be.howest.nmct.roeteplanner.repositories.LocatieRepo;

public class StartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnFragementReplaceListener, OnNieuweLocatieCreatieListener, LocatiesFragment.OnLocatieGevondenListener {

    private LocatieRepo _locatieRepo = null;
    private Route _route = new Route();
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            toonFragment(NavigationFragment.newInstance(_route));
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

        if (id == R.id.action_nieuwelocatie) {

            toonFragment(SmartNieuwLocatieFragement.newInstance());
            return true;
        }
        else if (id == R.id.action_navigatie){

            toonFragment(NavigationFragment.newInstance(_route));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_navigatie) {
            toonFragment(NavigationFragment.newInstance(_route));
        }
        else if (id == R.id.nav_locatie) {
            toonFragment(LocatiesFragment.newInstance());
        }
        else if (id == R.id.nav_wereld) {
            //Intent intent = new Intent(this, RoeteActivity.class);
            toonFragment(RoeteActivity.newInstance());
        }
        else if (id == R.id.nav_nieuwe_locatie) {
            toonFragment(SmartNieuwLocatieFragement.newInstance());
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
        transaction.addToBackStack("transaction_fromOldFragment_toNewFragment");
        transaction.commit();
    }

    @Override
    public void newFragment(Fragment fragment) {
        toonFragment(fragment);
    }

    @Override
    public void newFragment(Fragment fragment, Bundle bundle) {
        //toonFragment(fragment, bundle);
    }


    @Override
    public void onNieuweLocatieCreeerd(Locatie locatie) {
        _locatieRepo.addLocatie(locatie);
    }

    @Override
    public void onLocatieGevonden(Locatie locatie, LocatieSituatie heenTerug) {

        switch (heenTerug){

            case VERTREK:
                _route.setVertrekLocatie(locatie);
                break;

            case AANKOMST:
                _route.setAankomstLocatie(locatie);
                break;

            case NIET_GEKEND:
                return;
        }

        toonFragment(NavigationFragment.newInstance(_route));
    }
}
