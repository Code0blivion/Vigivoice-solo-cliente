package com.example.vigivoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class ComandosPersonalizados extends AppCompatActivity {

    private String archivoSalida = null;
    private MediaRecorder grabacion;
    private FloatingActionButton comando1, comando2, comando3, comando4;

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
    }

    public void grabarComando(View view, String comando){
        archivoSalida = getExternalFilesDir(null).getAbsolutePath()+"/"+comando+".wav";
        grabacion = new MediaRecorder();
        grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabacion.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        grabacion.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        grabacion.setOutputFile(archivoSalida);

        try{
            grabacion.prepare();
            grabacion.start();
            comando1.setEnabled(false);
            comando2.setEnabled(false);
            comando3.setEnabled(false);
            comando4.setEnabled(false);
            cambioGrabando();
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }catch (IOException e){}


        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                grabacion.stop();
                grabacion.release();
                grabacion = null;
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
}

