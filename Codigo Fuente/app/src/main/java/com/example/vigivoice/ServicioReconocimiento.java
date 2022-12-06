package com.example.vigivoice;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServicioReconocimiento extends Service {

    protected SpeechRecognizer reconocedor;
    private Intent recognizerIntent;


    public void onCreate(){
            reconocedor = SpeechRecognizer.createSpeechRecognizer(this);
            reconocedor.setRecognitionListener(new SpeechRecognitionListener());
            recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getOpPackageName());
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                recognizerIntent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE,true);
            }
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        reconocedor.startListening(recognizerIntent);
        return START_REDELIVER_INTENT;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected class SpeechRecognitionListener implements RecognitionListener
    {

        @Override
        public void onBeginningOfSpeech()
        {

        }

        @Override
        public void onBufferReceived(byte[] buffer)
        {

        }

        @Override
        public void onEndOfSpeech()
        {
            //Log.d(TAG, "onEndOfSpeech"); //$NON-NLS-1$
        }

        @Override
        public void onError(int error)
        {

        }

        @Override
        public void onEvent(int eventType, Bundle params)
        {

        }

        @Override
        public void onPartialResults(Bundle partialResults)
        {
            String  clave = "<OPEN>";

            String [] coincidencias = partialResults.getStringArray(SpeechRecognizer.RESULTS_RECOGNITION);
            float [] puntos = partialResults.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES);
            boolean flag = false;

            if(coincidencias !=null){

                if(flag){
                    flag=false;
                    Intent intma = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intma);
                }else{
                    for(String s:coincidencias){
                        if(s.equalsIgnoreCase(clave)){
                            flag=true;
                        }
                    }
                }

            }
        }

        @Override
        public void onReadyForSpeech(Bundle params)
        {

        }

        @Override
        public void onResults(Bundle results)
        {
            String  clave = "<OPEN>";

            String [] coincidencias = results.getStringArray(SpeechRecognizer.RESULTS_RECOGNITION);
            float [] puntos = results.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES);
            boolean flag = false;

            if(coincidencias !=null){

                if(flag){
                    flag=false;
                    Intent intma = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intma);
                }else{
                    for(String s:coincidencias){
                        if(s.equalsIgnoreCase(clave)){
                            flag=true;
                        }
                    }
                }

            }
        }

        @Override
        public void onRmsChanged(float rmsdB)
        {

        }

    }
}