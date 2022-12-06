package com.example.vigivoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Contactos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        Toolbar toolbar5 = findViewById(R.id.tlb5);
        setSupportActionBar(toolbar5);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contacto1 = (EditText) findViewById(R.id.contacto1);
        contacto2 = (EditText) findViewById(R.id.contacto2);
        contacto3 = (EditText) findViewById(R.id.contacto3);
        contacto4 = (EditText) findViewById(R.id.contacto4);
        casaviso = (TextView) findViewById(R.id.casillaviso);
        asignarContacto();

    }

    public void guardarContactos(View view) {
        String numero1 = contacto1.getText().toString();
        String numero2 = contacto2.getText().toString();
        String numero3 = contacto3.getText().toString();
        String numero4 = contacto4.getText().toString();
        String aviso = "";
        casaviso.setText("");
        boolean ver = false;

        if (numero1.equals("")) {
            aviso += "1 ";
            numero1 = "0";
            ver = true;
        }

        if (numero2.equals("")) {
            aviso += "2 ";
            numero2 = "0";
            ver = true;
        }

        if (numero3.equals("")) {
            aviso += "3 ";
            numero3 = "0";
            ver = true;
        }

        if (numero4.equals("")) {
            aviso += "4 ";
            numero4 = "0";
            ver = true;
        }

        if (ver == true) {
            Toast.makeText(this, "Los contactos " + "(" + aviso + ")" + " están vacios", Toast.LENGTH_LONG).show();
            aviso = "";
            ver = false;
        }

        if (numero1.length() != 10 || numero1.charAt(0) != '3') {
            numero1 = "0";
            aviso += "1 ";
            ver = true;
        }

        if (numero2.length() != 10 || numero2.charAt(0) != '3') {
            numero2 = "0";
            aviso += "2 ";
            ver = true;
        }

        if (numero3.length() != 10 || numero3.charAt(0) != '3') {
            numero3 = "0";
            aviso += "3 ";
            ver = true;
        }

        if (numero4.length() != 10 || numero4.charAt(0) != '3') {
            numero4 = "0";
            aviso += "4 ";
            ver = true;
        }

        if (ver == true) {
            Toast.makeText(this, "Los contactos " + "(" + aviso + ")" + " no tienen el formato de un celular colombiano" + "\n" +
                    "Por esta razón no se han guardado dichos contactos", Toast.LENGTH_LONG).show();
            aviso = "";
            ver = false;
        }

        try {
            cont1 = Long.parseLong(numero1);
            cont2 = Long.parseLong(numero2);
            cont3 = Long.parseLong(numero3);
            cont4 = Long.parseLong(numero4);

            SharedPreferences preferencias = getSharedPreferences("contactos", Context.MODE_PRIVATE);
            SharedPreferences.Editor obj_editor = preferencias.edit();
            obj_editor.putLong("Contacto 1",cont1);
            obj_editor.putLong("Contacto 2",cont2);
            obj_editor.putLong("Contacto 3",cont3);
            obj_editor.putLong("Contacto 4",cont4);
            obj_editor.commit();
            asignarContacto();
        } catch (Exception e) {
            casaviso.setText("Ha digitado simbolos aparte de números. Por favor digite solo números");
        }



    }

    public void asignarContacto(){
        cont1=cont2=cont3=cont4=0L;

        SharedPreferences preferencias = getSharedPreferences("contactos",Context.MODE_PRIVATE);

        cont1 = preferencias.getLong("Contacto 1",0);
        contacto1.setText(""+cont1);
        cont2 = preferencias.getLong("Contacto 2",0);
        contacto2.setText(""+cont2);
        cont3 = preferencias.getLong("Contacto 3",0);
        contacto3.setText(""+cont3);
        cont4 = preferencias.getLong("Contacto 4",0);
        contacto4.setText(""+cont4);

        if(cont1==0)
            contacto1.setText("");

        if(cont2==0)
            contacto2.setText("");

        if(cont3==0)
            contacto3.setText("");

        if(cont4==0)
            contacto4.setText("");
    }



    private Long cont1, cont2, cont3, cont4;
    private EditText contacto1, contacto2, contacto3, contacto4;
    private TextView casaviso;

}