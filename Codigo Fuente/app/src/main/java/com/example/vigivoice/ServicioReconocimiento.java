package com.example.vigivoice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Looper;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.telephony.SmsManager;
import android.text.method.Touch;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ServicioReconocimiento {

    private SpeechRecognizer mRecognizer;
    private FusedLocationProviderClient cliente;
    private String[] info = new String[2];
    private RecognitionListener mRecognitionListener = new RecognitionListener() {
        @Override
        public void onError(int error) {
            if ((error == SpeechRecognizer.ERROR_NO_MATCH) ||
                    (error == SpeechRecognizer.ERROR_SPEECH_TIMEOUT)) {
                startSpeechRecognition();
                return;
            }
        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> values = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            SharedPreferences preferenciascom = contexto.getSharedPreferences("Comandos", Context.MODE_PRIVATE);
            getLocation();
            String temp1 = preferenciascom.getString("comando1", "");
            String temp2 = preferenciascom.getString("comando2", "");
            String temp3 = preferenciascom.getString("comando3", "");
            String temp4 = preferenciascom.getString("comando4", "");
            String mensaje1 ="";
            String mensaje2 = "";
            String mensaje3 = "";
            String mensaje4 = "";
            SharedPreferences preferenciascon = contexto.getSharedPreferences("Contactos", Context.MODE_PRIVATE);

            SmsManager mensajero = SmsManager.getDefault();

            for (String res : values) {
                if (res.equalsIgnoreCase(temp1) || res.equalsIgnoreCase("0528")) {
                    Toast.makeText(contexto, "Comando recibido: Caso de ROBO", Toast.LENGTH_LONG).show();
                    mensaje1="[VigiVoice] ALERTA. PEPITO HA SIDO VICTIMA DE ROBO";
                    mensaje4 = "Su ubicación es:";
                    mensaje2=" Latitud: " + info[0];
                    mensaje3=" Longitud: " + info[1];
                    for (int i = 0; i < 4; i++) {
                        Long templ = preferenciascon.getLong("contacto" + (i + 1), 0);
                        if (templ != 0L) {
                            //Enviar mensaje de robo
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje1, null, null);
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje4, null, null);
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje2, null, null);
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje3, null, null);
                        }
                    }
                }
                if (res.equalsIgnoreCase(temp2) || res.equalsIgnoreCase("2387")) {
                    Toast.makeText(contexto, "Comando recibido: Caso de ASALTO", Toast.LENGTH_LONG).show();
                    mensaje1="[VigiVoice] ALERTA. PEPITO HA SIDO VICTIMA DE ASALTO";
                    mensaje4 = "Su ubicación es:";
                    mensaje2=" Latitud: " + info[0];
                    mensaje3=" Longitud: " + info[1];
                    for (int i = 0; i < 4; i++) {
                        if (preferenciascon.getLong("contacto" + (i + 1), 0) != 0) {
                            Long templ = preferenciascon.getLong("contacto" + (i + 1), 0);
                            if (templ != 0L) {
                                //Enviar mensaje de asalto
                                mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje1, null, null);
                                mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje4, null, null);
                                mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje2, null, null);
                                mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje3, null, null);
                            }
                        }
                    }
                }
                if (res.equalsIgnoreCase(temp3) || res.equalsIgnoreCase("4158")) {
                    Toast.makeText(contexto, "Comando recibido: Caso de SECUESTRO", Toast.LENGTH_LONG).show();
                    mensaje1="[VigiVoice] ALERTA. PEPITO HA SIDO VICTIMA DE SECUESTRO";
                    mensaje4 = "Su ubicación es:";
                    mensaje2=" Latitud: " + info[0];
                    mensaje3=" Longitud: " + info[1];
                    for (int i = 0; i < 4; i++) {
                        Long templ = preferenciascon.getLong("contacto" + (i + 1), 0);
                        if (templ != 0L) {
                            //Enviar mensaje de secuestro
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje1, null, null);
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje4, null, null);
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje2, null, null);
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje3, null, null);
                        }
                    }
                }
                if (res.equalsIgnoreCase(temp4) || res.equalsIgnoreCase("7884")) {
                    Toast.makeText(contexto, "Comando recibido: Caso de AVISTAMIENTO DE CRIMEN", Toast.LENGTH_LONG).show();
                    mensaje1="[VigiVoice] ALERTA. PEPITO HA PRESENCIADO UN CRIMEN";
                    mensaje4 = "Su ubicación es:";
                    mensaje2=" Latitud: " + info[0];
                    mensaje3=" Longitud: " + info[1];
                    for (int i = 0; i < 4; i++) {
                        Long templ = preferenciascon.getLong("contacto" + (i + 1), 0);
                        if (templ != 0L) {
                            //Enviar mensaje de avistamiento
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje1, null, null);
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje4, null, null);
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje2, null, null);
                            mensajero.sendTextMessage("+57" + preferenciascon.getLong("contacto" + (i + 1), 0), null, mensaje3, null, null);
                        }
                    }
                }
            }
            startSpeechRecognition();
        }


        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onBufferReceived(byte[] arg0) {
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onEvent(int arg0, Bundle arg1) {
        }

        @Override
        public void onPartialResults(Bundle arg0) {
        }

        @Override
        public void onReadyForSpeech(Bundle arg0) {
        }

        @Override
        public void onRmsChanged(float arg0) {
        }
    };

    protected void crearServicio() {
        startSpeechRecognition();
        getLocation();
    }

    private void startSpeechRecognition() {
        // Need to destroy a recognizer to consecutive recognition?
        if (mRecognizer != null) {
            mRecognizer.destroy();
        }

        // Create a recognizer.
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(contexto);
        mRecognizer.setRecognitionListener(mRecognitionListener);

        // Start recognition.
        String lang = "es-ES";
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speech recognition demo");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, lang);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, lang);
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, lang);
        mRecognizer.startListening(intent);
    }

    public ServicioReconocimiento(Context contexto) {
        setContexto(contexto);
        crearServicio();
    }

    public void destruirServicio() {
        if (mRecognizer != null) {
            mRecognizer.destroy();
        }
    }


    public void getLocation() {

        if (isLocationEnabled()) {

            if (ActivityCompat.checkSelfPermission(contexto, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(contexto, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            }
            cliente.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        Location location = task.getResult();
                        if (location != null) {
                            //Toast.makeText(contexto,"Latitud: "+location.getLatitude() + "Longitud: "+location.getLongitude(), Toast.LENGTH_LONG).show();
                            info[0] = "" + location.getLatitude();
                            info[1] = "" + location.getLongitude();
                        }
                    }
                }
            });
        } else {
            Toast.makeText(contexto, "HABILITA LA UBICACIÓN DE TU TELEFONO", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            contexto.startActivity(intent);
        }
    }

    public boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) contexto.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
        cliente = LocationServices.getFusedLocationProviderClient(contexto);
    }

    private Context contexto;
}