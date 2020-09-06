package com.example.prerana.comprehensiveretailsolution.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prerana.comprehensiveretailsolution.R;

public class SalesCustomAdapter extends BaseAdapter {

    Context context;
    int images[];
    String[] category;
    LayoutInflater inflater;



    public SalesCustomAdapter(Context context, int[] images, String[] category, LayoutInflater inflater) {
        this.context = context;
        this.images = images;
        this.category = category;
        this.inflater = inflater;
    }

    public SalesCustomAdapter(Context applicationContext, int[] images, String[] category) {
        this.context = applicationContext;
        this.images = images;
        this.category = category;
        inflater = (LayoutInflater.from(applicationContext));
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.spinner_custom_layout, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(images[i]);
        names.setText(category[i]);

        return view;
    }
}
