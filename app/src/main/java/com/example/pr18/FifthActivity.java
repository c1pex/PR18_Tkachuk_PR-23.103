package com.example.pr18;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class FifthActivity extends AppCompatActivity {

    private static final int CM_DELETE_ID = 1;

    DBFirst dbFirst;
    Cursor cursor;
    SimpleCursorAdapter adapter;
    ListView lvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        Button btn = findViewById(R.id.btnOpen);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(FifthActivity.this, SixthActivity.class);
            startActivity(intent);
        });

        lvData = findViewById(R.id.lvData);

        dbFirst = new DBFirst(this);
        dbFirst.open();

        cursor = dbFirst.getAllData();

        String[] from = {DBFirst.COLUMN_IMG, DBFirst.COLUMN_TXT};
        int[] to = {R.id.ivImg, R.id.tvText};

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.item5,
                cursor,
                from,
                to,
                0
        );

        lvData.setAdapter(adapter);

        registerForContextMenu(lvData);
    }

    public void onButtonClick(View v) {

        dbFirst.addRec("sometext " + cursor.getCount(), android.R.drawable.ic_menu_gallery);

        cursor.requery();
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

            dbFirst.delRec(info.id);

            cursor.requery();
            adapter.notifyDataSetChanged();

            return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbFirst.close();
    }
}