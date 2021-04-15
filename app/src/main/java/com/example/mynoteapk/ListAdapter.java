package com.example.mynoteapk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Note> movieItems;
    public ListAdapter(Activity activity, List<Note> movieItems){
        this.activity = activity;
        this.movieItems = movieItems;
    }
    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.costum_layout, null);
        TextView tgl = (TextView)convertView.findViewById(R.id.t_tgl);
        TextView title = (TextView)convertView.findViewById(R.id.t_title);
        TextView description = (TextView)convertView.findViewById(R.id.t_description);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.kartun);

        Note n = movieItems.get(position);

        tgl.setText("Tanggal : "+ n.getTgl());
        title.setText("Title : "+ n.getTitle());
        description.setText("Description : "+ n.getDeskripsi());

        return convertView;
    }
}
