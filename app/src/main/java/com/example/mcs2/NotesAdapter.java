package com.example.mcs2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mcs2.Model.Notes;

import java.util.ArrayList;

public class NotesAdapter extends ArrayAdapter<Notes> {
    private Context context;
    private int Resource;

    public NotesAdapter(Context context, int resource, ArrayList<Notes> data){
        super(context,resource,data);
        this.context = context;
        this.Resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(Resource,parent,false);

        TextView nName = (TextView) convertView.findViewById(R.id.Notesname);
        TextView nDate = (TextView) convertView.findViewById(R.id.Ndate);
        TextView description = (TextView) convertView.findViewById(R.id.Notesdesription);

        nName.setText(getItem(position).getNotesTitle());
        nDate.setText(getItem(position).getNotesDate());
        description.setText(getItem(position).getNotesDescription());

        return convertView;
    }
}
