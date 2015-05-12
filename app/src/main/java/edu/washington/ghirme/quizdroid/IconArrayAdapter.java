package edu.washington.ghirme.quizdroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IconArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public IconArrayAdapter(Context context, String[] values) {
        super(context, R.layout.icon_expandable_list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.icon_expandable_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);

        imageView.setImageResource(R.mipmap.ic_launcher);


        return rowView;
    }
}