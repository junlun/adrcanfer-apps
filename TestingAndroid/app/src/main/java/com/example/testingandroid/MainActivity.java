package com.example.testingandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int buttonPressCount;
    private TextView texto;
    private Integer cont;
    private long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (TextView) findViewById(R.id.text2);
        buttonPressCount=0;
    }


    public void pulBoton1(View v){
       cont = 0;
       time = System.currentTimeMillis();
       for(int var =0; var< 1000; var++){
           cont +=var;

       }
       time = System.currentTimeMillis() - time;
        texto.setText(String.format("Ha presionado el botón 1. (1 000 iteraciones) Tiempo: " + time + " milisegundos"));
        buttonPressCount++;

    }

    public void pulBoton2(View v){
        cont = 0;
        time = System.currentTimeMillis();
        for(int var =0; var< 10000; var++){
            cont +=var;
        }
        time = System.currentTimeMillis() - time;
        texto.setText(String.format("Ha presionado el botón 2. (10 000 iteraciones) Tiempo: " + time + " milisegundos"));
        buttonPressCount++;
    }

    public void pulBoton3(View v){
        cont = 0;
        time = System.currentTimeMillis();
        for(int var =0; var< 100000; var++) {
            cont += var;
        }
        time = System.currentTimeMillis() - time;
        texto.setText(String.format("Ha presionado el botón 3. (100 000 iteraciones) Tiempo: " + time + " milisegundos"));
        buttonPressCount++;
    }

    public void pulBoton4(View v){
        cont = 0;
        time = System.currentTimeMillis();
        for(int var =0; var< 1000000; var++){
            cont +=var;
        }
        time = System.currentTimeMillis() - time;
        texto.setText(String.format("Ha presionado el botón 4. (1 000 000 iteraciones) Tiempo: " + time + " milisegundos"));
        buttonPressCount++;
    }

    public void pulBoton5(View v){
        /*if(buttonPressCount==1)
            cont = 8/0;*/
        cont = 0;
        time = System.currentTimeMillis();
        for(int var =0; var< 2000000; var++){
            cont +=var;
        }
        time = System.currentTimeMillis() - time;
        texto.setText(String.format("Ha presionado el botón 5. (2 000 000 iteraciones) Tiempo: " + time + " milisegundos"));
        buttonPressCount++;
    }
}
