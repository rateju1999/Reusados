package com.example.reusados;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.reusados.fragments.FragmentOpc1;
import com.example.reusados.fragments.FragmentOpc2;
import com.example.reusados.fragments.FragmentOpc3;
import com.example.reusados.fragments.FragmentSubOpc1;
import com.example.reusados.fragments.FragmentSubOpc2;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar appBar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asignamos la toolbar como action bar
        appBar = findViewById(R.id.appbar);
        setSupportActionBar(appBar);


        //Icono de hamburguesa
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Necesitamos la referencia al drawerlayout para cerrarlo al pulsar sobre una opción
        drawerLayout = (DrawerLayout) findViewById(R.id.mainActivity);

        //Menu Navigation listener
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        FragmentMain fragmentoMain = FragmentMain.newInstance();
//
//        fragmentTransaction.replace(R.id.fragment_container, fragmentoMain);
        FragmentTiposPrenda fragmentoMain = FragmentTiposPrenda.newInstance();

        fragmentTransaction.replace(R.id.fragment_container, fragmentoMain);

        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.menu_opcion_1:
                fragment = FragmentOpc1.newInstance();
                break;
            case R.id.menu_opcion_2:
                fragment = FragmentOpc2.newInstance();
                break;
            case R.id.menu_opcion_3:
                fragment = FragmentOpc3.newInstance();
                break;
            case R.id.menu_subopcion_1:
                fragment = FragmentSubOpc1.newInstance();
                break;
            case R.id.menu_subopcion_2:
                fragment = FragmentSubOpc2.newInstance();
                break;
        }

        //Cambiamos el fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();




        //Cambiamos el título de la appbar por el texto de la opción pulsada
        getSupportActionBar().setTitle(menuItem.getTitle());

        //Cerramos la NavigationDrawer
        drawerLayout.closeDrawers();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



