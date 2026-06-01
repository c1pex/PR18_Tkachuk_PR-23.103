package com.example.pr18;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SeventhActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<>();
    BoxAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);

        fillData();

        ListView lv = findViewById(R.id.lvMain);
        adapter = new BoxAdapter(this, products);
        lv.setAdapter(adapter);
    }

    void fillData() {
        for (int i = 1; i <= 20; i++) {
            products.add(new Product(
                    "Product " + i,
                    i * 100,
                    android.R.drawable.ic_menu_gallery,
                    false
            ));
        }
    }

    public void showResult(View v) {

        StringBuilder result = new StringBuilder("Корзина:\n\n");

        for (Product p : adapter.getBox()) {
            result.append("• ").append(p.name).append("\n");
        }

        new AlertDialog.Builder(this)
                .setTitle("Корзина")
                .setMessage(result.toString())
                .setPositiveButton("OK", null)
                .show();
    }
}