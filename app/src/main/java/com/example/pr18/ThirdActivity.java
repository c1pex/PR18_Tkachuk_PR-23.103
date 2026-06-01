package com.example.pr18;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThirdActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button btn = findViewById(R.id.btnOpen);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
            startActivity(intent);
        });

        ListView lvSimple = findViewById(R.id.lvSimple);

        int[] load = {41, 48, 22, 35, 30, 67, 51, 88};

        ArrayList<Map<String, Object>> data = new ArrayList<>();

        for (int i = 0; i < load.length; i++) {
            Map<String, Object> map = new HashMap<>();

            map.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1) + ". Load: " + load[i] + "%");
            map.put(ATTRIBUTE_NAME_PB, load[i]);
            map.put(ATTRIBUTE_NAME_LL, load[i]);

            data.add(map);
        }

        String[] from = {
                ATTRIBUTE_NAME_TEXT,
                ATTRIBUTE_NAME_PB,
                ATTRIBUTE_NAME_LL
        };

        int[] to = {
                R.id.tvLoad,
                R.id.pbLoad,
                R.id.llLoad
        };

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item3,
                from,
                to
        );

        adapter.setViewBinder(new MyViewBinder());

        lvSimple.setAdapter(adapter);
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {

            int value;

            if (view.getId() == R.id.llLoad) {

                value = (Integer) data;

                if (value < 40) {
                    view.setBackgroundColor(Color.GREEN);
                } else if (value < 70) {
                    view.setBackgroundColor(Color.YELLOW);
                } else {
                    view.setBackgroundColor(Color.RED);
                }

                return true;
            }

            if (view.getId() == R.id.pbLoad) {
                ((ProgressBar) view).setProgress((Integer) data);
                return true;
            }

            return false;
        }
    }
}