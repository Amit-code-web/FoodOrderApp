package com.example.newjustjava;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class wordAdapter extends ArrayAdapter<word> {

    public wordAdapter(Activity context, ArrayList<word> abc)
    {
        super(context,0,abc);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        word currentword=getItem(position);
        TextView engtext=(TextView) listItemView.findViewById(R.id.text_1);
        engtext.setText(currentword.getenglishword());
        TextView hintext=(TextView) listItemView.findViewById(R.id.text_2);
        hintext.setText(currentword.gethindiword());
        ImageView icon= (ImageView) listItemView.findViewById((R.id.image));
//        setVisibility is used to set whether a image will be added or not based on the type
//        constructor called i.e. whether a two text constructor or two text and one image constructor.
        if(currentword.hasImage()) {
            icon.setImageResource(currentword.getImage_id());
            icon.setVisibility(View.VISIBLE);
        }
        else
        {
            icon.setVisibility(View.GONE);
        }
        return listItemView;
    }
}
