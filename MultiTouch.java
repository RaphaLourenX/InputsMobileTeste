package com.raphalourenx.helloWorld;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MultiTouch extends Activity {
    private MultiTouchView multiTouchView;

   public class MultiTouchView extends View implements View.OnTouchListener{
       public static final int MAX_TOUCHES = 5;
       Point points[];
       boolean isVisible[];
       int colors[];

       public MultiTouchView(Context context) {
           super(context);
           points = new Point[MAX_TOUCHES];
           isVisible = new boolean[MAX_TOUCHES];
           colors = new int[MAX_TOUCHES];

           colors[0] = android.R.color.black;
           colors[1] = android.R.color.holo_blue_bright;
           colors[2] = android.R.color.holo_red_dark;
           colors[3] = android.R.color.holo_green_dark;
           colors[4] = android.R.color.holo_purple;

           for (int i = 0; i < MAX_TOUCHES; i++) isVisible[i] = false;
       }

       @Override
       public boolean onTouch(View v, MotionEvent event) {

           int action = event.getActionMasked();
           int pointerIndex = event.getActionIndex();
           int id = event.getPointerId(pointerIndex);

           switch (action){
               case MotionEvent.ACTION_DOWN:
               case MotionEvent.ACTION_POINTER_DOWN:
                   points[id] = new Point();
                   points[id].x = (int) event.getX(pointerIndex);
                   points[id].y = (int) event.getY(pointerIndex);
                   isVisible[id] = true;
                   break;

               case MotionEvent.ACTION_UP:
               case MotionEvent.ACTION_POINTER_UP:
                   isVisible[id] = false;
                   break;

               case MotionEvent.ACTION_MOVE:
                   for (int i = 0;i < event.getPointerCount(); i++){
                       int _id = event.getPointerId(i);
                       if (id < MAX_TOUCHES) {
                           points[_id].x = (int) event.getX(i);
                           points[_id].y = (int) event.getY(i);
                       }
                   }
                   break;
           }

           invalidate();
           return true;
       }

       @Override
       protected void onDraw(Canvas canvas) {
           super.onDraw(canvas);

           Paint p = new Paint();

           for(int i = 0; i < MAX_TOUCHES; i++){
               if(isVisible[i]){
                   p.setColor(colors[i]);
                   canvas.drawCircle(points[i].x, points[i].y, 200,p);
               }
           }
       }
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        multiTouchView = new MultiTouchView(this);
        setContentView(multiTouchView);
    }
}