package com.raphalourenx.helloWorld;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SingleTouch extends Activity implements View.OnTouchListener{

    Game game;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = new Game(this);
        setContentView(game);

        game.setOnTouchListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                game.update();
                game.invalidate();

                new Handler().postDelayed(this, 300);
            }
        }, 300);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            Log.i("info", "Tocou");
            int x = (int) event.getX();
            int y = (int) event.getY();

            game.touch(x, y);
        }
        return true;
    }
}
