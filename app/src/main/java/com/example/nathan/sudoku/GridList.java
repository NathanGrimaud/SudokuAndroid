package com.example.nathan.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v4.internal.view.SupportSubMenu;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class GridList extends AppCompatActivity {

    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_list);
        context = this;
        Bundle bundle = getIntent().getExtras();
        final Integer level = bundle.getInt("level");

        ListView list = (ListView) findViewById(R.id.list);

        /**
         * we need to read a file, depending on chosen level
         */

        AssetManager assetManager = getAssets();
        final InputStream input;
        ArrayList<vGrid> items = new ArrayList<vGrid>();
        try {
            input = assetManager.open("levels/"+level+".txt");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // byte buffer into a string
            String text = new String(buffer);
            String[] levelList = text.split("\n");
            Integer i = 1;
            for (String col: levelList){
                vGrid grid = new vGrid(level, i, 0);
                grid.setGrid(col);
                items.add(grid);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vGrid grid = (vGrid) parent.getItemAtPosition(position);
                Intent intent = new Intent(context, GridActivity.class);
                Bundle extras =new Bundle();
                extras.putParcelable("vGrid", grid);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });


        MyAdapter adapter = new MyAdapter(this,items);
        list.setAdapter(adapter);

    }
}
