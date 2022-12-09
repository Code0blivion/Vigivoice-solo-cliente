package com.example.vigivoice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    private ServicioReconocimiento sr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED  &&
                ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_BACKGROUND_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO,Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 1000);
        }
        //startVoiceRecognitionActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sr.destruirServicio();
        //Toast.makeText(this,"Se destruye",Toast.LENGTH_SHORT).show();
    }


    public void salir(View view){
        System.exit(0);
    }

    public void irCaptura(View view){
        Intent captura = new Intent(this, CapturaAudio.class);
        if(sr!=null){
            sr.destruirServicio();
        }
        startActivity(captura);
    }

    public void irLista(View view){
        Intent lista = new Intent(this, ListaComandosPredeterminados.class);
        if(sr!=null){
            sr.destruirServicio();
        }
        startActivity(lista);
    }

    public void irCustom(View view){
        Intent custom = new Intent(this, ComandosPersonalizados.class);
        if(sr!=null){
            sr.destruirServicio();
        }
        startActivity(custom);
    }

    public void irContactos(View view){
        Intent contactos = new Intent(this, Contactos.class);
        if(sr!=null){
            sr.destruirServicio();
        }
        startActivity(contactos);
    }

    public void iniciarEscucha(View view){
        //Añadir condicional en el caso de que no haya ningun numero registrado
        sr = new ServicioReconocimiento(this);
    }

/*
    public void startVoiceRecognitionActivity() {
        String lang = "es-ES";
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speech recognition demo");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, lang);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, lang);
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, lang);
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            // matches is the result of voice input. It is a list of what the
            // user possibly said.
            // Using an if statement for the keyword you want to use allows the
            // use of any activity if keywords match
            // it is possible to set up multiple keywords to use the same
            // activity so more than one word will allow the user
            // to use the activity (makes it so the user doesn't have to
            // memorize words from a list)
            // to use an activity from the voice input information simply use
            // the following format;
            // if (matches.contains("keyword here") { startActivity(new
            // Intent("name.of.manifest.ACTIVITY")

            if (matches.contains("información")) {
                Toast.makeText(this,"Comando recibido",Toast.LENGTH_LONG).show();
            }
        }
    }

 */


}