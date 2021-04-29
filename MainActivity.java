package com.raphalourenx.helloWorld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button btnPlay;
    Button btnPlayMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnPlayMulti = findViewById(R.id.btnPlayMulti);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("info", "onStart() iniciado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("info", "on Resume");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("info", "on Stop");
    }

    public void onClick(View view){
        if (view == btnPlay){
            //Carregar janela de jogo
            Log.i("info", "Apertou Play");
            Intent intent = new Intent(this, SingleTouch.class);
            startActivity(intent);
        }

        if (view == btnPlayMulti){
            //Carregar janela de jogo
            Log.i("info", "Apertou Play");
            Intent intent = new Intent(this, MultiTouch.class);
            startActivity(intent);
        }
    }
}