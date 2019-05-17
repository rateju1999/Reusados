package com.example.reusados;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, ChildEventListener, View.OnClickListener , FragmentPrendaDetalle.OnFragmentInteractionListener,FragmentCarrito.OnFragmentInteractionListener{
    public static final int NUMERO_CARRITO = 0;
    private Toolbar appBar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private ImageView carrito;
    private ImageView logo;
    public TextView carrito_numero;
    private ArrayList<Prenda> prendas;
    private int numPrendasCarrito;
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
        navView.setBackgroundColor(Color.WHITE);

        prendas = new ArrayList<>();
        Query bdNodoReusados = FirebaseDatabase.getInstance().getReference()
                .child("carrito");
        bdNodoReusados.addChildEventListener(this);
        numPrendasCarrito = prendas.size();
        carrito_numero = findViewById(R.id.carrito_numero);
        carrito_numero.setText("" + numPrendasCarrito);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentMain fragmentoMain = FragmentMain.newInstance();
        fragmentTransaction.replace(R.id.fragment_container, fragmentoMain);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        carrito = findViewById(R.id.carrito);
        carrito.setOnClickListener(this);
        logo = findViewById(R.id.logoReusado);
        logo.setOnClickListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.menu_opcion_1:
                fragment = FragmentMain.newInstance();
                break;
            case R.id.menu_opcion_2:
                fragment = FragmentTiposPrenda.newInstance();
                break;
            case R.id.menu_opcion_3:
                fragment = FragmentPrenda.newInstance("TODAS");
                break;
            case R.id.menu_opcion_4:
                fragment = FragmentCarrito.newInstance();
                break;
            case R.id.menu_opcion_5:
                fragment = FragmentInfo.newInstance();
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

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.carrito:
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentCarrito fragmentoMain = FragmentCarrito.newInstance();
            fragmentTransaction.replace(R.id.fragment_container, fragmentoMain);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
                break;
            case R.id.logoReusado:
                Uri uri = Uri.parse("https://reusadovintage.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void cambiarNumero(int num) {
        carrito_numero.setText("" + num);

    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Prenda p = dataSnapshot.getValue(Prenda.class);
        p.setKey(dataSnapshot.getKey());
        prendas.add(p);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}



