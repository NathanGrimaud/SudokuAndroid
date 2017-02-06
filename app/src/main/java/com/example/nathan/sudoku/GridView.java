package com.example.nathan.sudoku;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Range;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class GridView extends View implements View.OnTouchListener {


    Case[][] cases = new Case[9][9];
    ArrayList<Case> input = new ArrayList<>();
    Case selected;
    vGrid grid;

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);

        Integer min = 1;
        Integer max = 10;
        Integer space = 100;

        Activity host = (Activity)this.getContext();
        Bundle b = host.getIntent().getExtras();
        grid = b.getParcelable("vGrid");

        char[] griItems = grid.getGrid().toCharArray();
        Integer counter = 0;
        for (Integer y =  min; y< max; y ++){
            for (Integer x = min; x < max; x ++){
                Integer top = y * space;
                Integer left = x * space;
                Integer topSize = top + space - 1;
                Integer leftSize = left + space - 1;

                String number = String.valueOf(griItems[counter]);

                Case c = new Case(left , top , leftSize , topSize, number, x, y );

                if(number.equals("0")){
                    c.setEditable(true);
                    c.setText("");
                }


                cases[x-1][y-1] = c;
                counter ++;
            }
        }

        Integer gridY = 1200;
        Paint fatBorder = new Paint();
        fatBorder.setColor(Color.parseColor("#212121"));
        fatBorder.setStrokeWidth(5);

        for (Integer x = 1; x<10;x++){
            // we set y to -1 because numbers are not in the grid;
            Case c = new Case(x*space ,gridY ,x*space+space-1 , gridY + space-1 , x.toString(), x, -1);
            c.setPaint(fatBorder);
            input.add(c);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(Case[] arr: cases){
            for(Case c: arr){
                c.draw(canvas);
            }
        }
        for (Case c:input){
            c.draw(canvas);
        }

        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        for (int row = 0 ; row < 9; row++) {
            Integer spacedRow = row+1;
            if(row % 3 == 0 && row != 0){
                canvas.drawLine(100, spacedRow * 100, 1000, spacedRow * 100, paint);
            }
        }
        for (int column = 0 ; column < 9; column++) {
            Integer spacedCol = column+1;
            if(column % 3 == 0 && column != 0 ){
                canvas.drawLine(spacedCol * 100, 100, spacedCol * 100, 1000, paint);
            }
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Integer x = (int)event.getX();
        Integer y = (int) event.getY();
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                for (Case c: input){
                    if (c.contains(x, y)){
                        selected = c;
                    }
                }
                break;


            case MotionEvent.ACTION_UP:

                for(Case[] arr: cases ){
                    for (Case c: arr){
                        if (c.contains(x, y)){
                            if(selected != null){
                                if(c.isEditable() && canPlace(c, selected))
                                    c.setText(selected.getText());
                            }

                            selected = null;
                            invalidate();
                        }
                    }
                }
                checkForWin();
                break;

        }
        return true;
    }
    public boolean canPlace(Case current, Case next){

        Integer caseRow = current.getRow()-1;
        Integer caseCol = current.getCol()-1;

        Activity host = (Activity)this.getContext();
        Toast toast = Toast.makeText(host, "Impossible de placer le nombre içi", Toast.LENGTH_SHORT);
        for(Integer xx=0; xx <9; xx++){
            if(cases[caseCol][xx].getText().equals(next.getText())){
                toast.show();
                return false;
            }
        }
        for(Integer yy = 0; yy < 9; yy ++){

            if(cases[yy][caseRow].getText().equals(next.getText())){
                toast.show();
                return false;
            }
        }

        // now we have to check if it can be placed in the 3/3 square
        Integer startX = 0;
        Integer startY = 0;


        if(caseRow/3 ==0)
            startX = 0;
        if(caseRow/3==1)
            startX = 3;
        if (caseRow/3==2)
            startX = 6;
        if(caseCol/3 ==0)
            startY = 0;
        if(caseCol/3==1)
            startY = 3;
        if (caseCol/3==2)
            startY = 6;

        for(Integer x = startX; x<startX+3;x++){
            for(Integer y = startY; y<startY+3;y++){
                if(cases[x][y].getText().equals(next.getText())){
                    toast.show();
                    return false;
                }
            }
        }

        return true;
    }
    public void checkForWin(){
        boolean win = true;
        for(Case[] arr: cases){
            for(Case c: arr){
               if(c.getText().equals("0"))
                   win = false;
            }
        }
        Activity host = (Activity)this.getContext();
        if(win)
            Toast.makeText(host, "Gagné", Toast.LENGTH_LONG);



    }
}
