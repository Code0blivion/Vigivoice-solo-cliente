package com.example.vigivoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


public class CapturaAudio extends AppCompatActivity {

    private TextView contador;
    private Button boton;
    private MediaRecorder grabacion;
    private String archivoSalida=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_audio);
        contador = (TextView) findViewById(R.id.avisocuenta);
        boton = (Button)findViewById(R.id.button);
        Toolbar toolbar2 = findViewById(R.id.tlb2);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void grabarAudio(View view){
        archivoSalida = getExternalFilesDir(null).getAbsolutePath()+"/Muestra.wav";
        grabacion = new MediaRecorder();
        grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabacion.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        grabacion.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        grabacion.setOutputFile(archivoSalida);

        try{
            grabacion.prepare();
            grabacion.start();
            boton.setEnabled(false);
            cambioGrabando();
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }catch (IOException e){}

        new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                contador.setText("Segundos restantes: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    grabacion.stop();
                    grabacion.release();
                    grabacion = null;
                    contador.setText("Grabación Completada!");
                    boton.setEnabled(true);
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

    public void cambioGrabando(){
        boton.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
    }

    public void cambioGrabar(){
        boton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
    }
}