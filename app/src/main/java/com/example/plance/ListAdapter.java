package com.example.plance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListAdapter extends ArrayAdapter {

    private int[] Icon;
    private String[] Headline;
    private String[] Subhead;
    private Context context;

    ListAdapter(@NonNull Context context, String[] headline, String[] subhead) {
        super(context, R.layout.custom_listview, headline);
        this.Headline = headline;
        this.Subhead = subhead;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_listview, null, true);
        TextView headline = view.findViewById(R.id.text_headline);
        TextView subhead = view.findViewById(R.id.text_subhead);

        headline.setText(Headline[position]);
        subhead.setText(Subhead[position]);
        return view;
    }
}