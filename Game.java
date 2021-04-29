package com.raphalourenx.helloWorld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

public class Game extends View {

    Bitmap character;
    int frames = 8;
    int currentFrame = 0;
    int x, y, targetX, targetY;
    float vel = 20;

    public Game(Context context){
        super(context);

        character = BitmapFactory.decodeResource(getResources(), R.drawable.bitmap_character);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect srcRect = new Rect();
        srcRect.top = 0;
        srcRect.left = currentFrame * (character.getWidth() / 8);
        srcRect.right = srcRect.left + (character.getWidth() / 8);
        srcRect.bottom = character.getHeight();

        Rect dstRect = new Rect();
        dstRect.left = x; dstRect.top = y;
        dstRect.right = dstRect.left + (character.getWidth() / 8);
        dstRect.bottom = dstRect.top + character.getHeight();

        canvas.drawBitmap(character, srcRect, dstRect, null);
    }

    public void update(){
        currentFrame = (currentFrame + 1) % frames;

        int deltaX = targetX - x;
        int deltaY = targetY - y;

        float mag = (float) Math.sqrt (deltaX * deltaX + deltaY * deltaY);

        if (mag > 0.1f){
            deltaX /= mag;
            deltaY /= mag;

            x += x + deltaX * 200;
            y += y + deltaY * 200;
        }
    }

    public void touch(int x, int y){
        targetX = x;
        targetY = y;
    }
}
