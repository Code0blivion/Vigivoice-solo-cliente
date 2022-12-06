package com.example.vigivoice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }

        Intent intent = new Intent(this,ServicioReconocimiento.class);
        //startService(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this,"Se destruye",Toast.LENGTH_SHORT).show();
    }

    public void salir(View view){
        System.exit(0);
    }

    public void irCaptura(View view){
        Intent captura = new Intent(this, CapturaAudio.class);
        startActivity(captura);
    }

    public void irLista(View view){
        Intent lista = new Intent(this, ListaComandosPredeterminados.class);
        startActivity(lista);
    }

    public void irCustom(View view){
        Intent custom = new Intent(this, ComandosPersonalizados.class);
        startActivity(custom);
    }

    public void irContactos(View view){
        Intent contactos = new Intent(this, Contactos.class);
        startActivity(contactos);
    }

}