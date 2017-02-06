package com.example.nathan.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelView extends AppCompatActivity {

    Activity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_view);

        context = this;

        Button level1 = (Button) findViewById(R.id.level1);
        Button level2 = (Button) findViewById(R.id.level2);
        Button level3 = (Button) findViewById(R.id.level3);


        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GridList.class);
                Bundle extras = new Bundle();
                extras.putInt("level",1);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GridList.class);
                Bundle extras = new Bundle();           
                extras.putInt("level",2);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GridList.class);
                Bundle extras = new Bundle();
                extras.putInt("level",3);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
