package com.example.pr18;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FourthActivity extends AppCompatActivity {

    private static final int CM_DELETE_ID = 1;

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView lvSimple;
    SimpleAdapter adapter;
    ArrayList<Map<String, Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Button btn = findViewById(R.id.btnOpen);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(FourthActivity.this, FifthActivity.class);
            startActivity(intent);
        });

        lvSimple = findViewById(R.id.lvSimple);

        data = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put(ATTRIBUTE_NAME_TEXT, "sometext " + i);
            map.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher);
            data.add(map);
        }

        String[] from = {
                ATTRIBUTE_NAME_TEXT,
                ATTRIBUTE_NAME_IMAGE
        };

        int[] to = {
                R.id.tvText,
                R.id.ivImg
        };

        adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item4,
                from,
                to
        );

        lvSimple.setAdapter(adapter);

        registerForContextMenu(lvSimple);
    }

    public void onButtonClick(View v) {

        Map<String, Object> map = new HashMap<>();
        map.put(ATTRIBUTE_NAME_TEXT, "sometext " + (data.size() + 1));
        map.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher);

        data.add(map);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == CM_DELETE_ID) {

            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            data.remove(info.position);
            adapter.notifyDataSetChanged();

            return true;
        }

        return super.onContextItemSelected(item);
    }
}