package com.example.pr18;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;

public class SixthActivity extends Activity {

    ExpandableListView elvMain;
    DB2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        Button btn = findViewById(R.id.btnOpen);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(SixthActivity.this, SeventhActivity.class);
            startActivity(intent);
        });
        elvMain = findViewById(R.id.elvMain);

        db = new DB2(this);
        db.open();

        Cursor groupCursor = db.getCompanyData();
        startManagingCursor(groupCursor);

        String[] groupFrom = { DB2.COMPANY_NAME };
        int[] groupTo = { android.R.id.text1 };

        String[] childFrom = { DB2.PHONE_NAME };
        int[] childTo = { android.R.id.text1 };

        SimpleCursorTreeAdapter adapter =
                new SimpleCursorTreeAdapter(
                        this,
                        groupCursor,
                        android.R.layout.simple_expandable_list_item_1,
                        groupFrom,
                        groupTo,
                        android.R.layout.simple_list_item_1,
                        childFrom,
                        childTo
                ) {

                    @Override
                    protected Cursor getChildrenCursor(Cursor groupCursor) {

                        int id = groupCursor.getInt(
                                groupCursor.getColumnIndexOrThrow(DB2.COMPANY_ID)
                        );

                        return db.getPhones(id);
                    }
                };

        elvMain.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}