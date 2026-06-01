package com.example.pr18;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_CHECKED = "checked";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnOpen);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        ListView lvSimple = findViewById(R.id.lvSimple);

        String[] texts = { "Text 1", "Text 2", "Text 3", "Text 4", "Text 5" };
        boolean[] checked = { true, false, true, false, true };
        int img = R.mipmap.ic_launcher;

        ArrayList<Map<String, Object>> data = new ArrayList<>();

        for (int i = 0; i < texts.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put(ATTRIBUTE_NAME_TEXT, texts[i]);
            map.put(ATTRIBUTE_NAME_CHECKED, checked[i]);
            map.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(map);
        }

        String[] from = {
                ATTRIBUTE_NAME_TEXT,
                ATTRIBUTE_NAME_CHECKED,
                ATTRIBUTE_NAME_IMAGE
        };

        int[] to = {
                R.id.tvText,
                R.id.cbChecked,
                R.id.ivImg
        };

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item,
                from,
                to
        );

        lvSimple.setAdapter(adapter);
    }
}