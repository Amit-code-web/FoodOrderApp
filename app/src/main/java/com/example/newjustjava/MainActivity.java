package com.example.newjustjava;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView t=new TextView(this);
        t.setTextColor(Color.RED);
        setContentView(R.layout.activity_main);
    }
    public void res(View view)
    {
      Intent i=new Intent(this, Restaurants.class);
      startActivity(i);
    }
    public void dis(View view)
    {
        Intent i=new Intent(this, Dishes.class);
        startActivity(i);
    }
    public void snack(View view)
    {
        Intent i=new Intent(this, Snacks.class);
        startActivity(i);
    }
    public void drink(View view)
    {
        Intent i=new Intent(this, Drinks.class);
        startActivity(i);
    }
}
