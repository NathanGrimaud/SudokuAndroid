package com.example.nathan.sudoku;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by nathan on 2/3/17.
 */

public class Case {
    private Rect rect;
    private Integer left;
    private Integer top;
    private Integer right;
    private Integer bottom;
    private String text;
    private Paint paint;
    private Integer col;
    private Integer row;
    private boolean editable = false;

    public Case(Integer left, Integer top, Integer right, Integer bottom, String text, Integer col, Integer row ){
        this.rect = new Rect(left, top, right, bottom);
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.col = col;
        this.row = row;
        this.text = text.toString();
    }

    public void draw(Canvas canvas){


        Paint fill = new Paint();
        fill.setColor(Color.parseColor("#F5F5F5"));
        fill.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, fill);


        if(paint == null)
            paint = new Paint();
        paint.setColor(Color.parseColor("#212121"));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);

        Paint textStyle = new Paint();
        textStyle.setTextSize(40);

        if (editable == false)
            textStyle.setFakeBoldText(true);

        canvas.drawText(String.valueOf(text), left + 30, top + 65,textStyle);

    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public boolean contains(Integer x, Integer y){
        return rect.contains(x,y);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text.toString();
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}
