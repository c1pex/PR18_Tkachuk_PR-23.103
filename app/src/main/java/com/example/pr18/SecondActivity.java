package com.example.pr18;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_VALUE = "value";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    final int positive = android.R.drawable.arrow_up_float;
    final int negative = android.R.drawable.arrow_down_float;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        Button btn = findViewById(R.id.btnOpen);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivity(intent);
        });

        ListView lvSimple = findViewById(R.id.lvSimple);

        int[] values = {8, 4, -3, 2, -5, 0, 3, -6, 1, -1};

        ArrayList<Map<String, Object>> data = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            Map<String, Object> map = new HashMap<>();

            map.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1));
            map.put(ATTRIBUTE_NAME_VALUE, values[i]);

            int img;
            if (values[i] > 0) img = positive;
            else if (values[i] < 0) img = negative;
            else img = 0;

            map.put(ATTRIBUTE_NAME_IMAGE, img);

            data.add(map);
        }

        String[] from = {
                ATTRIBUTE_NAME_TEXT,
                ATTRIBUTE_NAME_VALUE,
                ATTRIBUTE_NAME_IMAGE
        };

        int[] to = {
                R.id.tvText,
                R.id.tvValue,
                R.id.ivImg
        };

        MySimpleAdapter adapter = new MySimpleAdapter(
                this,
                data,
                R.layout.item_dynamic,
                from,
                to
        );

        lvSimple.setAdapter(adapter);
    }

    class MySimpleAdapter extends SimpleAdapter {

        public MySimpleAdapter(Context context,
                               List<? extends Map<String, ?>> data,
                               int resource,
                               String[] from,
                               int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public void setViewText(TextView v, String text) {
            super.setViewText(v, text);

            if (v.getId() == R.id.tvValue) {
                int value = Integer.parseInt(text);

                if (value < 0) v.setTextColor(Color.RED);
                else if (value > 0) v.setTextColor(Color.GREEN);
                else v.setTextColor(Color.GRAY);
            }
        }

        @Override
        public void setViewImage(ImageView v, int value) {
            super.setViewImage(v, value);

            if (value == negative) v.setBackgroundColor(Color.RED);
            else if (value == positive) v.setBackgroundColor(Color.GREEN);
        }
    }
}