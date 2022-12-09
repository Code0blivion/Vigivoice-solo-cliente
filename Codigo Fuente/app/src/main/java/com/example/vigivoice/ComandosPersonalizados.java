package com.example.vigivoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class ComandosPersonalizados extends AppCompatActivity {

    private FloatingActionButton comando1, comando2, comando3, comando4;
    private SpeechRecognizer mRecognizer;
    private String tempCom="";
    private RecognitionListener mRecognitionListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comandos_personalizados);
        Toolbar toolbar4 = findViewById(R.id.tlb4);
        setSupportActionBar(toolbar4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        comando1 = (FloatingActionButton) findViewById(R.id.com1);
        comando2 = (FloatingActionButton) findViewById(R.id.com2);
        comando3 = (FloatingActionButton) findViewById(R.id.com3);
        comando4 = (FloatingActionButton) findViewById(R.id.com4);
        setReconocedor(this);
    }

    public void grabarComando(View view, String comando){

        startSpeechRecognition();
        comando1.setEnabled(false);
        comando2.setEnabled(false);
        comando3.setEnabled(false);
        comando4.setEnabled(false);
        cambioGrabando();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                destruirServicio();
                //Guardar el string del comando en Preferences...
                SharedPreferences preferencias = getSharedPreferences("Comandos", Context.MODE_PRIVATE);
                SharedPreferences.Editor obj_editor = preferencias.edit();
                obj_editor.putString(comando,tempCom);
                obj_editor.commit();
                comando1.setEnabled(true);
                comando2.setEnabled(true);
                comando3.setEnabled(true);
                comando4.setEnabled(true);
                cambioGrabar();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                    /*
                    //Prueba del audio
                    MediaPlayer mp = new MediaPlayer();
                    try{
                        mp.setDataSource(archivoSalida);
                        mp.prepare();
                    }catch (IOException e){}

                    mp.start();
                     */
            }
        }.start();
    }

    public void grabarComando1(View view){
        grabarComando(view,"comando1");
    }

    public void grabarComando2(View view){
        grabarComando(view,"comando2");
    }

    public void grabarComando3(View view){
        grabarComando(view,"comando3");
    }

    public void grabarComando4(View view){
        grabarComando(view,"comando4");
    }

    public void cambioGrabando(){
        comando1.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        comando2.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        comando3.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        comando4.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
    }

    public void cambioGrabar(){
        comando1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        comando2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        comando3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        comando4.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
    }


    private void startSpeechRecognition() {
        // Need to destroy a recognizer to consecutive recognition?
        if (mRecognizer != null) {
            mRecognizer.destroy();
        }

        // Create a recognizer.
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(ComandosPersonalizados.this);
        mRecognizer.setRecognitionListener(mRecognitionListener);

        // Start recognition.
        String lang = "es-ES";
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speech recognition demo");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, lang);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, lang);
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, lang);
        mRecognizer.startListening(intent);
    }

    public void setReconocedor(Context contexto){
        mRecognitionListener = new RecognitionListener() {
            @Override
            public void onError(int error) {
                if ((error == SpeechRecognizer.ERROR_NO_MATCH) ||
                        (error == SpeechRecognizer.ERROR_SPEECH_TIMEOUT)) {
                    Toast.makeText(contexto,"Error: No se ha recibido comando alguno. Intente de nuevo",Toast.LENGTH_LONG).show();
                }
                return;
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> values = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                Toast.makeText(contexto,"Comando recibido: "+values.get(0)+"\nSi este no es el comando dictado," +
                        "dictelo de nuevo hasta que aparezca la expresión deseada",Toast.LENGTH_LONG).show();
                if(values.get(0)!=null){
                    tempCom = values.get(0);
                }
            }

            @Override public void onBeginningOfSpeech() {}
            @Override public void onBufferReceived(byte[] arg0) {}
            @Override public void onEndOfSpeech() {}
            @Override public void onEvent(int arg0, Bundle arg1) {}
            @Override public void onPartialResults(Bundle arg0) {}
            @Override public void onReadyForSpeech(Bundle arg0) {}
            @Override public void onRmsChanged(float arg0) {}
        };
    }


    public void destruirServicio(){
        if (mRecognizer != null) {
            mRecognizer.destroy();
        }
    }
}

