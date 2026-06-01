package com.example.pr18;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<Product> objects;
    LayoutInflater inflater;

    public BoxAdapter(Context ctx, ArrayList<Product> objects) {
        this.ctx = ctx;
        this.objects = objects;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        CheckBox cb;
        TextView name;
        TextView price;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item7, parent, false);

            holder = new ViewHolder();
            holder.cb = convertView.findViewById(R.id.cbBox);
            holder.name = convertView.findViewById(R.id.tvDescr);
            holder.price = convertView.findViewById(R.id.tvPrice);
            holder.img = convertView.findViewById(R.id.ivImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product p = objects.get(position);

        holder.name.setText(p.name);
        holder.price.setText(String.valueOf(p.price));
        holder.img.setImageResource(p.image);

        holder.cb.setOnCheckedChangeListener(null);
        holder.cb.setChecked(p.box);

        holder.cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            p.box = isChecked;
        });

        return convertView;
    }

    public ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<>();
        for (Product p : objects) {
            if (p.box) box.add(p);
        }
        return box;
    }
}